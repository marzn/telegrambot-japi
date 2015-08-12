package de.vivistra.telegrambot.model.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import de.vivistra.telegrambot.model.*;

public class MessageFactory {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Factory for Messages create them from JSON
	 * 
	 * @param messageObject
	 * @return
	 */
	public static Message fromJSON(JSONObject messageObject) {

		Integer recipient = null;
		Message message = null;

		// Text
		if (messageObject.keySet().contains(TextMessage.JSON_KEY)) {
			message = new TextMessage(recipient, messageObject.getString(TextMessage.JSON_KEY));
		}

		// Image
		else if (messageObject.keySet().contains(ImageMessage.JSON_KEY)) {

			JSONArray photoArray = messageObject.getJSONArray(ImageMessage.JSON_KEY);

			PhotoSize[] imageArray = new PhotoSize[photoArray.length()];

			for (int i = 0; i < photoArray.length(); i++) {
				imageArray[i] = PhotoSize.fromJSON(photoArray.getJSONObject(i));
			}

			message = new ImageMessage(recipient, imageArray);
		}

		// Audio
		else if (messageObject.keySet().contains(AudioMessage.JSON_KEY)) {
			Audio audio = Audio.fromJSON(messageObject.getJSONObject(AudioMessage.JSON_KEY));
			message = new AudioMessage(recipient, audio);
		}

		// Video
		// else if (messageObject.keySet().contains("video")) {
		// message = new VideoMessage(recipient,
		// messageObject.getString("video"));
		// }

		// Document
		else if (messageObject.keySet().contains(DocumentMessage.JSON_KEY)) {
			Document document = Document.fromJSON(messageObject.getJSONObject(DocumentMessage.JSON_KEY));
			message = new DocumentMessage(recipient, document);
		}

		// Sticker
		else if (messageObject.keySet().contains(StickerMessage.JSON_KEY)) {
			Sticker sticker = Sticker.fromJSON(messageObject.getJSONObject(StickerMessage.JSON_KEY));
			message = new StickerMessage(recipient, sticker);
		}

		// Contact
		// else if (messageObject.keySet().contains("contact")) {
		// message = new ContactMessage(recipient,
		// messageObject.getString("contact"));
		// }

		// Location
		// else if (messageObject.keySet().contains("location")) {
		// message = new LocationMessage(recipient,
		// messageObject.getString("location"));
		// }

		// Some other Message
		else {
			LOG.debug("Some kind of unhandled message sent. This is can be a group creation or something similar.");
			return new UnhandledMessage(messageObject);
		}

		// Set some other attributes
		message.setSender(User.fromJSON(messageObject.getJSONObject("from")));
		message.setGroupChat(GroupChat.fromJSON(messageObject.getJSONObject("chat")));
		message.setMessageID(messageObject.getInt("message_id"));
		message.setDate(messageObject.getInt("date"));

		return message;
	}
}
