package de.vivistra.telegrambot.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.vivistra.telegrambot.settings.BotSettings;

/**
 * This class connects to the Telegram API and posts querys.
 */
public class Bot {

	private static final Logger LOG = LogManager.getLogger();
	private static String API_URL_WITH_TOKEN = BotSettings.getApiUrlWithToken();

	private static final Integer RETRY_ATTEMPS = 10;

	private CloseableHttpClient httpsClient;

	/**
	 * Constructor opens the HTTPS connection to the Telegram API.
	 */
	public Bot() {
		httpsClient = buildHttpsClient();
	}

	/**
	 * Create a new httpsClient
	 * 
	 * @return a new httpsClient
	 */
	private CloseableHttpClient buildHttpsClient() {
		// Trust all SSL certificates the host trusts
		// TODO insecure, use custom keystore instead!
		SSLContext sslContext = SSLContexts.createDefault();

		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());

		// Create and return httpsClient
		return HttpClients.custom().setSSLSocketFactory(sslSF).build();
	}

	/**
	 * Close the HTTPS connection when program ends.
	 */
	public boolean close() {
		try {
			// Close connection
			httpsClient.close();
		} catch (IOException e) {
			LOG.error("Could not close HTTPS connection to Telegram API.", e);
			return false;
		}
		return true;
	}

	/**
	 * Performs a HTTPS POST request. This can be used for every request on the
	 * API.
	 */
	public BotResponse post(BotRequest request) {
		HttpPost httpPost = new HttpPost(API_URL_WITH_TOKEN + request.getCommand());

		LOG.debug(httpPost.getRequestLine());

		// Add some content to the request
		if (request.getContent() != null) {

			MultipartEntityBuilder requestEntity = request.getContent();

			httpPost.setEntity(requestEntity.build());
		}

		// Response variables
		CloseableHttpResponse response = null;
		BotResponse botResponse = null;

		// If it fails retry till it reaches RETRY_ATTEMPS
		for (int retryCounter = 0; retryCounter < RETRY_ATTEMPS; retryCounter++) {

			try {
				// Execute the POST query
				response = httpsClient.execute(httpPost);

				HttpEntity entity = response.getEntity();

				LOG.debug(response.getStatusLine());

				// Read the response - create readers
				InputStreamReader inputReader = new InputStreamReader(entity.getContent());
				BufferedReader reader = new BufferedReader(inputReader);

				// Read the response - create string builder
				StringBuilder bodyBuilder = new StringBuilder();
				String nextLine;

				// Read the response - read it now
				while ((nextLine = reader.readLine()) != null) {
					bodyBuilder.append(nextLine);
				}
				LOG.debug(bodyBuilder.toString());

				// Build a botResponse object, makes it comfortable to handle ;)
				botResponse = new BotResponse(response.getStatusLine().getStatusCode(), response.getAllHeaders(),
						bodyBuilder.toString());

				// Was successful, do not retry!
				retryCounter = RETRY_ATTEMPS;

			} catch (SocketException e) {
				LOG.warn("Run in SocketException. Attemp: " + (retryCounter + 1) + "/" + RETRY_ATTEMPS);

				// Close old httpsClient
				try {
					httpsClient.close();
				} catch (IOException ioe) {
					LOG.warn("Wanna retry, but I failed to close old httpClient.");
				}

				// Timeout first 0s, then 1s, 2s, 3s, ...
				try {
					Thread.sleep(retryCounter * 1000);
				} catch (InterruptedException exs) {
					LOG.debug("Thread sleep interrupted");
				}

				// Build a new httpsClient
				httpsClient = buildHttpsClient();
				LOG.info("New httpsClient built, retry now.");

			} catch (IOException e) {
				LOG.error("Error while sending / receiving data. Attemp: " + (retryCounter + 1) + "/" + RETRY_ATTEMPS,
						e);

			} finally {
				// When finished, close response
				try {
					if (response != null) {
						response.close();
					}
				} catch (IOException e) {
					LOG.warn("Could not close request.", e);
				}
			}
		}

		return botResponse;
	}
}
