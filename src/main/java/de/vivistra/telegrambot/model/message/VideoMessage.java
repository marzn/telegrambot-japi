package de.vivistra.telegrambot.model.message;

import java.io.File;

public class VideoMessage extends Message {

	private static final String COMMAND = "sendVideo";
	public static final String JSON_KEY = "video";

	/**
	 * Creates a message
	 * 
	 * @param recipient
	 * @param message
	 */
	public VideoMessage(Integer recipient, File message) {
		super(recipient, message);
	}

	/**
	 * Creates a message from String
	 * 
	 * @param recipient
	 * @param message
	 */
	public VideoMessage(Integer recipient, String message) {
		super(recipient, message);
	}

	/**
	 * Get command for the request. For all available methods see
	 * https://core.telegram.org/bots/api#available-methods
	 * 
	 * @return
	 */
	@Override
	public String getCommand() {
		return COMMAND;
	}

	/**
	 * Get the JSON key for this type of message. For all available keys see
	 * https://core.telegram.org/bots/api#message
	 * 
	 * @return
	 */
	@Override
	public String getJsonKey() {
		return JSON_KEY;
	}

	/**
	 * Returns the message type of a message
	 * 
	 * @return
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.IMAGE_MESSAGE;
	}

	/**
	 * Get message content.
	 * 
	 * @return
	 */
	@Override
	public File getMessage() {
		return (File) this.message;
	}
}
