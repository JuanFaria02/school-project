package com.schoolproject.schoolproject.services.exceptions;

public class DatabaseException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	public DatabaseException(String mensage) {
		super(mensage);
	}
	
}
