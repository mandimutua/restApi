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
import com.ipo.entities.Permissions;
import com.ipo.services.PermissionService;
import com.ipo.services.UsersService;
import com.ipo.utils.ErrorUtl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/permission")
@Api(value = "Permission management", description = "Permission management API")
public class PermissionController {

	@Autowired
	PermissionService pemService;
	@Autowired
	UsersService userService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Creates a batch", notes = "Creating Permssion")
	public RestResponse create(@RequestBody RestRequestObject<Permissions> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "createbatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(pemService.create(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Edit  Batch", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse edit(@RequestBody RestRequestObject<Permissions> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "editbatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(pemService.edit(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Approve  Permission", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse approve(@RequestBody RestRequestObject<Permissions[]> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approvePem");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(pemService.approve(req), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Reject  Permission", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse reject(@RequestBody RestRequestObject<Permissions[]> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approvebatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(pemService.reject(req), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/listall", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Edit  Batch", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse list(@RequestBody RestRequestObject<Permissions> req, HttpServletRequest request, Pageable page,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "editbatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(pemService.listall(req.getObject(), page), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

}
