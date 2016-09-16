package com.ipo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Banks;
import com.ipo.repositories.BankRepository;

@Service
public class BankService {
	
	@Autowired
	private BankRepository bankRepo;
	
	public RestResponseObject listall(Banks bnk, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			
				resp.setPayload(bankRepo.findAll(pageable));
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			
			
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			resp.setRequestStatus(true);
			System.err.println(e.toString());
		}
		return resp;
	}


}
