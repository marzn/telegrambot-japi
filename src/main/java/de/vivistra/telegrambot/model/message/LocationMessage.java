package de.vivistra.telegrambot.model.message;

import java.io.File;

import de.vivistra.telegrambot.model.Location;

public class LocationMessage extends Message {

	private static final String COMMAND = "sendLocation";
	public static final String JSON_KEY = "location";


	/**
	 * Creates a message from String
	 * 
	 * @param recipient
	 * @param message
	 */
	public LocationMessage(Long recipient, Location message) {
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
		return MessageType.LOCATION_MESSAGE;
	}

	/**
	 * Get message content.
	 * 
	 * @return
	 */
	@Override
	public Location getMessage() {
		return (Location) this.message;
	}
}
