package com.planet.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourcesNotFoundException extends RuntimeException {
	public ResourcesNotFoundException() {
		super();
	}
}
