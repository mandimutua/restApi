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

import com.ipo.services.BatchService;
import com.ipo.services.UsersService;
import com.ipo.utils.ErrorUtl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/batch")
@Api(value = "Batch management", description = "Batch management API")
public class BatchContoller {

	@Autowired
	BatchService batchService;
	@Autowired
	UsersService userService;

	@RequestMapping(value = "/listall", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Batch list", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse list(@RequestBody RestRequestObject<Batch> req, HttpServletRequest request, Pageable pageable,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "list_batch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(batchService.listall(req.getObject(), pageable), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Creates a batch", notes = "Creating Batch")
	public RestResponse create(@RequestBody RestRequestObject<Batch> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "createbatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(batchService.create(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Edit  Batch", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse edit(@RequestBody RestRequestObject<Batch> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "editbatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(batchService.edit(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Approve  Batch", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse approve(@RequestBody RestRequestObject<Batch[]> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approvebatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(batchService.approve(req), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Reject  Batch", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse reject(@RequestBody RestRequestObject<Batch[]> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approvebatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(batchService.reject(req), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/batchlist", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Search Customers", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse search(@RequestBody RestRequestObject<Batch> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "SearchCustomers");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(batchService.searchbatch(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Search  Batch", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse search(@RequestBody RestRequestObject<Batch> req, HttpServletRequest request, Pageable pageable,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "search_batch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(batchService.search(req.getObject(), pageable), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}
	
	@RequestMapping(value = "/searchApproved", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Search Approved Batch", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponse searchApproved(@RequestBody RestRequestObject<Batch> req, HttpServletRequest request, Pageable pageable,
	HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "search_batch");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(batchService.findApproved(req.getObject(), pageable), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}


}
