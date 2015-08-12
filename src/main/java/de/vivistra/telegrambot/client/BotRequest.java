package de.vivistra.telegrambot.client;

import java.io.File;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.vivistra.telegrambot.model.message.Message;
import de.vivistra.telegrambot.receiver.UpdateRequest;

/**
 * This class is used by {@see Bot} to perform a request against the Telegram
 * API.
 */
public class BotRequest {
	private static final Logger LOG = LogManager.getLogger();

	private static final String KEY_RECIPIENT = "chat_id";

	private String command = null;
	private MultipartEntityBuilder requestEntity = null;

	/**
	 * Send a message to Telegram. This could be a text, image, audio, ...
	 * message.
	 * 
	 * @param message
	 */
	public BotRequest(Message message) {
		this.command = message.getCommand();

		this.requestEntity = MultipartEntityBuilder.create();
		// New MIME type, data need to be UTF-8 encrypted
		ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), MIME.UTF8_CHARSET);

		// Set recipient, needed for every message
		requestEntity.addTextBody(KEY_RECIPIENT, message.getRecipient().toString(), contentType);

		// Add some more things, like a message text or an image
		switch (message.getMessageType()) {

		case TEXT_MESSAGE:
			requestEntity.addTextBody(message.getJsonKey(), (String) message.getMessage(), contentType);
			break;

		case IMAGE_MESSAGE:
		case DOCUMENT_MESSAGE:
		case AUDIO_MESSAGE:
		case VIDEO_MESSAGE:
			requestEntity.addBinaryBody(message.getJsonKey(), (File) message.getMessage());
			break;

		default:
			LOG.error("Message type is invalid, not supported or not yet implemented");
			break;
		}
	}

	/**
	 * Used for UpdateRequests. With this, the Bot can ask the Telegram API for
	 * Updates.
	 * 
	 * @param updateRequest
	 */
	public BotRequest(UpdateRequest updateRequest) {
		this.command = updateRequest.getCommand();
		this.requestEntity = updateRequest.getRequestEntity();
	}

	/**
	 * Used by the Bot to get the request command.
	 * 
	 * @return
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Used by the class Bot to get the content for the post query.
	 * 
	 * @return
	 */
	public MultipartEntityBuilder getContent() {
		return requestEntity;
	}

	/**
	 * Use this only if you know what you do and when you deeply modify the
	 * command for the request.
	 * 
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * Use this only if you know what you do and when you deeply modify the
	 * content for the request.
	 * 
	 * @param content
	 */
	public void setContent(MultipartEntityBuilder content) {
		this.requestEntity = content;
	}
}
