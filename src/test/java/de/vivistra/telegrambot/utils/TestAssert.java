package de.vivistra.telegrambot.utils;

import org.junit.Test;

public class TestAssert {
	@Test(expected = IllegalArgumentException.class)
	public void testNotNullException() {
		Assert.notNull(null);
	}

	@Test
	public void testNotNull() {
		Assert.notNull(new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNotEmptyExceptionNull() {
		Assert.notEmpty(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNotEmptyExceptionEmpty() {
		Assert.notEmpty("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNotEmptyExceptionEmpty2() {
		Assert.notEmpty(new String());
	}

	@Test
	public void testNotEmpty() {
		Assert.notNull("Hi");
	}
}
