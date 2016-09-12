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
import com.ipo.entities.Batch;

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
			cus.setCusCdscAcNo(req.getCusCdscAcNo().trim().toUpperCase());
			cus.setCusSurname(req.getCusSurname().trim().toUpperCase());
			cus.setCusAddress(req.getCusAddress().trim().toUpperCase());
			cus.setCusTown(req.getCusTown().trim().toUpperCase());
			cus.setCusCitizenship(req.getCusCitizenship().trim().toUpperCase());
			cus.setCusMobilePhone(req.getCusMobilePhone().trim().toUpperCase());
			cus.setCusEmail(req.getCusEmail());
			cus.setCusStatus(BigInteger.valueOf(2));
			cus.setCusCdate(Calendar.getInstance().getTime());
			cus.setCusInputter(req.getCusInputter());
			cus.setCusDate(Calendar.getInstance().getTime());
			cus.setcusFormSerialNo(req.getcusFormSerialNo());
			cus.setCusApplicantCategory(req.getCusApplicantCategory().trim().toUpperCase());
			cus.setCusApplicantResidence(req.getCusApplicantCategory().trim().toUpperCase());
			cus.setCusTaxExmpt(req.getCusTaxExmpt().trim().toUpperCase());
			cus.setCusOtherNames(req.getCusOtherNames().trim().toUpperCase());
			cus.setCusIdNo(req.getCusIdNo().trim().toUpperCase());
			cus.setCusJointSurname(req.getCusJointSurname().trim().toUpperCase());
			cus.setCusJointOthernames(req.getCusJointOthernames().trim().toUpperCase());
			cus.setCusJointApplicantIdNo(req.getCusJointApplicantIdNo().trim().toUpperCase());
			cus.setCusCompanyName(req.getCusCompanyName().trim().toUpperCase());
			cus.setCusCompanyRegNo(req.getCusCompanyRegNo().trim().toUpperCase());
			cus.setCusCompanyDateOfInc(req.getCusCompanyDateOfInc());
			cus.setCusNomineeName(req.getCusNomineeName().trim().toUpperCase());
			cus.setCusNomineeAc(req.getCusNomineeAc());
			cus.setCusPostalCode(req.getCusPostalCode().trim().toUpperCase());
			cus.setCusStreet(req.getCusStreet().trim().toUpperCase());
			cus.setCusTelNo(req.getCusTelNo().trim().toUpperCase());
			cus.setCusFaxNo(req.getCusFaxNo().trim().toUpperCase());
			cus.setCusBrkCode(req.getCusBrkCode());
			cus.setCusCountry(req.getCusCountry().trim().toUpperCase());
			cus.setCusApplicantType(req.getCusApplicantType().trim().toUpperCase());
			
		
			Customers createdcustomer = customerRepository.save(cus);
			resp.setMessage("Success");
			resp.setPayload(createdcustomer);
			resp.setRequestStatus(true);
		} catch (Exception er) {
			resp.setMessage("Server Error. Please try again later.");
			resp.setRequestStatus(true);
			System.err.println(er.toString());
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
					
					cust.setCusCdscAcNo(req.getCusCdscAcNo().trim().toUpperCase());
					cust.setCusSurname(req.getCusSurname().trim().toUpperCase());
					cust.setCusAddress(req.getCusAddress().trim().toUpperCase());
					cust.setCusTown(req.getCusTown().trim().toUpperCase());
					cust.setCusCitizenship(req.getCusCitizenship().trim().toUpperCase());
					cust.setCusMobilePhone(req.getCusMobilePhone().trim().toUpperCase());
					cust.setCusEmail(req.getCusEmail());
					cust.setCusStatus(BigInteger.valueOf(2));
					//cust.setCusCdate(Calendar.getInstance().getTime());
					cust.setCusInputter(req.getCusInputter());
					cust.setCusDate(Calendar.getInstance().getTime());
					cust.setcusFormSerialNo(req.getcusFormSerialNo());
					cust.setCusApplicantCategory(req.getCusApplicantCategory().trim().toUpperCase());
					cust.setCusApplicantResidence(req.getCusApplicantCategory().trim().toUpperCase());
					cust.setCusTaxExmpt(req.getCusTaxExmpt().trim().toUpperCase());
					cust.setCusOtherNames(req.getCusOtherNames().trim().toUpperCase());
					cust.setCusIdNo(req.getCusIdNo().trim().toUpperCase());
					cust.setCusJointSurname(req.getCusJointSurname().trim().toUpperCase());
					cust.setCusJointOthernames(req.getCusJointOthernames().trim().toUpperCase());
					cust.setCusJointApplicantIdNo(req.getCusJointApplicantIdNo().trim().toUpperCase());
					cust.setCusCompanyName(req.getCusCompanyName().trim().toUpperCase());
					cust.setCusCompanyRegNo(req.getCusCompanyRegNo().trim().toUpperCase());
					cust.setCusCompanyDateOfInc(req.getCusCompanyDateOfInc());
					cust.setCusNomineeName(req.getCusNomineeName().trim().toUpperCase());
					cust.setCusNomineeAc(req.getCusNomineeAc());
					cust.setCusPostalCode(req.getCusPostalCode().trim().toUpperCase());
					cust.setCusStreet(req.getCusStreet().trim().toUpperCase());
					cust.setCusTelNo(req.getCusTelNo().trim().toUpperCase());
					cust.setCusFaxNo(req.getCusFaxNo().trim().toUpperCase());
					cust.setCusCountry(req.getCusCountry().trim().toUpperCase());
					cust.setCusApplicantType(req.getCusApplicantType().trim().toUpperCase());
					
					
					
					resp.setMessage("Customer Edit Successfull");
					Customers createdcustomer = customerRepository.save(cust);
					resp.setPayload(createdcustomer);
					resp.setRequestStatus(true);
				} catch (DataIntegrityViolationException e) {
					resp.setMessage("Inputter is not a valid user");
					resp.setRequestStatus(true);

				} catch (Exception e) {
					resp.setMessage("Server Error. Please try again later.");
					resp.setRequestStatus(true);
					System.err.println(e.toString());
				}

			} else {
				resp.setMessage("Customer is Inactive");
				resp.setRequestStatus(true);
			}

		}

		return resp;
	}

	public RestResponseObject listall(Batch customer, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			if(customer.getBatBrkCode()==null)
			{
				resp.setPayload(customerRepository.findAll(pageable));
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			}
			else
			{
				resp.setPayload(customerRepository.findSpecific(customer.getBatBrkCode(),pageable));
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

	public RestResponseObject listCustomers(Customers customer) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setRequestStatus(false);
		List<Customers> cust = customerRepository.findByCusSurname(customer.getCusSurname().trim());

		if (cust == null) {
			resp.setRequestStatus(true);
			resp.setPayload(cust);
			resp.setMessage("Customer not found");
		} else {
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
				// check customer status
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
						resp.setMessage("Server Error. Please try again later.");
						resp.setRequestStatus(true);
						System.err.println(e.toString());
					} catch (Exception e) {
						resp.setMessage("Server Error. Please try again later.");
						resp.setRequestStatus(true);
						System.err.println(e.toString());
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
				// check customer status
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
						resp.setMessage("Server Error. Please try again later.");
						resp.setRequestStatus(true);
						System.err.println(e.toString());
					} catch (Exception e) {
						resp.setMessage("Server Error. Please try again later.");
						resp.setRequestStatus(true);
						System.err.println(e.toString());
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
		Page<Customers> cus = customerRepository.findByCusSurname(cust.getCusSurname().trim(), pageable);

		if (cus == null) {
			resp.setMessage("Customer not found");
			resp.setRequestStatus(true);
		} else {
			try {
				if (!cus.hasContent()) {
					resp.setPayload(cus);
					resp.setRequestStatus(true);
					resp.setMessage("Customer not found");
				} else {
					resp.setPayload(cus);
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

}
