package com.ipo.services;

import java.math.BigDecimal;
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
import com.ipo.entities.Application;
import com.ipo.entities.Batch;
import com.ipo.entities.Customers;
import com.ipo.entities.Payments;
import com.ipo.entities.SystemParameters;
import com.ipo.repositories.ApplicationRepository;
import com.ipo.repositories.BatchRepository;
import com.ipo.repositories.PaymentRepository;
import com.ipo.repositories.SystemParamsRepository;

@Service
public class BatchService {

	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private PaymentRepository payRepository;
		
	@Autowired
	private ApplicationRepository appRepository;
	
	@Autowired
	private SystemParamsRepository sysParamRepository;
	
	public RestResponseObject listall(Batch broker, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			resp.setPayload(batchRepository.findAll(pageable));
			resp.setRequestStatus(true);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}

	public RestResponseObject create(Batch req) {
		Batch bth = new Batch();
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating batch");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			bth.setBatCdate(Calendar.getInstance().getTime());
			bth.setBatCreateDate(Calendar.getInstance().getTime());
			bth.setBatTotalShares(req.getBatTotalShares());
			bth.setBatStatus(BigInteger.valueOf(2));
			bth.setBatBrkCode(req.getBatBrkCode());
			bth.setBatInputter(req.getBatInputter());
			bth.setBatDate(Calendar.getInstance().getTime());
			Batch createdbatch = batchRepository.save(bth);
			resp.setMessage("Success");
			resp.setPayload(createdbatch);
			resp.setRequestStatus(true);
		} catch (Exception er) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(er.toString());
			resp.setRequestStatus(true);

		}
		return resp;
	}

	public RestResponseObject edit(Batch req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Editting Batch");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Batch bth = batchRepository.findByBatCode(req.getBatCode());

		try {

			if (bth == null) {
				resp.setMessage("Batch not found");
				resp.setRequestStatus(true);
			} else {
				// check batch status
				if (bth.getBatStatus() == BigInteger.valueOf(2) | bth.getBatStatus() == BigInteger.valueOf(1)) {
					bth.setBatTotalShares(req.getBatTotalShares());
					bth.setBatMdate(Calendar.getInstance().getTime());
					bth.setBatDate(Calendar.getInstance().getTime());
					bth.setBatInputter(req.getBatInputter());
					bth.setBatStatus(req.getBatStatus());
					resp.setMessage("Batch Edit Successfull");
					Batch createdbroker = batchRepository.save(bth);
					resp.setPayload(createdbroker);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("Batch is Inactive");
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

	public RestResponseObject approve(RestRequestObject<Batch[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Batch");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {

			for (Batch r : req.getObject()) {
				Batch bth = batchRepository.findByBatCode(r.getBatCode());
				
								
				
				if (bth == null) {
					resp.setMessage("Batch not found");
					resp.setRequestStatus(true);
				} else {
					// check batch status
					if (bth.getBatStatus() == BigInteger.valueOf(2)) {
						
						if(balance(r.getBatCode()))
						{
							bth.setBatDate(Calendar.getInstance().getTime());
							bth.setBatAuthoriser((r.getBatInputter()));
							bth.setBatMdate((Calendar.getInstance().getTime()));
							bth.setBatStatus(BigInteger.valueOf(1));
							resp.setMessage("Batch Approval Successfull");
							Batch createdbatch = batchRepository.save(bth);
							resp.setPayload(createdbatch);
							resp.setRequestStatus(true);
						}
						else
						{
							resp.setMessage("Batch and Applications does not balance");
							resp.setRequestStatus(true);
							
						}

						

					} else {
						resp.setMessage("Batch is not set for approval");
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
	
	public boolean balance(BigDecimal num) {

		Application app = new Application();
		Batch bat = new Batch();
		bat.setBatCode(num);
		app.setAppBatCode(bat);

		Customers cus = new Customers();

		bat = app.getAppBatCode();

		List<Application> apps = appRepository.findBatchSize(app.getAppBatCode());

		if (apps == null) {

			return false;

		} else {
			BigInteger sum = BigInteger.ZERO;
			BigInteger amt = BigInteger.ZERO;
			SystemParameters params = sysParamRepository.findByParamName("SharePrice".trim());
			int share_price = Integer.parseInt(params.getParamValue1());
			for (Application x : apps) {
				sum = sum.add(x.getAppSharesApplied());
				System.out.println("Shares Applied per Bat====================" + x.getAppSharesApplied());
				cus = (x.getAppCusPalCode());

				System.out.println("Customer pal codes of the same Batch" + cus.getCusPalCode());

				List<Payments> cust = payRepository.findAmount(cus.getCusPalCode());
				System.out.println("Printing Cust" + cust);

				for (Payments p : cust) {
					amt =amt.add(p.getPayAmount());
					System.out.println("Payamout===============" + p.getPayAmount());
				}

				// System.out.println("Customer pal codes of the same
				// Batch"+x.getAppCusPalCode());
			}
			System.out.println("Total Price Amount====================" + amt);
			System.out.println("Total shares====================" + sum);
			Batch batch = batchRepository.findByBatCode(bat.getBatCode());
			System.out.println("Created Batch Size========================" + batch.getBatTotalShares());
			System.out.println("System Param Share Price"+(params.getParamValue1()));
			//BigInteger x= amt.multiply(BigInteger.valueOf(100));
			BigInteger y = sum.multiply(BigInteger.valueOf(share_price));
			
			System.out.println(" balance value of y"+y);
			System.out.println(" balance value of x"+amt);
			
			if (sum.equals(batch.getBatTotalShares())) {
				if (amt.equals(y))
				{
					System.out.println("Zime balance value of y"+y);
					System.out.println("Zime balance value of x"+amt);
					return true;
					
				}
				else {
					return false;
				}
								
			} else {
				return false;
			}

		}
	}
	
	
	
	public RestResponseObject reject(RestRequestObject<Batch[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Rejecting Batch");
		resp.setPayload(null);
		resp.setRequestStatus(false);


			for (Batch r : req.getObject()) {
				Batch bth = batchRepository.findByBatCode(r.getBatCode());

				if (bth == null) {
					resp.setMessage("Batch not found");
					resp.setRequestStatus(true);
				} else {
					// check batch status
					if (bth.getBatStatus() == BigInteger.valueOf(2)) {

						try {

							bth.setBatDate(Calendar.getInstance().getTime());
							bth.setBatAuthoriser((r.getBatAuthoriser()));
							bth.setBatMdate((Calendar.getInstance().getTime()));
							bth.setBatStatus(BigInteger.valueOf(3));
							resp.setMessage("Batch Rejection Successfull");
							Batch createdbatch = batchRepository.save(bth);
							resp.setPayload(createdbatch);
							resp.setRequestStatus(true);
						} catch (DataIntegrityViolationException e) {
							resp.setMessage("Authorizer does not exist");
							resp.setRequestStatus(true);
						} catch (Exception e) {
							resp.setMessage("Server Error. Please try again later.");
							System.err.println(e.toString());
							resp.setRequestStatus(true);
						}

					} else {
						resp.setMessage("Batch is not set for approval");
						resp.setRequestStatus(true);
					}

				}
			}
		
		return resp;
	}

	public RestResponseObject searchbatch(Batch batch) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setRequestStatus(false);
		List<Batch> bat = (List<Batch>) batchRepository.findAllByOrderByBatCodeDesc();
		try {

			if (bat == null) {
				resp.setRequestStatus(true);
				resp.setPayload(bat);
				resp.setMessage("Batch not found");
			} else {

				resp.setRequestStatus(true);
				resp.setPayload(bat);
				resp.setMessage("Success");

			}

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}

		return resp;
	}
			
	public RestResponseObject search(Batch batch, Pageable pagable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Page<Batch> brk = batchRepository.findByBatCode(batch.getBatCode(), pagable);

		try {
			if (brk == null) {
				resp.setMessage("Batch not found");
				resp.setRequestStatus(true);
			} else {

				resp.setPayload(brk);
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
}
