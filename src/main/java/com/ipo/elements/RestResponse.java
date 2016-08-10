package com.ipo.elements;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ipo.elements.RestResponseObject;

public class RestResponse extends ResponseEntity<RestResponseObject> {
	public RestResponse(RestResponseObject body, HttpStatus statusCode) {
		super(body, statusCode);
	}
}
