package de.vivistra.telegrambot.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import de.vivistra.telegrambot.receiver.UpdateRequest;

public class TestBotRequest {

	@Test
	public void testUpdateRequest() {

		UpdateRequest updateRequest = Mockito.mock(UpdateRequest.class);

		when(updateRequest.getCommand()).thenReturn("getUpdates");

		MultipartEntityBuilder requestEntity = MultipartEntityBuilder.create();
		// New MIME type, data need to be UTF-8 encrypted
		ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), MIME.UTF8_CHARSET);

		// Set timout, limit and offset
		requestEntity.addTextBody("timeout", "60", contentType);
		requestEntity.addTextBody("limit", "100", contentType);
		requestEntity.addTextBody("offset", "1337", contentType);

		when(updateRequest.getRequestEntity()).thenReturn(requestEntity);

		BotRequest request = new BotRequest(updateRequest);
		assertEquals("getUpdates", request.getCommand());
		assertEquals(requestEntity, request.getContent());
	}

	@Test
	public void testSetterAndGetter() {
		UpdateRequest updateRequest = Mockito.mock(UpdateRequest.class);
		when(updateRequest.getCommand()).thenReturn(null);
		when(updateRequest.getCommand()).thenReturn(null);

		BotRequest request = new BotRequest(updateRequest);

		assertNull(request.getCommand());
		assertNull(request.getContent());

		String command = "readySteadyGo";
		request.setCommand(command);

		assertNotNull(request.getCommand());
		assertEquals(command, request.getCommand());

		MultipartEntityBuilder content = Mockito.mock(MultipartEntityBuilder.class);

		request.setContent(content);

		assertNotNull(request.getContent());
		assertEquals(content, request.getContent());
	}
}