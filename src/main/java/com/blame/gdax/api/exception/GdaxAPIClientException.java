package com.blame.gdax.api.exception;

public class GdaxAPIClientException extends Exception {

	private static final long serialVersionUID = 1414203715474069772L;

	public GdaxAPIClientException(Exception e) {
		super(e);
	}

}
