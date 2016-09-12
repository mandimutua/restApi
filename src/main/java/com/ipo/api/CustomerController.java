package com.ipo.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponse;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Batch;

import com.ipo.entities.Customers;

import com.ipo.services.CustomerService;
import com.ipo.services.UsersService;
import com.ipo.utils.ErrorUtl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/customer")
@Api(value = "Customer management", description = "Customer management API")
public class CustomerController {

	@Autowired
	UsersService userService;

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Creates Customers", notes = "Creating Customer")
	public RestResponse create(@RequestBody RestRequestObject<Customers> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "create_customer");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(customerService.create(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Edit  Customer", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse edit(@RequestBody RestRequestObject<Customers> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "edit_customer");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(customerService.edit(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/listall", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Customers list", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse list(@RequestBody RestRequestObject<Batch> req, HttpServletRequest request,
			Pageable pageable, HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "list_batch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(customerService.listall(req.getObject(), pageable), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}
	
	   @RequestMapping(value = "/custsearch", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Search Customers", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse search(@RequestBody RestRequestObject<Customers> req, HttpServletRequest request, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "SearchCustomers");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(customerService.listCustomers(req.getObject()), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }

	@RequestMapping(value = "/approve", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Approve  Customer", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse approve(@RequestBody RestRequestObject<Customers[]> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approveCustomer");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(customerService.approve(req), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Reject  Customer", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse reject(@RequestBody RestRequestObject<Customers[]> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approvebatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(customerService.reject(req), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Search  Customer", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse search(@RequestBody RestRequestObject<Customers> req, HttpServletRequest request,
			Pageable pageable, HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "search_customer");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(customerService.search(req.getObject(), pageable), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

}
