package com.ipo.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.ipo.entities.Brokers;


public class LoginResponse {
	private boolean loginStatus;
	private String sessID, LoginMessage, names;
	private BigDecimal userID;
	Brokers batCode;
	

	private BigInteger usr_status;
	private String usr_email;

	public String getUsr_email() {
		return usr_email;
	}

	public void setUsr_email(String usr_email) {
		this.usr_email = usr_email;
	}

	public BigInteger getUsr_status() {
		return usr_status;
	}

	public void setUsr_status(BigInteger usr_status) {
		this.usr_status = usr_status;
	}

	public BigDecimal getUserID() {
		return userID;
	}

	public void setUserID(BigDecimal userID) {
		this.userID = userID;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getSessID() {
		return sessID;
	}

	public void setSessID(String sessID) {
		this.sessID = sessID;
	}

	public String getLoginMessage() {
		return LoginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		LoginMessage = loginMessage;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}
	
	public Brokers getBatCode() {
		return batCode;
	}

	public void setBatCode(Brokers brokers) {
		this.batCode = brokers;
	}
}
