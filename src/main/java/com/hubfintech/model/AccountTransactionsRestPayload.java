package com.hubfintech.model;

import java.math.BigDecimal;
import java.util.List;

import com.hubfintech.model.Transaction;

public class AccountTransactionsRestPayload {
	private String cardnumber;
	private BigDecimal availableAmount;
	private List<Transaction> transactions;
	
	
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public BigDecimal getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(BigDecimal availableAmount) {
		this.availableAmount = availableAmount;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
