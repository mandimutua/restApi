package com.ipo.utils;

public class TokenClass {
	private String token, usrAuthSalt;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthSalt() {
		return usrAuthSalt;
	}

	public void setAuthSalt(String authSalt) {
		this.usrAuthSalt = authSalt;
	}
}
