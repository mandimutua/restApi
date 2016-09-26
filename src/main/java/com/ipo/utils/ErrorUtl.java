package com.ipo.utils;

import org.springframework.http.HttpStatus;

import com.ipo.elements.RestResponse;
import com.ipo.elements.RestResponseObject;
import com.ipo.elements.RestResponseReport;
import com.ipo.elements.RestResponseReportsObjects;



public class ErrorUtl {
	 public static RestResponse getFailedMsg(){
	        RestResponse resp = null;
	        RestResponseObject r = new RestResponseObject();
	        r.setRequestStatus(false);
	        r.setMessage("Access denied");
	         resp = new RestResponse(r, HttpStatus.OK);
	        return resp;

	    }
	 
	 public static RestResponseReport getFailedMsg1(){
		 RestResponseReport resp = null;
	        RestResponseReportsObjects r = new RestResponseReportsObjects();
	        r.setRequestStatus(false);
	        r.setMessage("Access denied");
	         resp = new RestResponseReport(r, HttpStatus.OK);
	        return resp;

	    }


}
