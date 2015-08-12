package de.vivistra.telegrambot.model;

import org.json.JSONObject;

/**
 * This object represents one size of a photo or a file / sticker thumbnail.
 */
public class PhotoSize {
	// Unique identifier for this file
	private String fileId;
	// Photo width
	private Integer width;
	// Photo height
	private Integer height;
	// Optional. File size
	private Integer fileSize;

	/**
	 * Creates a PhotoSize object
	 * 
	 * @param fileId
	 * @param width
	 * @param height
	 * @param fileSize
	 */
	public PhotoSize(String fileId, Integer width, Integer height, Integer fileSize) {
		super();
		this.fileId = fileId;
		this.width = width;
		this.height = height;
		this.fileSize = fileSize;
	}

	/**
	 * Create a user or bot object from a JSON String
	 * 
	 * @param userObject
	 * @return User
	 */
	public static PhotoSize fromJSON(JSONObject userObject) {

		String fileId = userObject.getString("file_id");
		Integer width = userObject.getInt("width");
		Integer height = userObject.getInt("height");

		// Optional
		Integer fileSize = userObject.optInt("file_size");

		return new PhotoSize(fileId, width, height, fileSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.fileId == null) ? 0 : this.fileId.hashCode());
		result = prime * result + ((this.fileSize == null) ? 0 : this.fileSize.hashCode());
		result = prime * result + ((this.height == null) ? 0 : this.height.hashCode());
		result = prime * result + ((this.width == null) ? 0 : this.width.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PhotoSize)) {
			return false;
		}
		PhotoSize other = (PhotoSize) obj;
		if (this.fileId == null) {
			if (other.fileId != null) {
				return false;
			}
		} else if (!this.fileId.equals(other.fileId)) {
			return false;
		}
		if (this.fileSize == null) {
			if (other.fileSize != null) {
				return false;
			}
		} else if (!this.fileSize.equals(other.fileSize)) {
			return false;
		}
		if (this.height == null) {
			if (other.height != null) {
				return false;
			}
		} else if (!this.height.equals(other.height)) {
			return false;
		}
		if (this.width == null) {
			if (other.width != null) {
				return false;
			}
		} else if (!this.width.equals(other.width)) {
			return false;
		}
		return true;
	}

}
