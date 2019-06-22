package com.megatravel.gateway.utils;


public class ServiceResponse {

	private String body;
	private int status;
	
	public ServiceResponse() { }

	public ServiceResponse(String body, int status) {
		super();
		this.body = body;
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
