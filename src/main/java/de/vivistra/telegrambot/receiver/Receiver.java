package de.vivistra.telegrambot.receiver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.vivistra.telegrambot.client.Bot;
import de.vivistra.telegrambot.client.BotRequest;
import de.vivistra.telegrambot.client.BotResponse;
import de.vivistra.telegrambot.model.message.Message;
import de.vivistra.telegrambot.model.message.MessageType;
import de.vivistra.telegrambot.settings.BotSettings;

public class Receiver {

	private static final Logger LOG = LogManager.getLogger();

	private static final Queue<Message> messageQueue = new LinkedList<>();

	private static final Set<IReceiverService> receiverServices = new HashSet<>();

	private static final ReceiverThread receiver = new ReceiverThread();

	private static void notifyServices(Message message) {
		for (IReceiverService service : receiverServices) {
			service.received(message);
		}
	}

	public static void subscribe(IReceiverService service) {
		receiverServices.add(service);

		if (!receiver.isAlive()) {
			receiver.start();
		}
	}

	public static void unsubscribe(IReceiverService service) {
		receiverServices.remove(service);

		if (receiverServices.isEmpty()) {
			receiver.interrupt();
		}
	}

	private static class ReceiverThread extends Thread {

		@Override
		public void run() {

			if (BotSettings.isEmptyApiToken()) {
				LOG.error("API token is not set. Plase use BotSettings.setApiToken(<Your bots API token>);");
				return;
			}

			Bot bot = new Bot();

			UpdateRequest updateRequest;
			BotResponse botResponse;

			int nextExpectedMsg = 0;

			while (!isInterrupted()) {
				updateRequest = new UpdateRequest(nextExpectedMsg++);

				botResponse = bot.post(new BotRequest(updateRequest));

				switch (botResponse.getStatusCode()) {
				case 200:
					for (Message message : botResponse.getMessages()) {

						if (message.getMessageType() == MessageType.UNHANDLED_MESSAGE) {
							continue;
						}

						LOG.info(message.getMessage());

						messageQueue.add(message);

						notifyServices(message);
					}

					nextExpectedMsg = botResponse.getUpdateID() + 1;

					break;
				case 409:
					LOG.error("There is already a bot with this token connected");
					System.exit(1);
					break;
				default:
					LOG.error("Unknown answer, request failed.");
					break;
				}
			}

			bot.close();
		}
	}
}
