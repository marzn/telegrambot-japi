package de.vivistra.telegrambot.model;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

public class TestGroupChat {

	@Test
	public void testConstructorAndGetter() {
		GroupChat chat = new GroupChat(12345, "Name");

		assertTrue(12345 == chat.getId());
		assertEquals("Name", chat.getTitle());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithNullTitle() {
		new GroupChat(0, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithEmptyTitle() {
		new GroupChat(0, "");
	}

	@Test
	public void testFromJSON() {

		JSONObject json = new JSONObject("{\"id\":12345,\"first_name\":\"User\"}");

		assertNull(GroupChat.fromJSON(json));

		json = new JSONObject("{\"id\":12345,\"title\":\"Group title\"}");

		GroupChat chat = GroupChat.fromJSON(json);

		assertNotNull(chat);

		assertEquals(new GroupChat(12345, "Group title"), chat);

	}
}
