package com.ipo.elements;

public class BrokerageRow {
	
	private String brokerName, batchSize,
	totalAmount, totalShares, totalApplications;

	public String getBrokerName() {
		return brokerName;
	}

	public String getTotalApplications() {
		return totalApplications;
	}

	public void setTotalApplications(String totalApplications) {
		this.totalApplications = totalApplications;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalShares() {
		return totalShares;
	}

	public void setTotalShares(String totalShares) {
		this.totalShares = totalShares;
	}
	

}
