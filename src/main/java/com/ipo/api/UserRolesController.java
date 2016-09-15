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
import com.ipo.entities.UserRoles;
import com.ipo.services.UserRoleService;
import com.ipo.services.UsersService;
import com.ipo.utils.ErrorUtl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/userRoles")
@Api(value = "User Roles management", description = "System Parameters API")
public class UserRolesController {

	@Autowired
	UsersService userService;
	
	@Autowired
	UserRoleService usrRoleService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Creates Params", notes = "Creating Params")
public RestResponse create(@RequestBody RestRequestObject<UserRoles[]> req, HttpServletRequest request,
	HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "createapplication");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(usrRoleService.create(req), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}
	
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Removing Roles", notes = "Removing Roles")
public RestResponse revoke(@RequestBody RestRequestObject<UserRoles[]> req, HttpServletRequest request,
	HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "Remove Role");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(usrRoleService.revoke(req), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Edit  System Params", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponse edit(@RequestBody RestRequestObject<UserRoles> req, HttpServletRequest request,
	HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "EditSystemParams");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(usrRoleService.edit(req.getObject()), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}
	
	@RequestMapping(value = "/approve", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Approve  System Parameters", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponse approve(@RequestBody RestRequestObject<UserRoles[]> req, HttpServletRequest request,
	HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "approveSystemParameters");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(usrRoleService.approve(req), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}

	@RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Rejects  System Parameters", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponse reject(@RequestBody RestRequestObject<UserRoles[]> req, HttpServletRequest request,
	HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "rejectSystemParameters");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(usrRoleService.reject(req), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}
	
	@RequestMapping(value = "/listall", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Customers list", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponse list(@RequestBody RestRequestObject<UserRoles> req, HttpServletRequest request,
	Pageable pageable, HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "list_batch");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(usrRoleService.listRoles(req.getObject(), pageable), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}
	
}
