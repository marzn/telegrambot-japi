# TelegramBot JavaAPI

## Project status

This software is still under development and need some more time to be finished. But it is already working nice, so we want to share the software with you. If you want, feel free to contribute.

Stay updated with our [changelog](https://github.com/marzn/telegrambot-japi/blob/master/CHANGELOG.md).

## HowTo get the software

### Clone the repository (recommended)

```sh
git clone https://github.com/marzn/telegrambot-japi.git
```

### Just download a jar

Have a look at our [builds](https://github.com/marzn/telegrambot-japi/tree/master/builds).

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
	GetLosung getMessage = new GetLosung();

	// Subscribe the receiver
	Receiver.subscribe(getMessage);
}
```

And for sure you can combine these two code examples, this was only how to get started.
