package de.vivistra.telegrambot.utils;

public class Tester {
	public static boolean isNull(Object value) {
		return (value == null);
	}

	public static boolean isEmpty(String value) {

		return (isNull(value) || value.isEmpty());
	}
}
