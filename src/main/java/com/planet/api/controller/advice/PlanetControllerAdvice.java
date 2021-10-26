package com.planet.api.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.planet.api.exceptions.ArgumentNotValidException;
import com.planet.api.exceptions.PayloadNotConsistentException;
import com.planet.api.exceptions.ResourceAlreadyExistsException;
import com.planet.api.exceptions.ResourcesNotFoundException;

@ControllerAdvice
public class PlanetControllerAdvice {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleException(Exception ex) {
		return "Internal Server Error: " + ex.getMessage();
	}

	@ExceptionHandler(ResourcesNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleResourcesNotFoundException(ResourcesNotFoundException ex) {
		return "Resource not found";
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleResourceExistsException(ResourceAlreadyExistsException ex) {
		return "Resource Already Exists";
	}
	
	@ExceptionHandler(PayloadNotConsistentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handlePayloadNotConsistentException(PayloadNotConsistentException ex) {
		return "Payload not Consistent";
	}
}
