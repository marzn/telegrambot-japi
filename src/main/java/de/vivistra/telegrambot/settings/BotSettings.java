package de.vivistra.telegrambot.settings;

public class BotSettings {

	private static String apiUrl = "https://api.telegram.org/bot";
	private static String apiToken = "";

	public static String getApiUrlWithToken() {
		return apiUrl + apiToken + "/";
	}

	public static boolean isEmptyApiToken() {
		return apiToken.isEmpty();
	}

	public static void setApiUrl(String url) {
		apiUrl = url;
	}

	public static void setApiToken(String token) {
		apiToken = token;
	}

}
