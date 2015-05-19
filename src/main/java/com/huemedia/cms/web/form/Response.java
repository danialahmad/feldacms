package com.huemedia.cms.web.form;

import java.io.Serializable;

public class Response implements Serializable{
	private boolean success=true;
	private String message;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
