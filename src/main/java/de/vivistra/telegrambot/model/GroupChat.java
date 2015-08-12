package de.vivistra.telegrambot.model;

import org.json.JSONObject;

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
}
