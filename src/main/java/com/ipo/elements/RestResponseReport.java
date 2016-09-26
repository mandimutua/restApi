package com.ipo.elements;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponseReport extends ResponseEntity<RestResponseReportsObjects> {
	public RestResponseReport(RestResponseReportsObjects body, HttpStatus statusCode) {
		super(body, statusCode);
	}
}


