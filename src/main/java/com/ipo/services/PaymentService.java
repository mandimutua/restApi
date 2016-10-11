package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Application;
import com.ipo.entities.Customers;
import com.ipo.entities.Payments;
import com.ipo.repositories.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public RestResponseObject listall(Application payment, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			if(payment.getAppBatCode()==null)
			{
				resp.setPayload(paymentRepository.findAll(pageable));
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			}
			else
			{
				resp.setPayload(paymentRepository.findSpecific(payment.getAppBatCode(),pageable));
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			}
			
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			resp.setRequestStatus(true);
			System.err.println(e.toString());
		}
		return resp;
	}

	public RestResponseObject create(Payments req) {
		Payments pay = new Payments();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating payments");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			pay.setPayAppCusPalCode(req.getPayAppCusPalCode());
			//System.out.println("CusPalCode========="+req.getPayAppCusPalCode().getAppCusPalCode().getCusPalCode());
			
			pay.setPayAccountName(req.getPayAccountName());
			pay.setPayAccountNo(req.getPayAccountNo());
			pay.setPayAmount(req.getPayAmount());
			pay.setPayStatus(BigInteger.valueOf(2));
			pay.setPayCdate(Calendar.getInstance().getTime());
			pay.setPayInputter(req.getPayInputter());
			pay.setPayDate(Calendar.getInstance().getTime());
			pay.setPayType(req.getPayType());
			
			if(req.getPayType().trim().equalsIgnoreCase("CHEQUE"))
			{
				pay.setPayChequeNo(req.getPayChequeNo());
			}
			else if (req.getPayType().trim().equalsIgnoreCase("KCB MTAANI"))
			{
				pay.setPayTerminalId(req.getPayTerminalId());
			}
			
			else if (req.getPayFinanced()==true)
			{
				pay.setPayFinanced(true);
				pay.setPayLoanAcNo(req.getPayLoanAcNo());
				pay.setPayCsdSrNum(req.getPayCsdSrNum());
				pay.setPayFinancingBank(req.getPayFinancingBank());
				pay.setPayFinancingBankBranch(req.getPayFinancingBankBranch());
			}
			pay.setPayFinanced(false);
			pay.setPayType(req.getPayType().trim());
			//pay.setPayBankCode(req.getPayBankCode());
			//pay.setPayBranch(req.getPayBranch());
			pay.setPayTransRef(req.getPayTransRef());
			
			Payments createdpayments = paymentRepository.save(pay);
			resp.setMessage("Success");
			resp.setPayload(createdpayments);
			resp.setRequestStatus(true);
		} catch (Exception er) {
			//resp.setMessage("Server Error. Please try again later.");
			resp.setMessage(er.toString());
			resp.setRequestStatus(true);
			er.printStackTrace();
		}
		return resp;
	}

	public RestResponseObject edit(Payments req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Editting Payment");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Payments pay = paymentRepository.findByPayCode(req.getPayCode());

		if (pay == null) {
			resp.setMessage("Payment not found");
			resp.setRequestStatus(true);
		} else {
			try{
			// check payment status
			if (pay.getPayStatus() == BigInteger.valueOf(2)) {

				//pay.setPayAppCusPalCode(req.getPayAppCusPalCode());
				//System.out.println("CusPalCode========="+req.getPayAppCusPalCode().getAppCusPalCode().getCusPalCode());
				
				pay.setPayAccountName(req.getPayAccountName());
				pay.setPayAccountNo(req.getPayAccountNo());
				pay.setPayAmount(req.getPayAmount());
				pay.setPayStatus(BigInteger.valueOf(2));
				pay.setPayCdate(Calendar.getInstance().getTime());
				pay.setPayInputter(req.getPayInputter());
				pay.setPayDate(Calendar.getInstance().getTime());
				pay.setPayType(req.getPayType());
				
				if(req.getPayType().trim().equalsIgnoreCase("CHEQUE"))
				{
					pay.setPayChequeNo(req.getPayChequeNo());
				}
				else if (req.getPayType().trim().equalsIgnoreCase("KCB MTAANI"))
				{
					pay.setPayTerminalId(req.getPayTerminalId());
				}
				
				
				pay.setPayBankCode(req.getPayBankCode());
				pay.setPayBranch(req.getPayBranch());
				
				pay.setPayTransRef(req.getPayTransRef());
				
				Payments createdpayments = paymentRepository.save(pay);
				resp.setMessage("Success");
				resp.setPayload(createdpayments);
				resp.setRequestStatus(true);
			

			} else {
				resp.setMessage("Payment is Inactive");
				resp.setRequestStatus(true);
			}

			}catch(Exception e)
			{
				resp.setMessage("Server Error. Please try again later.");
				resp.setRequestStatus(true);
				System.err.println(e.toString());
			}
		}

		return resp;
	}

	public RestResponseObject approve(RestRequestObject<Payments[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Payments");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		for (Payments r : req.getObject()) {
			Payments pay = paymentRepository.findByPayCode(r.getPayCode());

			if (pay == null) {
				resp.setMessage("Batch not found");
				resp.setRequestStatus(true);
			} else {
				try{
				// check payment status
				if (pay.getPayStatus() == BigInteger.valueOf(2)) {
					pay.setPayDate(Calendar.getInstance().getTime());
					pay.setPayAuthoriser(r.getPayInputter());
					pay.setPayMdate(Calendar.getInstance().getTime());
					pay.setPayStatus(BigInteger.valueOf(1));
					resp.setMessage("Payment Approval Successfull");
					Payments approvedpayments = paymentRepository.save(pay);
					resp.setPayload(approvedpayments);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("Payment is not set for approval");
					resp.setRequestStatus(true);
				}
				}catch(Exception e)
				{
					resp.setMessage("Server Error. Please try again later.");
					resp.setRequestStatus(true);
					System.err.println(e.toString());
					
				}

			}
		}
		return resp;
	}

	public RestResponseObject reject(RestRequestObject<Payments[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Rejecting Payments");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		for (Payments r : req.getObject()) {
			Payments pay = paymentRepository.findByPayCode(r.getPayCode());

			if (pay == null) {
				resp.setMessage("Batch not found");
				resp.setRequestStatus(true);
			} else {
				try{
				// check payment status
				if (pay.getPayStatus() == BigInteger.valueOf(2)) {
					pay.setPayDate(Calendar.getInstance().getTime());
					pay.setPayAuthoriser(r.getPayInputter());
					pay.setPayMdate(Calendar.getInstance().getTime());
					pay.setPayStatus(BigInteger.valueOf(3));
					resp.setMessage("Payment Rejection Successfull");
					Payments rejectedpayments = paymentRepository.save(pay);
					resp.setPayload(rejectedpayments);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("Payment is not set for rejection");
					resp.setRequestStatus(true);
				}

				}catch(Exception e)
				{
					resp.setMessage("Server Error. Please try again later.");
					resp.setRequestStatus(true);
					System.err.println(e.toString());
				}
			}
		}
		return resp;
	}

	public RestResponseObject search(Payments pay, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Page<Payments> pays = paymentRepository.findPayment(pay.getPayAppCusPalCode(),pageable);

		System.out.println("Pay code being sent============="+pay.getPayAppCusPalCode());
		if (pays == null) {
			resp.setMessage("Payment not found");
			resp.setRequestStatus(true);
		} else {
			try {
				if(!pays.hasContent())
				{
					resp.setRequestStatus(true);
					resp.setMessage("No Payments Available ");
				}else{
					resp.setPayload(pays);
					resp.setRequestStatus(true);
					resp.setMessage("Success");
				}

			} catch (Exception e) {
				resp.setMessage("Server Error. Please try again later.");
				resp.setRequestStatus(true);
				System.err.println(e.toString());
			}
		}

		return resp;
	}


	public RestResponseObject searchPay(Customers app, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Page<Payments> apps = paymentRepository.findSpecificPay(app.getCusSurname().trim(),pageable);


		try {
			if (apps==null){
				resp.setMessage("Applications Not Found");
				resp.setRequestStatus(true);
			}
			else{
			resp.setPayload(apps);
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
	
	public RestResponseObject searchPayments(Payments pay) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setRequestStatus(false);
		List<Payments> bat = (List<Payments>) paymentRepository.findAllByPayCode();
		try {

			if (bat == null) {
				resp.setRequestStatus(true);
				resp.setPayload(bat);
				resp.setMessage("Payment not found");
			} else {
				if(bat.isEmpty())
				{
					resp.setRequestStatus(true);
					resp.setPayload(bat);
					resp.setMessage("No Authorized Payments Available");
				}
				else
				{
					resp.setRequestStatus(true);
					resp.setPayload(bat);
					resp.setMessage("Success");
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
