package de.vivistra.telegrambot.model;

import org.json.JSONObject;

import de.vivistra.telegrambot.utils.Assert;

/**
 * This class represents a GroupChat
 */
public class GroupChat {
	// Unique identifier for this group chat
	private Integer id;
	// Group name
	private String title;

	/**
	 * Creates a GroupChat object
	 * 
	 * @param id
	 * @param title
	 */
	public GroupChat(int id, String title) {
		Assert.notEmpty(title);

		this.id = id;
		this.title = title;
	}

	/**
	 * Create GroupChat from JSON
	 * 
	 * @param chatObject
	 * @return
	 */
	public static GroupChat fromJSON(JSONObject chatObject) {

		// If it is not a GroupChat JSON Object
		if (!chatObject.has("title")) {
			return null;
		}

		Integer id = chatObject.getInt("id");
		String title = chatObject.getString("title");

		return new GroupChat(id, title);
	}

	/**
	 * Get GroupChat id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get GroupChat title
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
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
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
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
		if (!(obj instanceof GroupChat)) {
			return false;
		}
		GroupChat other = (GroupChat) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!this.title.equals(other.title)) {
			return false;
		}
		return true;
	}

}
