package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Customers;

import com.ipo.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public RestResponseObject create(Customers req) {
		Customers cus = new Customers();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating customer");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			cus.setCusSharesAcNo(req.getCusSharesAcNo());
			cus.setCusName(req.getCusName().toUpperCase());
			cus.setCusAddress(req.getCusAddress().toUpperCase());
			cus.setCusTown(req.getCusTown().toUpperCase());
			cus.setCusCountry(req.getCusCountry().toUpperCase());
			cus.setCusShareholding(req.getCusShareholding());
			cus.setCusPhone(req.getCusPhone());
			cus.setCusEmail(req.getCusEmail());
			cus.setCusStatus(BigInteger.valueOf(2));
			cus.setCusCdate(Calendar.getInstance().getTime());
			cus.setCusInputter(req.getCusInputter());
			cus.setCusDate(Calendar.getInstance().getTime());

			Customers createdcustomer = customerRepository.save(cus);
			resp.setMessage("Success");
			resp.setPayload(createdcustomer);
			resp.setRequestStatus(true);
		} catch (Exception er) {
			er.printStackTrace();
		}
		return resp;
	}

	public RestResponseObject edit(Customers req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Editting Customer");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Customers cust = customerRepository.findByCusPalCode(req.getCusPalCode());

		if (cust == null) {
			resp.setMessage("Customer not found");
			resp.setRequestStatus(true);
		} else {
			// check batch status
			if (cust.getCusStatus() == BigInteger.valueOf(2) | cust.getCusStatus() == BigInteger.valueOf(1)) {
				try {
					cust.setCusSharesAcNo(req.getCusSharesAcNo());
					cust.setCusName(req.getCusName());
					cust.setCusAddress(req.getCusAddress());
					cust.setCusTown(req.getCusTown());
					cust.setCusCountry(req.getCusCountry());
					cust.setCusShareholding(req.getCusShareholding());
					cust.setCusPhone(req.getCusPhone());
					cust.setCusEmail(req.getCusEmail());
					cust.setCusStatus(req.getCusStatus());
					cust.setCusInputter(req.getCusInputter());
					cust.setCusDate(Calendar.getInstance().getTime());
					resp.setMessage("Customer Edit Successfull");
					Customers createdcustomer = customerRepository.save(cust);
					resp.setPayload(createdcustomer);
					resp.setRequestStatus(true);
				} catch (DataIntegrityViolationException e) {
					resp.setMessage("Inputter is not a valid user");
					resp.setRequestStatus(true);

				} catch (Exception e) {
					resp.setMessage(e.toString());
					resp.setRequestStatus(true);
				}

			} else {
				resp.setMessage("Customer is Inactive");
				resp.setRequestStatus(true);
			}

		}

		return resp;
	}

	public RestResponseObject listall(Customers customer, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			resp.setPayload(customerRepository.findAll(pageable));
			resp.setRequestStatus(true);
			resp.setMessage("Success");
		} catch (Exception e) {

		}
		return resp;
	}

	public RestResponseObject listCustomers(Customers customer) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		//resp.setPayload(null);
		resp.setRequestStatus(false);
		List<Customers> cust = customerRepository.findByCusName(customer.getCusName().trim());
		
		if(cust==null)
		{
			resp.setRequestStatus(true);
			resp.setPayload(cust);
			resp.setMessage("Customer not found");
		}
		else
		{
			
			
			resp.setRequestStatus(true);
			resp.setPayload(cust);
			resp.setMessage("Success");
		}	
			
		
		return resp;
	}
	
	public RestResponseObject approve(RestRequestObject<Customers[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Customers");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		

		for (Customers r : req.getObject()) {
			Customers cust = customerRepository.findByCusPalCode(r.getCusPalCode());
		
		if (cust == null) {
			resp.setMessage("Customer not found");
			resp.setRequestStatus(true);
		} else {
			// check batch status
			if (cust.getCusStatus() == BigInteger.valueOf(2)) {

				try {
					cust.setCusStatus(BigInteger.valueOf(1));
					cust.setCusAuthoriser(r.getCusAuthoriser());
					cust.setCusMdate(Calendar.getInstance().getTime());
					cust.setCusDate(Calendar.getInstance().getTime());

					resp.setMessage("Customer Approval Successfull");
					Customers approvedcustomer = customerRepository.save(cust);
					resp.setPayload(approvedcustomer);
					resp.setRequestStatus(true);

				} catch (DataIntegrityViolationException e) {
					resp.setMessage(e.toString());
					resp.setRequestStatus(true);
				} catch (Exception e) {
					resp.setMessage(e.toString());
					resp.setRequestStatus(true);
				}
			} else {
				resp.setMessage("Customer is not set for approval");
				resp.setRequestStatus(true);
			}

		}
		}
		return resp;
	}
	
	public RestResponseObject reject(RestRequestObject<Customers[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Rejecting Customers");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		
		for (Customers r : req.getObject()) {
		Customers cust = customerRepository.findByCusPalCode(r.getCusPalCode());

		if (cust == null) {
			resp.setMessage("Customer not found");
			resp.setRequestStatus(true);
		} else {
			// check batch status
			if (cust.getCusStatus() == BigInteger.valueOf(2)) {

				try {
					cust.setCusStatus(BigInteger.valueOf(3));
					cust.setCusAuthoriser(r.getCusAuthoriser());
					cust.setCusMdate(Calendar.getInstance().getTime());
					cust.setCusDate(Calendar.getInstance().getTime());

					resp.setMessage("Customer Rejection Successfull");
					Customers approvedcustomer = customerRepository.save(cust);
					resp.setPayload(approvedcustomer);
					resp.setRequestStatus(true);

				} catch (DataIntegrityViolationException e) {
					resp.setMessage(e.toString());
					resp.setRequestStatus(true);
				} catch (Exception e) {
					resp.setMessage(e.toString());
					resp.setRequestStatus(true);
				}
			} else {
				resp.setMessage("Customer is not set for rejection");
				resp.setRequestStatus(true);
			}

		}

		}
		return resp;
	}
	
	public RestResponseObject search(Customers cust, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Page<Customers> cus = customerRepository.findByCusName(cust.getCusName(), pageable);

		if (cus == null) {
			resp.setMessage("Customer not found");
			resp.setRequestStatus(true);
		} else {
			if (!cus.hasContent()) {
				resp.setPayload(cus);
				resp.setRequestStatus(true);
				resp.setMessage("Customer not found");
			} else {
				resp.setPayload(cus);
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			}

		}

		return resp;
	}

}
