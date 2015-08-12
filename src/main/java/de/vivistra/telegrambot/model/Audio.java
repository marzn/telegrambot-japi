package de.vivistra.telegrambot.model;

import org.json.JSONObject;

/**
 * Represents a audio message send trough Telegram
 */
public class Audio {

	// Unique file identifier
	private String fileId;
	// Duration of the audio in seconds as defined by sender
	private Integer duration;
	// Optional. MIME type of the file as defined by sender
	private String mimeType;
	// Optional. File size
	private Integer fileSize;

	/**
	 * Creates a audio object
	 * 
	 * @param fileId
	 * @param thumb
	 * @param fileName
	 * @param mimeType
	 * @param fileSize
	 */
	public Audio(String fileId, Integer duration, String mimeType, Integer fileSize) {
		this.fileId = fileId;
		this.duration = duration;
		this.mimeType = mimeType;
		this.fileSize = fileSize;
	}

	/**
	 * Create a audio object from a JSON String
	 * 
	 * @param audioObject
	 * @return Audio
	 */
	public static Audio fromJSON(JSONObject audioObject) {

		String fileId = audioObject.getString("file_id");
		Integer duration = audioObject.getInt("duration");

		// Optional
		String mimeType = audioObject.optString("mime_type");
		Integer fileSize = audioObject.optInt("file_size");

		return new Audio(fileId, duration, mimeType, fileSize);
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
		result = prime * result + ((this.duration == null) ? 0 : this.duration.hashCode());
		result = prime * result + ((this.fileId == null) ? 0 : this.fileId.hashCode());
		result = prime * result + ((this.fileSize == null) ? 0 : this.fileSize.hashCode());
		result = prime * result + ((this.mimeType == null) ? 0 : this.mimeType.hashCode());
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
		if (!(obj instanceof Audio)) {
			return false;
		}
		Audio other = (Audio) obj;
		if (this.duration == null) {
			if (other.duration != null) {
				return false;
			}
		} else if (!this.duration.equals(other.duration)) {
			return false;
		}
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
		if (this.mimeType == null) {
			if (other.mimeType != null) {
				return false;
			}
		} else if (!this.mimeType.equals(other.mimeType)) {
			return false;
		}
		return true;
	}

}
