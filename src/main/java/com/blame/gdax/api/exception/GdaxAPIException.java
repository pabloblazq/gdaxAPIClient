package com.blame.gdax.api.exception;

public class GdaxAPIException extends Exception {

	private static final long serialVersionUID = 1414203715474069772L;

	public GdaxAPIException(Exception e) {
		super(e);
	}

}
