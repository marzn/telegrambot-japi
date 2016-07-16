package de.vivistra.telegrambot.model.message;

import java.io.File;

import de.vivistra.telegrambot.model.Contact;

public class ContactMessage extends Message {

	private static final String COMMAND = "sendContact";
	public static final String JSON_KEY = "contact";

	/**
	 * Creates a message from String
	 * 
	 * @param recipient
	 * @param message
	 */
	public ContactMessage(Long recipient, Contact contact) {
		super(recipient, contact);
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
		return MessageType.CONTACT_MESSAGE;
	}

	/**
	 * Get message content.
	 * 
	 * @return
	 */
	@Override
	public Contact getMessage() {
		return (Contact) this.message;
	}
}
