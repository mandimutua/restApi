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
import com.ipo.entities.Application;
import com.ipo.entities.Customers;
import com.ipo.entities.Payments;
import com.ipo.services.PaymentService;
import com.ipo.services.UsersService;
import com.ipo.utils.ErrorUtl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/payment")
@Api(value = "Payment management", description = "Payment management API")
public class PaymentController {

	@Autowired
	UsersService userService;

	@Autowired
	PaymentService paymentService;

	@RequestMapping(value = "/listall", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Payment list", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse list(@RequestBody RestRequestObject<Application> req, HttpServletRequest request,
			Pageable pageable, HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "list_payments");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(paymentService.listall(req.getObject(), pageable), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Creates Payments", notes = "Creating Payments")
	public RestResponse create(@RequestBody RestRequestObject<Payments> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "createpayments");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(paymentService.create(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Edit  Payment", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse edit(@RequestBody RestRequestObject<Payments> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "editpayment");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(paymentService.edit(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Reject  Payments", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse reject(@RequestBody RestRequestObject<Payments[]> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approvebatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(paymentService.reject(req), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Approve  Payment", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse approve(@RequestBody RestRequestObject<Payments[]> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approvePayment");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(paymentService.approve(req), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Search Payments", notes = "Search Payments")
public RestResponse search(@RequestBody RestRequestObject<Payments> req,Pageable pageable,HttpServletRequest request,
	HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "createpayments");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(paymentService.search(req.getObject(),pageable), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}
	
	@RequestMapping(value = "/searchPay", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Search  Payment", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponse searchApp(@RequestBody RestRequestObject<Customers> req, HttpServletRequest request,
	Pageable pageable, HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "search_batch");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(paymentService.searchPay(req.getObject(),pageable), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}
	
	@RequestMapping(value = "/searchPayCode", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Search  Batch", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponse search(@RequestBody RestRequestObject<Payments> req, HttpServletRequest request, Pageable pageable,
	HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "search_batch");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(paymentService.searchPayments(req.getObject()),HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}

}
