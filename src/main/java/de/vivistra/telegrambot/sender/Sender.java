package de.vivistra.telegrambot.sender;

import de.vivistra.telegrambot.client.Bot;
import de.vivistra.telegrambot.client.BotRequest;
import de.vivistra.telegrambot.model.message.Message;

public class Sender {
	public static final Bot bot = new Bot();
	public static BotRequest request;

	public static void send(Message message) {

		request = new BotRequest(message);

		bot.post(request);
	}
}
