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
import com.ipo.elements.RestResponseReport;
import com.ipo.elements.RestResponseReportsObjects;
import com.ipo.entities.Batch;
import com.ipo.services.ReportsService;
import com.ipo.services.UsersService;
import com.ipo.utils.ErrorUtl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/reports")
@Api(value = "Report management", description = "Report management API")
public class ReportController {
	
	@Autowired
	ReportsService reportService;
	
	@Autowired
	UsersService userService;
	
	@RequestMapping(value = "/batchReport", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Fetch Application list", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponseReport list(@RequestBody RestRequestObject<Batch> req, HttpServletRequest request,
	Pageable pageable, HttpServletResponse response) {
final RestResponseReportsObjects authorizeStatus = userService.permit(req.getToken(), "list_application");
RestResponseReport resp = new RestResponseReport(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponseReport(reportService.fetchall(req.getObject(), pageable), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg1();
}
return resp;
}
	
	@RequestMapping(value = "/brokersReport", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
@ApiOperation(value = "Fetch Application list", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
public RestResponseReport brk(@SuppressWarnings("rawtypes") @RequestBody RestRequestObject req, HttpServletRequest request,
	Pageable pageable, HttpServletResponse response) {
final RestResponseReportsObjects authorizeStatus = userService.permit(req.getToken(), "list_application");
RestResponseReport resp = new RestResponseReport(authorizeStatus, HttpStatus.ACCEPTED);
if (authorizeStatus.isRequestStatus()) {
	resp = new RestResponseReport(reportService.brokersReport(pageable), HttpStatus.OK);
} else {
	resp = ErrorUtl.getFailedMsg1();
}
return resp;
}
	

}
