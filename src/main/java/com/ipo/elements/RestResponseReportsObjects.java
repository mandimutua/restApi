package com.ipo.elements;

import java.math.BigInteger;

public class RestResponseReportsObjects {

	public RestResponseReportsObjects() {
	}

	public String message;
	private Object payload;
	private boolean requestStatus;
	private BigInteger totalAmountShares, totalAmountPayments, totalAmountBatch;
	private String brkName, custName;

	public BigInteger getTotalAmountShares() {
		return totalAmountShares;
	}

	public void setTotalAmountShares(BigInteger totalAmountShares) {
		this.totalAmountShares = totalAmountShares;
	}

	public BigInteger getTotalAmountPayments() {
		return totalAmountPayments;
	}

	public void setTotalAmountPayments(BigInteger totalAmountPayments) {
		this.totalAmountPayments = totalAmountPayments;
	}

	public BigInteger getTotalAmountBatch() {
		return totalAmountBatch;
	}

	public void setTotalAmountBatch(BigInteger totalAmountBatch) {
		this.totalAmountBatch = totalAmountBatch;
	}

	public String getBrkName() {
		return brkName;
	}

	public void setBrkName(String brkName) {
		this.brkName = brkName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public boolean isRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(boolean requestStatus) {
		this.requestStatus = requestStatus;
	}
}
