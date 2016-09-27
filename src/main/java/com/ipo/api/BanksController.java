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
import com.ipo.entities.Banks;
import com.ipo.services.BankService;
import com.ipo.services.UsersService;
import com.ipo.utils.ErrorUtl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/bank")
@Api(value = "Bank management", description = "Bank management API")
public class BanksController {
	
	@Autowired
	UsersService userService;
	
	@Autowired
	BankService bankService;
	@RequestMapping(value = "/listall", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "Bank list", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public RestResponse list(@RequestBody RestRequestObject<Banks> req, HttpServletRequest request, Pageable pageable,
			HttpServletResponse response) {
		final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "list_banks");
		RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
		if (authorizeStatus.isRequestStatus()) {
			resp = new RestResponse(bankService.listall(req.getObject(), pageable), HttpStatus.OK);
		} else {
			resp = ErrorUtl.getFailedMsg();
		}
		return resp;
	}
	
	@RequestMapping(value = "/filterBanks", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Bank Filter", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponse filter(@RequestBody RestRequestObject<Banks> req, HttpServletRequest request, Pageable pageable,
	HttpServletResponse response) {
final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "list_banks");
RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponse(bankService.filterBanks(req.getObject()), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg();
}
return resp;
}

}
