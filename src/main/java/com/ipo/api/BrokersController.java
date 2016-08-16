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
import com.ipo.entities.Brokers;
import com.ipo.services.BrokersService;
import com.ipo.services.UsersService;
import com.ipo.utils.ErrorUtl;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/brokers")
@Api(value = "Broker management", description = "Broker management API")
public class BrokersController {

	@Autowired BrokersService brokerService;
	@Autowired UsersService userService;
	


	
	  @RequestMapping(value = "/listall", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Broker list", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse list(@RequestBody RestRequestObject<Brokers> req, HttpServletRequest request,Pageable pageable, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "listbrokers");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(brokerService.listall(req.getObject(),pageable), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	  
	  
	  @RequestMapping(value = "/listbrokers", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Broker list", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse listBrokers(@RequestBody RestRequestObject<Brokers> req, HttpServletRequest request,Pageable pageable, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "listbrokers");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(brokerService.list(req.getObject()), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	  
	  @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Creates a broker if doesnt exists", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse create(@RequestBody RestRequestObject<Brokers> req, HttpServletRequest request, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "createbroker");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(brokerService.create(req.getObject()), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	  
	  @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Edit  Broker", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse edit(@RequestBody RestRequestObject<Brokers> req, HttpServletRequest request, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "editbroker");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(brokerService.edit(req.getObject()), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	  
	  
	  @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Search  Broker", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse search(@RequestBody RestRequestObject<Brokers> req, HttpServletRequest request,Pageable pageable, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "search_broker");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(brokerService.search(req.getObject(),pageable), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	  
	  
	  @RequestMapping(value = "/approve", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Approve broker", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse approve(@RequestBody RestRequestObject<Brokers[]> req, HttpServletRequest request, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "ApproveUsers");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(brokerService.approve(req), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	  
	  @RequestMapping(value = "/reject", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	    @ApiOperation(value = "Reject broker", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	    public RestResponse reject(@RequestBody RestRequestObject<Brokers[]> req, HttpServletRequest request, HttpServletResponse response) {
	        final RestResponseObject authorizeStatus = userService.authorize(req.getToken(), "ApproveUsers");
	        RestResponse resp = new RestResponse(authorizeStatus, HttpStatus.ACCEPTED);
	        if(authorizeStatus.isRequestStatus()){
	            resp = new RestResponse(brokerService.reject(req), HttpStatus.OK);
	        }else{
	            resp = ErrorUtl.getFailedMsg();
	        }
	        return resp;
	    }
	  
	  
}
