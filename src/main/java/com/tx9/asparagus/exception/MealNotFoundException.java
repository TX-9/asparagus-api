package com.tx9.asparagus.exception;

public class MealNotFoundException extends RuntimeException {

	public MealNotFoundException(Long id) {
		super("cannot find meal " + id);
	}

}
