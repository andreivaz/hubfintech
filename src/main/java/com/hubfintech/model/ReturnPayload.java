package com.hubfintech.model;

import com.fasterxml.jackson.annotation.JsonAlias;


public class ReturnPayload {
	private final String action = "withdraw";
	private String code;
	@JsonAlias("authorization_code")
	private String authorizationCode;
	

	public ReturnPayload(String code, String authorizationCode) {
		this.code = code;
		this.authorizationCode = authorizationCode;
	}
	
	public String getAction() {
		return action;
	}
	public String getCode() {
		return code;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	
	
	
	
}
