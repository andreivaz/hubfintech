package com.hubfintech.enums;

public enum ReturnCode {
	APROVADA("00"), 
	SALDO_INSUFICIENTE("51"),
	CONTA_INVALIDA("14"),
	ERRO_PROCESSAMENTO("96");
	
	private ReturnCode(String codigoRetorno) {
		this.codRetorno = codigoRetorno;
	}
	
	private String codRetorno;
	
	public String getCodigoRetorno() {
		return codRetorno;
	}
}
