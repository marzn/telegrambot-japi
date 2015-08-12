package de.vivistra.telegrambot.model.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.vivistra.telegrambot.model.GroupChat;
import de.vivistra.telegrambot.model.User;

public abstract class TestMessage {
	private static final Logger LOG = LogManager.getLogger();

	// The recipient of the message
	protected Integer recipient;
	// The content of the message
	protected Object message;
	// Type of the message
	protected MessageType messageType;
	// Sender of the message
	protected User sender;
	// GroupChat the message was sent in. Use sender if groupChat is null.
	protected GroupChat groupChat;
	// Unique message identifier
	protected Integer messageID;
	// Date the message was sent in Unix time
	protected Integer date;
	// Optional. For forwarded messages, sender of the original message
	protected String forwardFrom;
	// Optional. For forwarded messages, date the original message was sent in
	// Unix time
	protected Integer forwardDate;
	// Optional. For replies, the original message. Note that the Message object
	// in this field will not contain further reply_to_message fields even if it
	// itself is a reply.
	protected Message replyToMessage;

	/**
	 * Construct a message
	 * 
	 * @param recipient
	 * @param message
	 */
	public TestMessage(Integer recipient, Object message) {
		this.recipient = recipient;
		this.message = message;
	}

	/**
	 * Get command for the request. For all available methods see
	 * https://core.telegram.org/bots/api#available-methods
	 */
	public abstract String getCommand();

	/**
	 * Get the JSON key for this type of message. For all available keys see
	 * https://core.telegram.org/bots/api#message
	 */
	public abstract String getJsonKey();

	/**
	 * Returns the message type of a message
	 * 
	 * @return
	 */
	public abstract MessageType getMessageType();

	/**
	 * Get the recipient of the message. Is null if the bot is the recipient and
	 * received the message.
	 * 
	 * @return
	 */
	public Integer getRecipient() {
		return this.recipient;
	}

	/**
	 * Get message content.
	 * 
	 * @return
	 */
	public abstract Object getMessage();

	/**
	 * Set the sender of the message (used for received messages) not needed
	 * when sending messages.
	 * 
	 * @param sender
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}

	/**
	 * Get the sender of the message.
	 * 
	 * @return
	 */
	public User getSender() {
		return this.sender;
	}

	/**
	 * Set the messageId of the message (used for received messages) not needed
	 * when sending messages
	 * 
	 * @param messageID
	 */
	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}

	/**
	 * Set date of message (Unix timestamp)
	 * 
	 * @param timestamp
	 */
	public void setDate(Integer timestamp) {
		this.date = timestamp;
	}

	/**
	 * Set the GroupChat object
	 * 
	 * @param chat
	 */
	public void setGroupChat(GroupChat chat) {
		this.groupChat = chat;
	}

	/**
	 * Get the GroupChat object
	 * 
	 * @return
	 */
	public GroupChat getGroupChat() {
		return this.groupChat;
	}

	/**
	 * Was this message received from a group chat?
	 * 
	 * @return
	 */
	public boolean isFromGroupChat() {
		return this.groupChat != null;
	}
}
