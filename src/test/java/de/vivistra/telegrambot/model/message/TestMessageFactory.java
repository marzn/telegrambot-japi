package de.vivistra.telegrambot.model.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;

import de.vivistra.telegrambot.model.Audio;
import de.vivistra.telegrambot.model.Document;
import de.vivistra.telegrambot.model.PhotoSize;
import de.vivistra.telegrambot.model.User;

public class TestMessageFactory {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * A helper method to load a message from a resource json-file
	 * 
	 * @param filename
	 *            the filename, for example "TextMessage.json"
	 * @return the JsonObject created from the message file
	 */
	private JSONObject load(String filename) {

		String content = "";

		try {
			content = IOUtils.toString(this.getClass().getResourceAsStream("/messages/" + filename), "UTF-8");

		} catch (Exception e) {
			LOG.error("could not read file (" + filename + ")");
			assertTrue(false);
		}

		return new JSONObject(content);
	}

	/**
	 * Tests the factory method for text messages
	 */
	@Test
	public void testFromJSON() {

		Message message = MessageFactory.fromJSON(load("TextMessage.json"));

		assertFalse(message.isFromGroupChat());

		assertNull(message.getGroupChat());

		User sender = new User(7872355, "Marcel", "", "Marcn");

		assertEquals(sender, message.getSender());
	}

	/**
	 * Tests the factory method for text messages
	 */
	@Test
	public void testTextFromJSON() {

		Message message = MessageFactory.fromJSON(load("TextMessage.json"));

		assertTrue(message instanceof TextMessage);

		TextMessage textMessage = (TextMessage) message;

		assertEquals("hi", textMessage.getMessage());
	}

	/**
	 * Tests the factory method for image messages
	 */
	@Test
	public void testImageFromJSON() {

		Message message = MessageFactory.fromJSON(load("ImageMessage.json"));

		assertTrue(message instanceof ImageMessage);

		ImageMessage imageMessage = (ImageMessage) message;

		PhotoSize[] photoSize = {
				new PhotoSize("AgADBAAD_akxG2MfeAAB5hgiqZX29Kj4CGswAARyvIRKidWQpFhiAAIC", 90, 27, 789),
				new PhotoSize("AgADBAAD_akxG2MfeAAB5hgiqZX29Kj4CGswAASfc9iAh4qMpVdiAAIC", 209, 62, 2682) };

		PhotoSize[] messageContent = imageMessage.getPhotoSize();

		assertNotNull(messageContent);
		assertEquals(photoSize.length, messageContent.length);

		for (int i = 0; i < messageContent.length; i++) {
			assertEquals(photoSize[i], messageContent[i]);
		}
	}

	/**
	 * Tests the factory method for audio messages
	 */
	@Test
	public void testAudioFromJSON() {

		Message message = MessageFactory.fromJSON(load("AudioMessage.json"));

		assertTrue(message instanceof AudioMessage);

		AudioMessage audioMessage = (AudioMessage) message;

		Audio audio = new Audio("AwADBAADawADYx94AAEBLoNiv82GrQI", 1, "audio/ogg", 487);

		Audio messageContent = audioMessage.getMessage();

		assertEquals(audio, messageContent);
	}

	// /**
	// * Tests the factory method for video messages
	// */
	// @Test
	// public void testVideoFromJSON() {
	//
	// Message message = MessageFactory.fromJSON(load("VideoMessage.json"));
	//
	// assertTrue(message instanceof VideoMessage);
	//
	// VideoMessage videoMessage = (VideoMessage) message;
	//
	// Video video = new Video("AwADBAADawADYx94AAEBLoNiv82GrQI", 1,
	// "audio/ogg", 487);
	//
	// Video messageContent = videoMessage.getMessage();
	//
	// assertEquals(video, messageContent);
	// }

	/**
	 * Tests the factory method for video messages
	 */
	@Test
	public void testDocumentFromJSON() {

		Message message = MessageFactory.fromJSON(load("DocumentMessage.json"));

		assertTrue(message instanceof DocumentMessage);

		DocumentMessage videoMessage = (DocumentMessage) message;

		Document video = new Document("BQADBAADbAADYx94AAGYue6ufheQlQI", null, "test.txt", "text/plain", 2);

		Document messageContent = videoMessage.getMessage();

		assertEquals(video, messageContent);
	}

	// Sticker

	// Contact

	// Location

	// Some other Message
}
