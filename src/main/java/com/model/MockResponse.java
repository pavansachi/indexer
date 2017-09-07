package com.model;

/**
 * mock response
 * @author pavansachi
 *
 */
public class MockResponse {

	private String data;
	private int status;
	
	public String getData() {
		return data;
	}

	public int getStatus() {
		return status;
	}

	public MockResponse(String data, int status) {
		super();
		this.data = data;
		this.status = status;
	}
	
	public MockResponse(int status) {
		super();
		this.status = status;
	}
	
}
