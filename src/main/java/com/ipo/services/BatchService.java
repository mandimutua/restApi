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

import com.ipo.repositories.BatchRepository;

@Service
public class BatchService {

	@Autowired
	private BatchRepository batchRepository;

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
			er.printStackTrace();
		}
		return resp;
	}

	public RestResponseObject edit(Batch req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error creating Batch");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		Batch bth = batchRepository.findByBatCode(req.getBatCode());

		if (bth == null) {
			resp.setMessage("Batch not found");
			resp.setRequestStatus(true);
		} else {
			// check batch status
			if (bth.getBatStatus() == BigInteger.valueOf(2) | bth.getBatStatus() == BigInteger.valueOf(1)) {
				bth.setBatTotalShares(req.getBatTotalShares());
				bth.setBatDate(Calendar.getInstance().getTime());
				bth.setBatInputter(req.getBatInputter());
				bth.setBatStatus(BigInteger.valueOf(2));
				resp.setMessage("Batch Edit Successfull");
				Batch createdbroker = batchRepository.save(bth);
				resp.setPayload(createdbroker);
				resp.setRequestStatus(true);

			} else {
				resp.setMessage("Batch is Inactive");
				resp.setRequestStatus(true);
			}

		}

		return resp;
	}

	public RestResponseObject approve(RestRequestObject<Batch[]> req) {

		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Error Approving Batch");
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

					bth.setBatDate(Calendar.getInstance().getTime());
					bth.setBatAuthoriser((r.getBatInputter()));
					bth.setBatMdate((Calendar.getInstance().getTime()));
					bth.setBatStatus(BigInteger.valueOf(1));
					resp.setMessage("Batch Approval Successfull");
					Batch createdbatch = batchRepository.save(bth);
					resp.setPayload(createdbatch);
					resp.setRequestStatus(true);

				} else {
					resp.setMessage("Batch is not set for approval");
					resp.setRequestStatus(true);
				}

			}
		}
		return resp;
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
						resp.setMessage("Authorizer does not exist");
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
		//resp.setPayload(null);
		resp.setRequestStatus(false);
		List<Batch> bat = (List<Batch>) batchRepository.findAllByOrderByBatCodeDesc();
		
		if(bat==null)
		{
			resp.setRequestStatus(true);
			resp.setPayload(bat);
			resp.setMessage("Batch not found");
		}
		else
		{
//			if (batch.getBatStatus()==BigInteger.valueOf(1))
//			{
			resp.setRequestStatus(true);
			resp.setPayload(bat);
			resp.setMessage("Success");
			//}
		}	
			
		
		return resp;
	}

	public RestResponseObject search(Batch batch, Pageable pagable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);
	Page <Batch> brk = batchRepository.findByBatCode(batch.getBatCode(),pagable);

		if (brk == null) {
			resp.setMessage("Batch not found");
			resp.setRequestStatus(true);
		} else {
			
				resp.setPayload(brk);
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			}
		
		return resp;
	}
}
