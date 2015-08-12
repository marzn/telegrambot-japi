package de.vivistra.telegrambot.model;

import org.json.JSONObject;

/**
 * Represents for example a PDF send trough Telegram
 */
public class Document {
	// Unique file identifier
	private String fileId;
	// Optional. Document thumbnail as defined by sender
	private PhotoSize thumb;
	// Optional. Original filename as defined by sender
	private String fileName;
	// Optional. MIME type of the file as defined by sender
	private String mimeType;
	// Optional. File size
	private Integer fileSize;

	/**
	 * Creates a document object
	 * 
	 * @param fileId
	 * @param thumb
	 * @param fileName
	 * @param mimeType
	 * @param fileSize
	 */
	public Document(String fileId, PhotoSize thumb, String fileName, String mimeType, Integer fileSize) {
		this.fileId = fileId;
		this.thumb = thumb;
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.fileSize = fileSize;
	}

	/**
	 * Create a document object from a JSON String
	 * 
	 * @param documentObject
	 * @return Document
	 */
	public static Document fromJSON(JSONObject documentObject) {

		String fileId = documentObject.getString("file_id");

		// Optional
		JSONObject thumbJson = documentObject.optJSONObject("thumb");
		PhotoSize thumb = (thumbJson != null) ? PhotoSize.fromJSON(thumbJson) : null;

		String fileName = documentObject.optString("file_name");
		String mimeType = documentObject.optString("mime_type");
		Integer fileSize = documentObject.optInt("file_size");

		return new Document(fileId, thumb, fileName, mimeType, fileSize);
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
		result = prime * result + ((this.fileName == null) ? 0 : this.fileName.hashCode());
		result = prime * result + ((this.fileSize == null) ? 0 : this.fileSize.hashCode());
		result = prime * result + ((this.mimeType == null) ? 0 : this.mimeType.hashCode());
		result = prime * result + ((this.thumb == null) ? 0 : this.thumb.hashCode());
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
		if (!(obj instanceof Document)) {
			return false;
		}
		Document other = (Document) obj;
		if (this.fileId == null) {
			if (other.fileId != null) {
				return false;
			}
		} else if (!this.fileId.equals(other.fileId)) {
			return false;
		}
		if (this.fileName == null) {
			if (other.fileName != null) {
				return false;
			}
		} else if (!this.fileName.equals(other.fileName)) {
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
		if (this.thumb == null) {
			if (other.thumb != null) {
				return false;
			}
		} else if (!this.thumb.equals(other.thumb)) {
			return false;
		}
		return true;
	}

}
