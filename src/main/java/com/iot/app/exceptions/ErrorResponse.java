package com.iot.app.exceptions;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BAD_REQUEST")
public class ErrorResponse {

	private int statusCode;
	// General error message about nature of error
	private String message;
	
	private String reason;

	// Specific errors in API request processing
	private List<String> details;

	

	public ErrorResponse(int statusCode, String message, String reason, List<String> details) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.reason = reason;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	

}