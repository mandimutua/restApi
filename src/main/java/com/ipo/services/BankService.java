package com.ipo.services;

import java.util.List;

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
			
				resp.setPayload(bankRepo.findAll());
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			
			
		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			resp.setRequestStatus(true);
			System.err.println(e.toString());
		}
		return resp;
	}

	public RestResponseObject filterBanks(Banks bnk) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setRequestStatus(false);
		List<Banks> bank = bankRepo.findByBankName(bnk.getBankName().trim());

		if (bank == null) {
			resp.setRequestStatus(true);
			resp.setPayload(bank);
			resp.setMessage("Bank not found");
		} else {
			resp.setRequestStatus(true);
			resp.setPayload(bank);
			resp.setMessage("Success");
		}

		return resp;
	}
	

}
