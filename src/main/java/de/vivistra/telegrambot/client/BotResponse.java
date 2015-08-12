package de.vivistra.telegrambot.client;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import de.vivistra.telegrambot.model.message.Message;
import de.vivistra.telegrambot.model.message.MessageFactory;

public class BotResponse {

	private int statusCode;
	private Header[] header;
	private JSONObject body;

	public BotResponse(int statusCode, Header[] header, String body) {
		this.statusCode = statusCode;
		this.header = header;
		this.body = new JSONObject(new JSONTokener(body));
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Header[] getHeader() {
		return header;
	}

	public void setHeader(Header[] header) {
		this.header = header;
	}

	public void setBody(String body) {
		this.body = new JSONObject(new JSONTokener(body));
	}

	public Message[] getMessages() {
		JSONArray resultArray = this.body.getJSONArray("result");
		int length = resultArray.length();

		Message[] messages = new Message[length];

		for (int i = 0; i < length; i++) {
			JSONObject result = resultArray.getJSONObject(i);

			messages[i] = MessageFactory.fromJSON(result.getJSONObject("message"));

			if (this.updateID < result.getInt("update_id")) {
				this.updateID = result.getInt("update_id");
			}
		}

		return messages;
	}

	private int updateID;

	public int getUpdateID() {
		return this.updateID;
	}

	public boolean getStatus() {
		return this.body.optBoolean("ok");
	}

}
