package de.vivistra.telegrambot.model.message;

public class UnhandledMessage extends Message {

	/**
	 * Creates a message
	 * 
	 * @param recipient
	 * @param message
	 */
	public UnhandledMessage(Object message) {
		super(0, message);
	}

	/**
	 * Get command for the request. For all available methods see
	 * https://core.telegram.org/bots/api#available-methods
	 * 
	 * @return
	 */
	@Override
	public String getCommand() {
		return null;
	}

	/**
	 * Get the JSON key for this type of message. For all available keys see
	 * https://core.telegram.org/bots/api#message
	 * 
	 * @return
	 */
	@Override
	public String getJsonKey() {
		return null;
	}

	/**
	 * Returns the message type of a message
	 * 
	 * @return
	 */
	@Override
	public MessageType getMessageType() {
		return MessageType.UNHANDLED_MESSAGE;
	}

	/**
	 * Get message content.
	 * 
	 * @return
	 */
	@Override
	public Object getMessage() {
		return this.message;
	}
}
