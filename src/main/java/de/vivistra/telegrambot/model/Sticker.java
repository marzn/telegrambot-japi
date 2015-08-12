package de.vivistra.telegrambot.model;

import org.json.JSONObject;

public class Sticker {
	// Unique identifier for this file
	String fileId;
	// Sticker width
	Integer width;
	// Sticker height
	Integer height;
	// Optional. Sticker thumbnail in .webp or .jpg format
	PhotoSize thumb;
	// Optional. File size
	Integer fileSize;

	/**
	 * Creates a PhotoSize object
	 * 
	 * @param fileId
	 * @param width
	 * @param height
	 * @param fileSize
	 */
	public Sticker(String fileId, Integer width, Integer height, PhotoSize thumb, Integer fileSize) {
		super();
		this.fileId = fileId;
		this.width = width;
		this.height = height;
		this.thumb = thumb;
		this.fileSize = fileSize;
	}

	/**
	 * Create a user or bot object from a JSON String
	 * 
	 * @param userObject
	 * @return User
	 */
	public static Sticker fromJSON(JSONObject userObject) {

		String fileId = userObject.getString("file_id");
		Integer width = userObject.getInt("width");
		Integer height = userObject.getInt("height");

		// Optional
		PhotoSize thumb = PhotoSize.fromJSON(userObject.optJSONObject("thumb"));
		Integer fileSize = userObject.optInt("file_size");

		return new Sticker(fileId, width, height, thumb, fileSize);
	}

}
