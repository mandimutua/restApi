package com.ipo.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipo.elements.BrokerageRow;
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
//			BigInteger batchTotalAmount = BigInteger.ZERO;
//
//			SystemParameters params = sysParamRepository.findByParamName("SharePrice".trim());
//			int share_price = Integer.parseInt(params.getParamValue1());
//
//			Page<Application> theList = appRepository.findSpecificForReports(bat.getBatBrkCode(), bat.getBatNumber(),
//					pageable);

//			for (Application app : theList) {
//				batchTotalAmount = app.getAppBatCode().getBatTotalShares();
//			}
			resp.setPayload(appRepository.findSpecificForReports(bat.getBatBrkCode(), bat.getBatNumber(), pageable));

		//	resp.setTotalAmountBatch(batchTotalAmount.multiply(BigInteger.valueOf(share_price)));
			resp.setRequestStatus(true);
			resp.setMessage("Success");

		} catch (Exception e) {
			resp.setMessage("Server Error. Please try again later.");
			System.err.println(e.toString());
			e.printStackTrace();
			resp.setRequestStatus(true);

		}
		return resp;
	}

	public RestResponseReportsObjects brokersReport(Pageable pageable) {
		RestResponseReportsObjects resp = new RestResponseReportsObjects();
		resp.setMessage("Not Found");
		resp.setPayload(null);
		resp.setRequestStatus(false);
		BigInteger totalAmountApplied = BigInteger.ZERO;
		BigInteger totalSharesApplies = BigInteger.ZERO;
		int totalBatchesApplied = 0;
		int totalApplicationsApplied = 0;
		
		List<Object> reports = new ArrayList<>();

		try {
			List<Brokers> brokerList = (List<Brokers>) brokersRepository.findAll();
			SystemParameters params = sysParamRepository.findByParamName("SharePrice".trim());
			int share_price = Integer.parseInt(params.getParamValue1());
			
			for (Brokers b : brokerList) {
				int totalBatches = 0, totalApplications = 0;
				BigInteger totalShares = BigInteger.ZERO;
				BigInteger totalAmount = BigInteger.ZERO;
				
			
				
				BrokerageRow row = new BrokerageRow();
				
				System.err.println("----------Broker Name:"+b.getBrkName()+"------");
				row.setBrokerName(b.getBrkName());
				
				List<Batch> batList = batRepository.findSpecificUnpaginated(b);
				totalBatches = batList.size();
				System.err.println("----------Batch size:"+totalBatches+"------");
				totalBatchesApplied = totalBatchesApplied+batList.size();
				row.setBatchSize(String.valueOf(totalBatches));
				
				for (Batch bat : batList) {		
					List<Application> appList = appRepository.findBatchSizeForReports(bat);
					totalApplications = appList.size();
					System.err.println("----------Total applications:"+totalApplications+"------");
					totalApplicationsApplied = totalApplicationsApplied+appList.size();
					row.setTotalApplications(String.valueOf(totalApplications));
				
					for (Application app : appList) {
						totalShares = totalShares.add(app.getAppSharesApplied());
						System.err.println("----------Total shares:"+app.getAppSharesApplied()+"------");
						totalSharesApplies =totalSharesApplies.add(app.getAppSharesApplied());
						row.setTotalShares(String.valueOf(totalShares));
					}
					totalAmount = totalShares.multiply(BigInteger.valueOf(share_price));
					System.err.println("----------Total Amount:"+totalAmount.toString()+"------");	
					row.setTotalAmount(totalAmount.toString());
				}
				
				System.out.println("----------Total Shares Applied:"+totalSharesApplies.toString()+"------");	
				totalAmountApplied = totalSharesApplies.multiply(BigInteger.valueOf(share_price));
				System.out.println("----------Total Amount Applied:"+totalAmountApplied.toString()+"------");
				System.out.println("----------Total Batches Applied:"+String.valueOf(totalBatchesApplied)+"------");
				System.out.println("----------Total Applications Applied:"+String.valueOf(totalApplicationsApplied)+"------");
				
				reports.add(row);
			}
			
			BrokerageRow totalRow = new BrokerageRow();
			totalRow.setBrokerName("TOTALS");
			totalRow.setBatchSize(String.valueOf(totalBatchesApplied));
			totalRow.setTotalApplications(String.valueOf(totalApplicationsApplied));
			totalRow.setTotalAmount(totalAmountApplied.toString());
			totalRow.setTotalShares(totalSharesApplies.toString());
			
			reports.add(totalRow);
			
			System.out.print("-------");
			resp.setPayload(reports);
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