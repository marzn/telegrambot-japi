package de.vivistra.telegrambot.model.message;

import java.io.File;

import de.vivistra.telegrambot.model.Audio;

public class AudioMessage extends Message {

	private static final String COMMAND = "sendAudio";
	public static final String JSON_KEY = "audio";

	/**
	 * Creates a message
	 * 
	 * @param recipient
	 * @param message
	 */
	public AudioMessage(Integer recipient, File message) {
		super(recipient, message);
	}

	/**
	 * Creates a message
	 * 
	 * @param recipient
	 * @param message
	 */
	public AudioMessage(Integer recipient, Audio message) {
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
		return MessageType.AUDIO_MESSAGE;
	}

	/**
	 * Get message content.
	 * 
	 * @return
	 */
	@Override
	public Audio getMessage() {
		return (Audio) this.message;
	}
}
