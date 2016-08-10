package com.ipo.utils;

import org.springframework.http.HttpStatus;

import com.ipo.elements.RestResponse;
import com.ipo.elements.RestResponseObject;



public class ErrorUtl {
	 public static RestResponse getFailedMsg(){
	        RestResponse resp = null;
	        RestResponseObject r = new RestResponseObject();
	        r.setRequestStatus(false);
	        r.setMessage("Access denied");
	         resp = new RestResponse(r, HttpStatus.OK);
	        return resp;

	    }

}
