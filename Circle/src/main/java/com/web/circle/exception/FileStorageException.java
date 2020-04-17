package com.web.circle.exception;

/**
 * File meta data
 * 
 * @author jr April 2020
 * */
public class FileStorageException extends Exception {
	
	public FileStorageException() {
		super();
	}
	
	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
