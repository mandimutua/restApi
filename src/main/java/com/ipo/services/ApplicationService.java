package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Application;

import com.ipo.repositories.ApplicationRepository;

@Service
public class ApplicationService {
	@Autowired
	private ApplicationRepository appRepository;

	public RestResponseObject create(Application req) {
		Application app = new Application();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating Application");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
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
		} catch (Exception er) {
			er.printStackTrace();
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
			if (app.getAppStatus() == BigInteger.valueOf(2) | app.getAppStatus() == BigInteger.valueOf(1)) {
				app.setAppStatus(BigInteger.valueOf(2));
				app.setAppBatCode(req.getAppBatCode());
				app.setAppCustMobileNo(req.getAppCustMobileNo());
				app.setAppSharesApplied(req.getAppSharesApplied());
				app.setAppPaymentMode(req.getAppPaymentMode());
				app.setAppMdate(Calendar.getInstance().getTime());
				app.setAppInputter(req.getAppInputter());
				resp.setMessage("Application Edit Successfull");
				Application edittedApp = appRepository.save(app);
				resp.setPayload(edittedApp);
				resp.setRequestStatus(true);

			} else {
				resp.setMessage("Application is Inactive");
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
		return resp;
	}

	public RestResponseObject reject(RestRequestObject<Application[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Rejecting Application");
		resp.setPayload(null);
		resp.setRequestStatus(false);

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
		return resp;
	}

}
