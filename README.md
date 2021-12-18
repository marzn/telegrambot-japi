# TelegramBot JavaAPI

![Build state](https://api.travis-ci.org/marzn/telegrambot-japi.svg "Build state")

## Project state

This software is **not maintained** anymore. Please consider to fork it and take care of it :)

Stay updated with our [changelog](https://github.com/marzn/telegrambot-japi/blob/master/CHANGELOG.md).

## HowTo get the software

### Clone the repository

```sh
git clone https://github.com/marzn/telegrambot-japi.git
```

## HowTo use the software

### Send a message

```java
import de.vivistra.telegrambot.model.message.Message;
import de.vivistra.telegrambot.model.message.TextMessage;
import de.vivistra.telegrambot.sender.Sender;
import de.vivistra.telegrambot.settings.BotSettings;

/**
 * This file launches the a telegram bot.
 */
public class Launcher {

	public static void main(String[] args) throws Exception {
		new Launcher();
	}

	private Launcher() throws Exception {

		// Set API token
		BotSettings.setApiToken("<Your TelegramAPI token here>");
		
		// A Telegram ID. It is a negative Integer for bots and a positive Integer for humans.
		int recipient = <Your TelegramID here>;
		
		// Create a message
		Message message = new TextMessage(recipient, "Hello =)");

		// Send the message
		Sender.send(message);
	}
}
```

### Receive a message

First you need to implement the `IReceiverService` interface.

```java
public class GetMessage implements IReceiverService {

	@Override
	public void received(Message message) {
		switch (message.getMessageType()) {
		case TEXT_MESSAGE:
			String sender = message.getSender().toString();

			String text = message.getMessage().toString();

			System.out.println(sender + " wrote: " + text);

			break;
		default:
			System.out.println("Ignore received message.");
		}
	}
}
```

Now we will create a object of our `GetMessage` and subscribe the `Receiver`. Our method `received` will be called every time a `Message` was received.

```java
private Launcher() throws Exception {

	// Set API token
	BotSettings.setApiToken("<Your TelegramAPI token here>");

	// Create an IReceiverService object
	GetMessage getMessage = new GetMessage();

	// Subscribe the receiver
	Receiver.subscribe(getMessage);
}
```

And for sure you can combine these two code examples, this was only how to get started.

## Projects using this software

[LosungsBot](https://github.com/dipdi/losungsbot)

Tell me, if you wanna have your project listed here =)
