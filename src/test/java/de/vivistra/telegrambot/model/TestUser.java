package de.vivistra.telegrambot.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUser {

	@Test
	public void testConstructorAndGetter() {
		User peter = new User(1, "Peter", "Mueller", "PeMu");

		assertTrue(1 == peter.getId());
		assertEquals("Peter", peter.getFirstName());
		assertEquals("Mueller", peter.getLastName());
		assertEquals("PeMu", peter.getUserName());

		User bot = new User(-1, "Bot", null, null);

		assertTrue(-1 == bot.getId());
		assertEquals("Bot", bot.getFirstName());
		assertNull(bot.getLastName());
		assertNull(bot.getUserName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithIllegalFirstName() {
		new User(0, null, null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithIllegalId() {
		new User(null, "Bot", null, null);
	}

	@Test
	public void testToString() {
		User peter = new User(1, "Peter", "Mueller", "PeMu");

		assertEquals("Peter Mueller [PeMu@1]", peter.toString());

		User bot = new User(-1, "Bot", null, null);

		assertEquals("Bot [-1]", bot.toString());
	}
}
