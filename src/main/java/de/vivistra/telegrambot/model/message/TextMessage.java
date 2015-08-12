package de.vivistra.telegrambot.model.message;

public class TextMessage extends Message {

	private static final String COMMAND = "sendMessage";
	public static final String JSON_KEY = "text";

	/**
	 * Creates a message
	 * 
	 * @param recipient
	 * @param message
	 */
	public TextMessage(Integer recipient, String message) {
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
		return MessageType.TEXT_MESSAGE;
	}

	/**
	 * Get message content.
	 * 
	 * @return
	 */
	@Override
	public String getMessage() {
		return (String) this.message;
	}
}
