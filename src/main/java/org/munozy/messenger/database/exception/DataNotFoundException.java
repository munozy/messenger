package org.munozy.messenger.database.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5656567676L;

	
	public DataNotFoundException(String message) {
		super(message);
	}
	
}
