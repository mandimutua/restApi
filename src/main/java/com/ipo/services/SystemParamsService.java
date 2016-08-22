package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.SystemParameters;
import com.ipo.repositories.SystemParamsRepository;

@Service
public class SystemParamsService {

@Autowired
private SystemParamsRepository paramsRepository;

public RestResponseObject create(SystemParameters req) {
	SystemParameters app = new SystemParameters();
	RestResponseObject resp = new RestResponseObject();
	resp.setMessage("Error creating System Parameters");
	resp.setPayload(null);
	resp.setRequestStatus(false);

	try {
		app.setParamName(req.getParamName());
		app.setParamValue1(req.getParamValue1());
		app.setParamValue2(req.getParamValue2());
		app.setParamAppl(req.getParamAppl());
		app.setParamExempt(req.getParamExempt());
		app.setParamStatus(BigInteger.valueOf(2));
		app.setParamCdate(Calendar.getInstance().getTime());
		app.setParamDate(Calendar.getInstance().getTime());
		app.setParamInputter(req.getParamInputter());
		app.setParamDate(Calendar.getInstance().getTime());
		SystemParameters createdParam = paramsRepository.save(app);
		resp.setMessage("Success");
		resp.setPayload(createdParam);
		resp.setRequestStatus(true);
	} catch (Exception er) {
		resp.setMessage("Server Error. Please try again later.");
		System.err.println(er.toString());
		resp.setRequestStatus(true);
	}
	return resp;
}

public RestResponseObject listall(SystemParameters app, Pageable pageable) {
	RestResponseObject resp = new RestResponseObject();
	resp.setMessage("Not Found");
	resp.setPayload(null);
	resp.setRequestStatus(false);

	try {
		resp.setPayload(paramsRepository.findAll(pageable));
		resp.setRequestStatus(true);
		resp.setMessage("Success");
	} catch (Exception e) {
		resp.setMessage("Server Error. Please try again later.");
		System.err.println(e.toString());
		resp.setRequestStatus(true);

	}
	return resp;
}

public RestResponseObject edit(SystemParameters req) {

	RestResponseObject resp = new RestResponseObject();
	resp.setMessage("Error Editting Batch");
	resp.setPayload(null);
	resp.setRequestStatus(false);
	SystemParameters app = paramsRepository.findByParamCode(req.getParamCode());

	if (app == null) {
		resp.setMessage("System Param not found");
		resp.setRequestStatus(true);
	} else {
		// check batch status
		try {
			if (app.getParamStatus() == BigInteger.valueOf(2) | app.getParamStatus() == BigInteger.valueOf(1)) {
				app.setParamName(req.getParamName());
				app.setParamValue1(req.getParamValue1());
				app.setParamValue2(req.getParamValue2());
				app.setParamAppl(req.getParamAppl());
				app.setParamExempt(req.getParamExempt());
				app.setParamStatus(BigInteger.valueOf(2));
				app.setParamMdate((Calendar.getInstance().getTime()));
				app.setParamInputter(req.getParamInputter());
				app.setParamDate((Calendar.getInstance().getTime()));
				resp.setMessage("Param Edit Successfull");
				SystemParameters edittedParam = paramsRepository.save(app);
				resp.setPayload(edittedParam);
				resp.setRequestStatus(true);

			} else {
				resp.setMessage("System Param is Inactive");
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

public RestResponseObject approve(RestRequestObject<SystemParameters[]> req) {

	RestResponseObject resp = new RestResponseObject();
	resp.setMessage("Error Approving Application");
	resp.setPayload(null);
	resp.setRequestStatus(false);

	try {
		for (SystemParameters r : req.getObject()) {
			SystemParameters app = paramsRepository.findByParamCode(r.getParamCode());

			if (app == null) {
				resp.setMessage("Param not found");
				resp.setRequestStatus(true);
			} else {
				// check batch status
				if (app.getParamStatus()== BigInteger.valueOf(2)) {

					app.setParamMdate(Calendar.getInstance().getTime());
					app.setParamStatus(BigInteger.valueOf(1));
					app.setParamAuthoriser(r.getParamAuthoriser());
					app.setParamDate(Calendar.getInstance().getTime());
					resp.setMessage("System Parameters Approval Successfull");
					SystemParameters approvedParam = paramsRepository.save(app);
					resp.setPayload(approvedParam);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("System Param is not set for approval");
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

public RestResponseObject reject(RestRequestObject<SystemParameters[]> req) {

	RestResponseObject resp = new RestResponseObject();
	resp.setMessage("Error Approving Application");
	resp.setPayload(null);
	resp.setRequestStatus(false);

	try {
		for (SystemParameters r : req.getObject()) {
			SystemParameters app = paramsRepository.findByParamCode(r.getParamCode());

			if (app == null) {
				resp.setMessage("Param not found");
				resp.setRequestStatus(true);
			} else {
				// check batch status
				if (app.getParamStatus()== BigInteger.valueOf(2)) {

					app.setParamMdate(Calendar.getInstance().getTime());
					app.setParamStatus(BigInteger.valueOf(3));
					app.setParamAuthoriser(r.getParamAuthoriser());
					app.setParamDate(Calendar.getInstance().getTime());
					resp.setMessage("System Parameters Rejection Successfull");
					SystemParameters rejectParam = paramsRepository.save(app);
					resp.setPayload(rejectParam);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("System Param is not set for rejection");
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
