package com.blog.newsangblog2.common.exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
