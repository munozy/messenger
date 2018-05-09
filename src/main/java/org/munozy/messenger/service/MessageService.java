package org.munozy.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.munozy.messenger.model.Message;

public class MessageService {

	public List<Message> getAllMessages() {
		Message m1 =  new Message(1L, "Iai galera!", "MunozY");
		Message m2 =  new Message(1L, "Fala papito!", "MunozY");
		
		List<Message> messages = new ArrayList<>();
		messages.add(m1);
		messages.add(m2);
		
		return messages;
	}
}
