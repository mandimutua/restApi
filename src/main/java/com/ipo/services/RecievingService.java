package com.ipo.services;

import java.math.BigInteger;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestRequestObject;
import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Batch;
import com.ipo.entities.Recieving;
import com.ipo.repositories.RecievingRepository;

@Service
public class RecievingService {
	@Autowired
	private RecievingRepository recievingRepository;
	
	public RestResponseObject listall(Recieving recieving, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Recieving Batch Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			resp.setPayload(recievingRepository.findAll(pageable));
			resp.setRequestStatus(true);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);
		}
		return resp;
	}
	
	public RestResponseObject create(RestRequestObject<Recieving[]> req) {
		
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating Recieving Batch");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		
		try {
			
			for (Recieving r : req.getObject())
			{
			Recieving ref = new Recieving();	
			ref.setRcvBatCode(r.getRcvBatCode());
			ref.setRcvStatus(BigInteger.valueOf(2));
			ref.setRcvCdate(Calendar.getInstance().getTime());
			ref.setRcvDate(Calendar.getInstance().getTime());
			ref.setRcvInputter(r.getRcvInputter());
			Recieving createdRecieving = recievingRepository.save(ref);
			resp.setMessage("Success");
			resp.setPayload(createdRecieving);
			resp.setRequestStatus(true);
			}
		} catch (Exception er) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(er.toString());
			resp.setRequestStatus(true);

		}
		return resp;
	}

	public RestResponseObject fetchall(Batch bat, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
		
				// resp.setMessage("entered not null"+bat.getBatBrkCode());
				resp.setPayload(recievingRepository.findSpecificForRecieving(bat.getBatBrkCode(),bat.getBatNumber(), pageable));
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);

		}
		return resp;
	}

	public RestResponseObject approve(RestRequestObject<Recieving[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		for (Recieving r : req.getObject()) {
			System.out.println("Length of Object" + req.getObject().length);

			Recieving rcv = recievingRepository.findByRcvBatCode(r.getRcvBatCode());
			if (rcv == null) {
				resp.setMessage("Recieving Batch not found");
				resp.setRequestStatus(true);
			} else {
				if (rcv.getRcvStatus() == BigInteger.valueOf(2)) {
					try {
						rcv.setRcvStatus(BigInteger.valueOf(1));
						rcv.setRcvAuthoriser(r.getRcvAuthoriser());
						rcv.setRcvMdate(Calendar.getInstance().getTime());
						rcv.setRcvDate(Calendar.getInstance().getTime());
						resp.setMessage("Recieving Batch Approval Successfull");
						resp.setRequestStatus(true);
						recievingRepository.save(rcv);

					} catch (Exception e) {

						resp.setMessage("Server Error. Please try again later.");
						System.err.println(e.toString());
						resp.setRequestStatus(true);
					}
				} else {
					resp.setMessage("Receiving Batch has not been marked for approval");
				}
			}
		}

		return resp;

	}
	
	public RestResponseObject reject(RestRequestObject<Recieving[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		for (Recieving r : req.getObject()) {
			System.out.println("Length of Object" + req.getObject().length);

			Recieving rcv = recievingRepository.findByRcvBatCode(r.getRcvBatCode());
			if (rcv == null) {
				resp.setMessage("Recieving Batch not found");
				resp.setRequestStatus(true);
			} else {
				if (rcv.getRcvStatus() == BigInteger.valueOf(2)) {
					try {
						rcv.setRcvStatus(BigInteger.valueOf(3));
						rcv.setRcvAuthoriser(r.getRcvAuthoriser());
						rcv.setRcvMdate(Calendar.getInstance().getTime());
						rcv.setRcvDate(Calendar.getInstance().getTime());
						resp.setMessage("Recieving Batch Rejection Successfull");
						resp.setRequestStatus(true);
						recievingRepository.save(rcv);

					} catch (Exception e) {

						resp.setMessage("Server Error. Please try again later.");
						System.err.println(e.toString());
						resp.setRequestStatus(true);
					}
				} else {
					resp.setMessage("Receiving Batch has not been marked for rejection");
				}
			}
		}

		return resp;

	}

}
