package edu.pnu.exception;

public class IDExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public IDExistException(String message) {
		super(message);
	}
}
