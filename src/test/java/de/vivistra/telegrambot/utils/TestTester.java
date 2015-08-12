package de.vivistra.telegrambot.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestTester {
	@Test
	public void testIsNull() {
		assertTrue(Tester.isNull(null));
		assertFalse(Tester.isNull(""));
		assertFalse(Tester.isNull(new Object()));
		assertFalse(Tester.isNull("Hallo"));
		assertFalse(Tester.isNull(new Long(1)));
		assertFalse(Tester.isNull(1));
		assertFalse(Tester.isNull(Long.MIN_VALUE));
	}

	@Test
	public void testIsEmpty() {
		assertTrue(Tester.isEmpty(null));
		assertTrue(Tester.isEmpty(""));
		assertFalse(Tester.isEmpty("Hallo"));
	}
}
