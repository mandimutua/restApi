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
import com.ipo.entities.Roles;
import com.ipo.entities.UserRoles;
import com.ipo.services.RolesService;
import com.ipo.services.UsersService;
import com.ipo.utils.ErrorUtl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/roles")
@Api(value = "Role management", description = "Role management API")
public class RolesController {

	@Autowired
	RolesService roleService;

	@Autowired
	UsersService userService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Creates a role", notes = "Creating Role")
	public RestResponse create(@RequestBody RestRequestObject<Roles> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "createRole");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(roleService.create(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Edit  User Role", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse edit(@RequestBody RestRequestObject<Roles> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "editbatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(roleService.edit(req.getObject()), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Approve  User Role", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse approve(@RequestBody RestRequestObject<Roles[]> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approvebatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(roleService.approve(req), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Reject  User Roles", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse reject(@RequestBody RestRequestObject<Roles[]> req, HttpServletRequest request,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approvebatch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(roleService.reject(req), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

	@RequestMapping(value = "/listall", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Search Roles", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse search(@RequestBody RestRequestObject<Roles> req, HttpServletRequest request,Pageable page,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "SearchRoles");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(roleService.listRoles(req.getObject(),page), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}
	
	
	@RequestMapping(value = "/listallRoles", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Search Roles", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponse listAllRoles(@RequestBody RestRequestObject<Roles> req, HttpServletRequest request,Pageable page,
	HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "SearchRoles");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(roleService.listAllRoles(req.getObject()), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}


	@RequestMapping(value = "/getRoles", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Get Roles for a user", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse list(@RequestBody RestRequestObject<UserRoles> req, HttpServletRequest request,
			Pageable pageable, HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "list_batch");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(roleService.listUserRoles(req.getObject(), pageable), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}

}
