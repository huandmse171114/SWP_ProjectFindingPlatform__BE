package com.findhub.finhubbackend.exception;

public class EmailNotFoundException extends Exception {

	public EmailNotFoundException() {
		super();
	}

	public EmailNotFoundException(
			String message,
			Throwable cause,
			boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmailNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailNotFoundException(String message) {
		super(message);
	}

	public EmailNotFoundException(Throwable cause) {
		super(cause);
	}

}
