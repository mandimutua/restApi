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

import com.ipo.utils.ErrorUtl;
import com.ipo.utils.LoginResponse;
import com.ipo.utils.UserLogin;
import com.ipo.elements.ResetPasswordObject;
import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponse;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Batch;
import com.ipo.entities.Users;
import com.ipo.services.UsersService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/users")
@Api(value = "User management", description = "User management API")
public class UsersController {
	@Autowired
	private UsersService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "User login", notes = "User login")
	public LoginResponse login(@RequestBody UserLogin loginRequest, HttpServletRequest request,
			HttpServletResponse response) {
		return userService.userLogin(loginRequest);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/logout", method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ApiOperation(value = "User logout", notes = "User logout")
    public LoginResponse userLogOut(@RequestBody RestRequestObject req, HttpServletRequest request, HttpServletResponse response){
        return userService.logout(req);
    }
	
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    @ApiOperation(value = "Creates a user if doesnt exists", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public RestResponse create(@RequestBody RestRequestObject<Users> req, HttpServletRequest request, HttpServletResponse response) {
        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "createUser");
        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
        if(authorizeStatus.isRequestStatus()){
            resp = new RestResponse(userService.create(req.getObject()), HttpStatus.OK);
        }else{
            resp = ErrorUtl.getFailedMsg();
        }
        return resp;
    }
	
	@RequestMapping(value = "/reset", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ApiOperation(value = "User login", notes = "User login")
	public ResetPasswordObject reset(@RequestBody ResetPasswordObject resetPassword, HttpServletRequest request,
			HttpServletResponse response) {
		return userService.reset(resetPassword);
	}
	
	   @RequestMapping(value = "/listall", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Listing all users", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse list(@RequestBody RestRequestObject<Batch> req, HttpServletRequest request,Pageable pageable, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "listUsers");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(userService.listUsers(req.getObject(),pageable), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	   @RequestMapping(value = "/approve", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Approve user", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse approve(@RequestBody RestRequestObject<Users[]> req, HttpServletRequest request, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "ApproveUsers");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(userService.approve(req), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	   
	   @RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Reject user", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse reject(@RequestBody RestRequestObject<Users[]> req, HttpServletRequest request, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "RejectUsers");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(userService.reject(req), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	   
	   @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Edit user", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse edit(@RequestBody RestRequestObject<Users> req, HttpServletRequest request, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "EditUsers");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(userService.edit(req.getObject()), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	   
	   @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Search users", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse search(@RequestBody RestRequestObject<Users> req, HttpServletRequest request, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "SearchUsers");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(userService.search(req.getObject()), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	
}
