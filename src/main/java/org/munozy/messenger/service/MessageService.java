package org.munozy.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.munozy.messenger.database.DatabaseClass;
import org.munozy.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1, "Iai galera!", "MunozY"));
		messages.put(2L, new Message(2, "Fala papito!", "MunozY"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new RuntimeException("Message with id " + id + " not found");
		}
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
