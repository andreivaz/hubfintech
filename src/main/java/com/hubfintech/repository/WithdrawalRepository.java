package com.hubfintech.repository;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WithdrawalRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void debit(String cardNumber, String amount) {
		String updateSql = "update card set available_amount = available_amount - ? where card_number = ?";
		jdbcTemplate.update(updateSql, amount, cardNumber);
	}
	
	public boolean checkFund(String cardNumber, String amount) {
		String sql = "select count(*) from card where card_number = ? and available_amount > ?";
		int count = jdbcTemplate.queryForObject(
                sql, new Object[] { cardNumber, amount }, Integer.class);
		
		return count > 0;
	}

	public boolean hasCard(String cardNumber) {
		String sql = "select count(*) from card where card_number = ?";
		int count = jdbcTemplate.queryForObject(
                sql, new Object[] { cardNumber }, Integer.class);
		
		return count > 0;
	}
	
	public BigDecimal getAvailableAmount(String cardNumber) {
		String sql = "select available_amount from card where card_number = ?";
		
		return jdbcTemplate.queryForObject(
                sql, new Object[] { cardNumber }, BigDecimal.class);
	}
}
