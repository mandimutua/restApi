package com.ipo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	

}
