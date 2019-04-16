package com.tx9.asparagus.advice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tx9.asparagus.exception.MealNotFoundException;

@ControllerAdvice
public class MealNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(MealNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String mealNotFoundHandler(MealNotFoundException ex) {
		return ex.getMessage();
	}
}
