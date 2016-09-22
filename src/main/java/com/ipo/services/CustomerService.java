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

	public RestResponseObject create(Customers req) throws NullPointerException {
		Customers cus = new Customers();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating customer");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			cus.setCusCdscAcNo(req.getCusCdscAcNo().trim().toUpperCase());
			cus.setCusCitizenship(req.getCusCitizenship().trim().toUpperCase());
			cus.setCusApplicantType(req.getCusApplicantType().trim().toUpperCase());
			
			cus.setCusTaxExmpt(req.getCusTaxExmpt().trim().toUpperCase());
			
			cus.setCusStatus(BigInteger.valueOf(2));
			cus.setCusCdate(Calendar.getInstance().getTime());
			cus.setCusInputter(req.getCusInputter());
			cus.setCusDate(Calendar.getInstance().getTime());
			cus.setcusFormSerialNo(req.getcusFormSerialNo());
			cus.setCusApplicantResidence(req.getCusApplicantResidence().trim().toUpperCase());
			cus.setCusBrkCode(req.getCusBrkCode());
			cus.setCusApplicantCategory(req.getCusApplicantCategory().trim().toUpperCase());
			
			if (req.getCusApplicantCategory().trim().equalsIgnoreCase("INDIVIDUAL"))
			{
				cus.setCusSurname(req.getCusSurname().trim().toUpperCase());
				cus.setCusOtherNames(req.getCusOtherNames().trim().toUpperCase());
				cus.setCusIdNo(req.getCusIdNo().trim().toUpperCase());
			}
			else if(req.getCusApplicantCategory().trim().equalsIgnoreCase("JOINT"))
			{
				cus.setCusSurname(req.getCusSurname().trim().toUpperCase());
				cus.setCusOtherNames(req.getCusOtherNames().trim().toUpperCase());
				cus.setCusIdNo(req.getCusIdNo().trim().toUpperCase());
				cus.setCusJointSurname(req.getCusJointSurname().trim().toUpperCase());
				cus.setCusJointOthernames(req.getCusJointOthernames().trim().toUpperCase());
				cus.setCusJointApplicantIdNo(req.getCusJointApplicantIdNo().trim().toUpperCase());
			}else if (req.getCusApplicantCategory().trim().equalsIgnoreCase("COMPANY"))
			{
				cus.setCusCompanyName(req.getCusCompanyName().trim().toUpperCase());
				cus.setCusCompanyRegNo(req.getCusCompanyRegNo().trim().toUpperCase());
				cus.setCusCompanyDateOfInc(req.getCusCompanyDateOfInc());
				
			}
			else if (req.getCusApplicantCategory().trim().equalsIgnoreCase("NOMINEE"))
			{
				cus.setCusNomineeName(req.getCusNomineeName().trim().toUpperCase());
				cus.setCusNomineeAc(req.getCusNomineeAc());
			}
			
			 if (req.getCusCountry()!=null)
			{
				cus.setCusCountry(req.getCusCountry().trim().toUpperCase());
			}
				
			 if (req.getCusTown()!=null)
			{
				cus.setCusTown(req.getCusTown().trim().toUpperCase());
			}
			
			 if (req.getCusAddress()!=null)
			{
				cus.setCusAddress(req.getCusAddress().trim().toUpperCase());
			}
			
			 if (req.getCusMobilePhone()!=null)
			{
				cus.setCusMobilePhone(req.getCusMobilePhone().trim().toUpperCase());
			}
				
			 if (req.getCusEmail()!=null)
			{
				cus.setCusEmail(req.getCusEmail().trim());
			}
			
			 if (req.getCusPostalCode()!=null)
			{
				cus.setCusPostalCode(req.getCusPostalCode().trim());
			}
			
			 if (req.getCusStreet()!=null)
			{
				cus.setCusStreet(req.getCusStreet().trim().toUpperCase());
			}
			
			 if (req.getCusTelNo()!=null)
			{
				cus.setCusTelNo(req.getCusTelNo().trim().toUpperCase());
			}
			
			 if (req.getCusFaxNo()!=null)
			{
				cus.setCusFaxNo(req.getCusFaxNo().trim().toUpperCase());
			}
			
		
		
			Customers createdcustomer = customerRepository.save(cus);
			resp.setMessage("Success");
			resp.setPayload(createdcustomer);
			resp.setRequestStatus(true);
			
			
		}catch(NullPointerException e)
		{
			resp.setMessage("Server Error. Please try again later.");
			System.out.println("Null Pointer Being Caught");
			resp.setMessage(e.toString());

		
			
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
					cust.setCusCitizenship(req.getCusCitizenship().trim().toUpperCase());
					cust.setCusApplicantType(req.getCusApplicantType().trim().toUpperCase());
					
					cust.setCusTaxExmpt(req.getCusTaxExmpt().trim().toUpperCase());
					
					cust.setCusStatus(BigInteger.valueOf(2));
					cust.setCusCdate(Calendar.getInstance().getTime());
					cust.setCusInputter(req.getCusInputter());
					cust.setCusDate(Calendar.getInstance().getTime());
					cust.setcusFormSerialNo(req.getcusFormSerialNo());
					cust.setCusApplicantResidence(req.getCusApplicantResidence().trim().toUpperCase());
					cust.setCusBrkCode(req.getCusBrkCode());
					cust.setCusApplicantCategory(req.getCusApplicantCategory().trim().toUpperCase());
					
					if (req.getCusApplicantCategory().trim().equalsIgnoreCase("INDIVIDUAL"))
					{
						cust.setCusSurname(req.getCusSurname().trim().toUpperCase());
						cust.setCusOtherNames(req.getCusOtherNames().trim().toUpperCase());
						cust.setCusIdNo(req.getCusIdNo().trim().toUpperCase());
					}
					else if(req.getCusApplicantCategory().trim().equalsIgnoreCase("JOINT"))
					{
						cust.setCusSurname(req.getCusSurname().trim().toUpperCase());
						cust.setCusOtherNames(req.getCusOtherNames().trim().toUpperCase());
						cust.setCusIdNo(req.getCusIdNo().trim().toUpperCase());
						cust.setCusJointSurname(req.getCusJointSurname().trim().toUpperCase());
						cust.setCusJointOthernames(req.getCusJointOthernames().trim().toUpperCase());
						cust.setCusJointApplicantIdNo(req.getCusJointApplicantIdNo().trim().toUpperCase());
					}else if (req.getCusApplicantCategory().trim().equalsIgnoreCase("COMPANY"))
					{
						cust.setCusCompanyName(req.getCusCompanyName().trim().toUpperCase());
						cust.setCusCompanyRegNo(req.getCusCompanyRegNo().trim().toUpperCase());
						cust.setCusCompanyDateOfInc(req.getCusCompanyDateOfInc());
						
					}
					else if (req.getCusApplicantCategory().trim().equalsIgnoreCase("NOMINEE"))
					{
						cust.setCusNomineeName(req.getCusNomineeName().trim().toUpperCase());
						cust.setCusNomineeAc(req.getCusNomineeAc());
					}
					
					 if (req.getCusCountry()!=null)
					{
						 cust.setCusCountry(req.getCusCountry().trim().toUpperCase());
					}
						
					 if (req.getCusTown()!=null)
					{
						 cust.setCusTown(req.getCusTown().trim().toUpperCase());
					}
					
					 if (req.getCusAddress()!=null)
					{
						 cust.setCusAddress(req.getCusAddress().trim().toUpperCase());
					}
					
					 if (req.getCusMobilePhone()!=null)
					{
						 cust.setCusMobilePhone(req.getCusMobilePhone().trim().toUpperCase());
					}
						
					 if (req.getCusEmail()!=null)
					{
						 cust.setCusEmail(req.getCusEmail().trim());
					}
					
					 if (req.getCusPostalCode()!=null)
					{
						 cust.setCusPostalCode(req.getCusPostalCode().trim());
					}
					
					 if (req.getCusStreet()!=null)
					{
						 cust.setCusStreet(req.getCusStreet().trim().toUpperCase());
					}
					
					 if (req.getCusTelNo()!=null)
					{
						 cust.setCusTelNo(req.getCusTelNo().trim().toUpperCase());
					}
					
					 if (req.getCusFaxNo()!=null)
					{
						 cust.setCusFaxNo(req.getCusFaxNo().trim().toUpperCase());
					}
												
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
						System.out.println("Authorizer being sent"+r.getCusAuthoriser());
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
