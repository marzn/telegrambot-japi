package de.vivistra.telegrambot.model;

import org.json.JSONObject;

import de.vivistra.telegrambot.utils.Assert;
import de.vivistra.telegrambot.utils.Tester;

/**
 * This class represents a Telegram user or bot
 */
public class User {
	// Unique identifier for this user or bot
	private Integer id;
	// User's or bot's first name
	private String firstName;
	// Optional. User's or bot's last name
	private String lastName;
	// Optional. User's or bot's username
	private String userName;

	/**
	 * Constructor for a user or bot object
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param userName
	 */
	public User(Integer id, String firstName, String lastName, String userName) {
		Assert.notNull(id);
		Assert.notEmpty(firstName);

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
	}

	/**
	 * Create a user or bot object from a JSON String
	 * 
	 * @param userObject
	 * @return User
	 */
	public static User fromJSON(JSONObject userObject) {
		//
		Integer id = userObject.getInt("id");
		String firstName = userObject.getString("first_name");

		// Optional
		String lastName = userObject.optString("last_name");
		String userName = userObject.optString("username");

		return new User(id, firstName, lastName, userName);
	}

	/**
	 * Get user or bot id
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Get users or bots first name
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Get users or bots last name
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Get users or bots username
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * The object as a String
	 */
	public String toString() {
		return firstName + (!Tester.isEmpty(lastName) ? " " + lastName : "") + " ["
				+ (!Tester.isEmpty(userName) ? userName + "@" : "") + id + "]";
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
		result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
		result = prime * result + ((this.userName == null) ? 0 : this.userName.hashCode());
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
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (this.firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!this.firstName.equals(other.firstName)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!this.lastName.equals(other.lastName)) {
			return false;
		}
		if (this.userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!this.userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

}
