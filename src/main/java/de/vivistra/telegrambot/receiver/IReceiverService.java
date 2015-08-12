package de.vivistra.telegrambot.receiver;

import de.vivistra.telegrambot.model.message.Message;

public interface IReceiverService {
	public void received(Message message);
}
