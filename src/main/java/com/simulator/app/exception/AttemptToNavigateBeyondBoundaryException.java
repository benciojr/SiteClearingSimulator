package com.simulator.app.exception;

public class AttemptToNavigateBeyondBoundaryException extends Exception {

	private static final long serialVersionUID = 1688060841017365384L;

	public AttemptToNavigateBeyondBoundaryException(String message) {
        super(message);
    }

}