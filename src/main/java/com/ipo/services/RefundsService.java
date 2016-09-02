package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Application;
import com.ipo.entities.Refunds;
import com.ipo.repositories.RefundsRepository;

@Service
public class RefundsService {
	@Autowired
	private RefundsRepository refundsRepository;

	public RestResponseObject listall(Application refund, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Refunds Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			if(refund.getAppBatCode()==null)
			{
				resp.setPayload(refundsRepository.findAll(pageable));
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			}
			else
			{
				resp.setPayload(refundsRepository.findSpecific(refund.getAppBatCode(),pageable));
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			}
			
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject create(Refunds req) {
		Refunds ref = new Refunds();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating batch");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			ref.setRfdPayCode(req.getRfdPayCode());
			ref.setRfdAppCode(req.getRfdAppCode());
			ref.setRfdAmount(req.getRfdAmount());
			ref.setRfdMode(req.getRfdMode());
			ref.setRfdChequeNo(req.getRfdChequeNo());
			ref.setRfdBankCode(req.getRfdBankCode());
			ref.setRfdAccountNo(req.getRfdAccountNo());
			ref.setRfdTransRef(req.getRfdTransRef());
			ref.setRfdPhoneNo(req.getRfdPhoneNo());
			ref.setRfdStatus(BigInteger.valueOf(2));
			ref.setRfdCdate(Calendar.getInstance().getTime());
			ref.setRfdInputter(req.getRfdInputter());
			ref.setRfdDate(Calendar.getInstance().getTime());

			Refunds createdRefund = refundsRepository.save(ref);
			resp.setMessage("Success");
			resp.setPayload(createdRefund);
			resp.setRequestStatus(true);
		} catch (DataIntegrityViolationException er) {
			resp.setMessage("Server Error Application or Paycode");
			System.err.println(er.toString());
			resp.setRequestStatus(true);

		}

		catch (Exception er) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(er.toString());
			resp.setRequestStatus(true);

		}
		return resp;
	}

	public RestResponseObject edit(Refunds req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Editting Refund");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Refunds ref = refundsRepository.findByRfdCode(req.getRfdCode());

		try {

			if (ref == null) {
				resp.setMessage("Refund not found");
				resp.setRequestStatus(true);
			} else {
				// check batch status
				if (ref.getRfdStatus() == BigInteger.valueOf(2)) {
					ref.setRfdPayCode(req.getRfdPayCode());
					ref.setRfdAppCode(req.getRfdAppCode());
					ref.setRfdAmount(req.getRfdAmount());
					ref.setRfdMode(req.getRfdMode());
					ref.setRfdChequeNo(req.getRfdChequeNo());
					ref.setRfdBankCode(req.getRfdBankCode());
					ref.setRfdAccountNo(req.getRfdAccountNo());
					ref.setRfdTransRef(req.getRfdTransRef());
					ref.setRfdPhoneNo(req.getRfdPhoneNo());
					ref.setRfdStatus(BigInteger.valueOf(2));
					ref.setRfdMdate(Calendar.getInstance().getTime());
					ref.setRfdInputter(req.getRfdInputter());
					resp.setMessage("Refund Edit Successfull");
					Refunds editedRefunds = refundsRepository.save(ref);
					resp.setPayload(editedRefunds);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("Refund is not set for Editting");
					resp.setRequestStatus(true);
				}

			}

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject approve(RestRequestObject<Refunds[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Refund");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {

			for (Refunds r : req.getObject()) {
				Refunds ref = refundsRepository.findByRfdCode(r.getRfdCode());

				if (ref == null) {
					resp.setMessage("Refund not found");
					resp.setRequestStatus(true);
				} else {
					// check refund status
					if (ref.getRfdStatus() == BigInteger.valueOf(2)) {

						ref.setRfdMdate(Calendar.getInstance().getTime());
						;
						ref.setRfdAuthoriser(r.getRfdAuthoriser());

						ref.setRfdStatus(BigInteger.valueOf(1));
						resp.setMessage("Refund Approval Successfull");
						Refunds approvedRefund = refundsRepository.save(ref);
						resp.setPayload(approvedRefund);
						resp.setRequestStatus(true);

					} else {
						resp.setMessage("Refund is not set for approval");
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

	public RestResponseObject reject(RestRequestObject<Refunds[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Refund");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {

			for (Refunds r : req.getObject()) {
				Refunds ref = refundsRepository.findByRfdCode(r.getRfdCode());

				if (ref == null) {
					resp.setMessage("Refund not found");
					resp.setRequestStatus(true);
				} else {
					// check refund status
					if (ref.getRfdStatus() == BigInteger.valueOf(2)) {

						ref.setRfdMdate(Calendar.getInstance().getTime());
						;
						ref.setRfdAuthoriser(r.getRfdAuthoriser());

						ref.setRfdStatus(BigInteger.valueOf(3));
						resp.setMessage("Refund Rejection Successfull");
						Refunds approvedRefund = refundsRepository.save(ref);
						resp.setPayload(approvedRefund);
						resp.setRequestStatus(true);

					} else {
						resp.setMessage("Refund is not set for rejection");
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
