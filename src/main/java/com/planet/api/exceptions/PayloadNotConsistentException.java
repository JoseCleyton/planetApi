package com.planet.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PayloadNotConsistentException extends RuntimeException {
	public PayloadNotConsistentException() {
		super("Payload not Consistent");
	}
}
