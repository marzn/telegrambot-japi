package de.vivistra.telegrambot.utils;

public class Assert {
	public static void notNull(Object value) {

		if (Tester.isNull(value)) {
			throw new IllegalArgumentException("Argument is NULL.");
		}
	}

	public static void notEmpty(String value) {

		if (Tester.isEmpty(value)) {
			throw new IllegalArgumentException("Argument is NULL or empty.");
		}
	}

}
