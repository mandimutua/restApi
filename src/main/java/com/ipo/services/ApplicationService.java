package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Application;
import com.ipo.entities.SystemParameters;
import com.ipo.repositories.ApplicationRepository;
import com.ipo.repositories.SystemParamsRepository;




@Service
public class ApplicationService {
	@Autowired
	private ApplicationRepository appRepository;
	
	@Autowired
	private SystemParamsRepository sysParamRepository;
	
	

	public RestResponseObject create(Application req) {
		Application app = new Application();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating Application");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		
	

		try {
			//if(appRepository.countByAppBatCode(req.getAppBatCode())sysParamRepository.){}
			SystemParameters params = sysParamRepository.findByParamName("BatchSize".trim());
			System.out.println("Batch System Param size"+(params.getParamValue1()));
			int batch_size = appRepository.countByAppBatCode(req.getAppBatCode()).intValueExact();
			int batch_limit = Integer.parseInt(params.getParamValue1());
			
			if(params.getParamStatus()==BigInteger.valueOf(2))
			{
				resp.setMessage("Batch Size is not Approved");
				resp.setRequestStatus(true);
			}
			else{
			System.out.println("===============Batch size"+batch_size+"=========batch limit"+batch_limit);
			if(batch_size >=batch_limit){
				resp.setMessage("Batch Limit reached");
				resp.setRequestStatus(true);
			} else{
				//insert
			
			//if(appRepository.countByAppBatCode(req.getAppBatCode())==(((BigDecimal)sysParamRepository.findByParamName("BatchSize")))){}
			System.out.println("Batch Number=============="+appRepository.countByAppBatCode(req.getAppBatCode()));
			
			app.setAppCustMobileNo(req.getAppCustMobileNo());
			app.setAppBatCode(req.getAppBatCode());
			app.setAppCusPalCode(req.getAppCusPalCode());
			app.setAppCustMobileNo(req.getAppCustMobileNo());
			app.setAppSharesApplied(req.getAppSharesApplied());
			app.setAppPaymentMode(req.getAppPaymentMode());
			app.setAppStatus(BigInteger.valueOf(2));
			app.setAppCdate(Calendar.getInstance().getTime());
			app.setAppInputter(req.getAppInputter());
			app.setAppDate(Calendar.getInstance().getTime());

			Application createdapp = appRepository.save(app);
			resp.setMessage("Success");
			resp.setPayload(createdapp);
			resp.setRequestStatus(true);
			}
			}
		} catch (Exception er) {
			er.printStackTrace();
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(er.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject listall(Application app, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			resp.setPayload(appRepository.findAll(pageable));
			resp.setRequestStatus(true);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);

		}
		return resp;
	}

	public RestResponseObject edit(Application req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Editting Batch");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Application app = appRepository.findByAppCode(req.getAppCode());

		if (app == null) {
			resp.setMessage("Application not found");
			resp.setRequestStatus(true);
		} else {
			// check batch status
			try {
				if (app.getAppStatus() == BigInteger.valueOf(2) | app.getAppStatus() == BigInteger.valueOf(1)) {
					app.setAppStatus(BigInteger.valueOf(2));
					app.setAppBatCode(req.getAppBatCode());
					app.setAppCustMobileNo(req.getAppCustMobileNo());
					app.setAppSharesApplied(req.getAppSharesApplied());
					app.setAppPaymentMode(req.getAppPaymentMode());
					app.setAppMdate(Calendar.getInstance().getTime());
					app.setAppInputter(req.getAppInputter());
					app.setAppDate(Calendar.getInstance().getTime());
					resp.setMessage("Application Edit Successfull");
					Application edittedApp = appRepository.save(app);
					resp.setPayload(edittedApp);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("Application is Inactive");
					resp.setRequestStatus(true);
				}
			} catch (Exception e) {
				resp.setMessage("Server Error. Please try again later.");
				System.err.println(e.toString());
				resp.setRequestStatus(true);
			}

		}

		return resp;
	}

	public RestResponseObject approve(RestRequestObject<Application[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Application");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			for (Application r : req.getObject()) {
				Application app = appRepository.findByAppCode(r.getAppCode());

				if (app == null) {
					resp.setMessage("Batch not found");
					resp.setRequestStatus(true);
				} else {
					// check batch status
					if (app.getAppStatus() == BigInteger.valueOf(2)) {

						app.setAppMdate(Calendar.getInstance().getTime());
						app.setAppStatus(BigInteger.valueOf(1));
						app.setAppAuthoriser(r.getAppAuthoriser());
						app.setAppDate(Calendar.getInstance().getTime());
						resp.setMessage("Application Approval Successfull");
						Application approvedApp = appRepository.save(app);
						resp.setPayload(approvedApp);
						resp.setRequestStatus(true);

					} else {
						resp.setMessage("Application is not set for approval");
						resp.setRequestStatus(true);
					}

				}
			}
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject reject(RestRequestObject<Application[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Rejecting Application");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {

			for (Application r : req.getObject()) {
				Application app = appRepository.findByAppCode(r.getAppCode());

				if (app == null) {
					resp.setMessage("Batch not found");
					resp.setRequestStatus(true);
				} else {
					// check batch status
					if (app.getAppStatus() == BigInteger.valueOf(2)) {

						app.setAppMdate(Calendar.getInstance().getTime());
						app.setAppStatus(BigInteger.valueOf(3));
						app.setAppAuthoriser(r.getAppAuthoriser());
						app.setAppDate(Calendar.getInstance().getTime());
						resp.setMessage("Application Rejection Successfull");
						Application approvedApp = appRepository.save(app);
						resp.setPayload(approvedApp);
						resp.setRequestStatus(true);

					} else {
						resp.setMessage("Application is not set for rejection");
						resp.setRequestStatus(true);
					}

				}
			}
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

}
