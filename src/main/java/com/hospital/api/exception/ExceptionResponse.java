package com.hospital.api.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
	
	private Date timestamp;
	private String message;
	private String detials;
	
	public ExceptionResponse() {
	}

	public ExceptionResponse(Date timestamp, String message, String detials) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.detials = detials;
	}
	
	

}
