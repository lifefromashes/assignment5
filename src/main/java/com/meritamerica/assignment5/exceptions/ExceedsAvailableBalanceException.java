package com.meritamerica.assignment5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceedsAvailableBalanceException extends Exception {

	//returns 404 if exception is thrown

	public ExceedsAvailableBalanceException(String msg) {

		super(msg);
	}
}
