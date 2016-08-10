package com.ipo.elements;

public class RestRequestObject<T> {
	private String token;
	private T object;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
}
