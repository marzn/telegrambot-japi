package de.vivistra.telegrambot.receiver;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;

public class UpdateRequest {

	private static final String COMMAND = "getUpdates";
	private static final Integer TIMEOUT = 60; // in seconds for long polling
	private static final Integer LIMIT = 100;

	private static final String KEY_MSG_UPDATE_OFFSET = "offset";
	private static final String KEY_MSG_UPDATE_LIMIT = "limit";
	private static final String KEY_MSG_UPDATE_TIMEOUT = "timeout";

	private final Integer offset;

	private MultipartEntityBuilder requestEntity = null;

	public UpdateRequest(Integer nextExpectedMsg) {
		this.offset = nextExpectedMsg;

		this.requestEntity = MultipartEntityBuilder.create();
		// New MIME type, data need to be UTF-8 encrypted
		ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), MIME.UTF8_CHARSET);

		// Set timout, limit and offset
		requestEntity.addTextBody(KEY_MSG_UPDATE_TIMEOUT, TIMEOUT.toString(), contentType);
		requestEntity.addTextBody(KEY_MSG_UPDATE_LIMIT, LIMIT.toString(), contentType);
		requestEntity.addTextBody(KEY_MSG_UPDATE_OFFSET, offset.toString(), contentType);

	}

	public String getCommand() {
		return COMMAND;
	}

	public MultipartEntityBuilder getRequestEntity() {
		return this.requestEntity;
	}
}
