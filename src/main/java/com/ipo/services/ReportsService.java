package com.ipo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestResponseObject;
import com.ipo.entities.Batch;
import com.ipo.repositories.ApplicationRepository;


@Service
public class ReportsService {
	
	
	
	@Autowired
	private ApplicationRepository appRepository;
	
	
	public RestResponseObject fetchall(Batch bat, Pageable pageable) {
		RestResponseObject resp = new RestResponseObject();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
		
				// resp.setMessage("entered not null"+bat.getBatBrkCode());
				resp.setPayload(appRepository.findSpecificForReports(bat.getBatBrkCode(),bat.getBatNumber(), pageable));
				resp.setRequestStatus(true);
				resp.setMessage("Success");
			

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);

		}
		return resp;
	}

}
