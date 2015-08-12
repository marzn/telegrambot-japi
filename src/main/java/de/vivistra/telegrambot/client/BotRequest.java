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
			requestEntity.addBinaryBody(message.getJsonKey(), (File) message.getMessage());
			break;

		default:
			LOG.error("Message type is invalid, not supported or not yet implemented");
			break;
		}
	}

	public BotRequest(UpdateRequest updateRequest) {
		this.command = updateRequest.getCommand();
		this.requestEntity = updateRequest.getRequestEntity();
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public MultipartEntityBuilder getContent() {
		return requestEntity;
	}

	public void setContent(MultipartEntityBuilder content) {
		this.requestEntity = content;
	}
}
