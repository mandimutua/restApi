package com.ipo.services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.RestResponseReportsObjects;
import com.ipo.entities.Application;
import com.ipo.entities.Batch;
import com.ipo.entities.Brokers;
import com.ipo.entities.SystemParameters;
import com.ipo.repositories.ApplicationRepository;
import com.ipo.repositories.BatchRepository;
import com.ipo.repositories.BrokersRepository;
import com.ipo.repositories.SystemParamsRepository;

@Service
public class ReportsService {

	@Autowired
	private ApplicationRepository appRepository;

	@Autowired
	private BrokersRepository brokersRepository;

	@Autowired
	private BatchRepository batRepository;

	@Autowired
	private SystemParamsRepository sysParamRepository;

	public RestResponseReportsObjects fetchall(Batch bat, Pageable pageable) {
		RestResponseReportsObjects resp = new RestResponseReportsObjects();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);

		try {
			BigInteger batchTotalAmount = BigInteger.ZERO;

			SystemParameters params = sysParamRepository.findByParamName("SharePrice".trim());
			int share_price = Integer.parseInt(params.getParamValue1());

			Page<Application> theList = appRepository.findSpecificForReports(bat.getBatBrkCode(), bat.getBatNumber(),
					pageable);

			for (Application app : theList) {
				batchTotalAmount = app.getAppBatCode().getBatTotalShares();
			}
			resp.setPayload(appRepository.findSpecificForReports(bat.getBatBrkCode(), bat.getBatNumber(), pageable));

			resp.setTotalAmountBatch(batchTotalAmount.multiply(BigInteger.valueOf(share_price)));
			resp.setRequestStatus(true);
			resp.setMessage("Success");

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			resp.setRequestStatus(true);

		}
		return resp;
	}

	public RestResponseReportsObjects brokersReport(Pageable pageable) {
		RestResponseReportsObjects resp = new RestResponseReportsObjects();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		int totalBatches, totalApplications;
		BigInteger totalShares = BigInteger.ZERO;
		BigInteger totalAmount = BigInteger.ZERO;

		try {
			List<Brokers> brokerList = (List<Brokers>) brokersRepository.findAll();

			for (Brokers b : brokerList) {
				System.out.println("----------Broker Name:"+b.getBrkName()+"------");
				
				List<Batch> batList = batRepository.findSpecificUnpaginated(b);
				totalBatches = batList.size();
				
				System.out.println("----------Batch size:"+totalBatches+"------");
				
				for (Batch bat : batList) {
					List<Application> appList = appRepository.findBatchSizeForReports(bat);
					totalApplications = appList.size();
					System.out.println("----------Total applications:"+totalApplications+"------");
					
					for (Application app : appList) {
						totalShares.add(app.getAppSharesApplied());
					}
					System.out.println("----------Total shares:"+totalShares.toString()+"------");
					
					totalAmount = totalShares.multiply(BigInteger.valueOf(100));
					System.out.println("----------Total Amount:"+totalAmount.toString()+"------");
					
					
				}

			}
			System.out.print("-------");
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
