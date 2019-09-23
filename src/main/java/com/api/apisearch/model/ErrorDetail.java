package com.api.apisearch.model;

public class ErrorDetail {

	private int status;
	private String message;
	private String detailsMessage;

	public ErrorDetail(int status, String message, String detailsMessage) {
		super();
		this.status = status;
		this.message = message;
		this.detailsMessage = detailsMessage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailsMessage() {
		return detailsMessage;
	}

	public void setDetailsMessage(String detailsMessage) {
		this.detailsMessage = detailsMessage;
	}

	@Override
	public String toString() {
		return "ErrorDetail [status=" + status + ", message=" + message + ", detailsMessage=" + detailsMessage + "]";
	}

}
