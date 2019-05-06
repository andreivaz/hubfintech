package com.hubfintech.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hubfintech.model.Transaction;

@Repository
public class TransactionRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Long associarTransacao(String cardNumber, String amount) {
		String insertSQL = "insert into TRANSACTION (debit_date, amount, card_number) values (CURRENT_TIMESTAMP(), ?, ?);";
		
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, amount);
				ps.setString(2, cardNumber);
				return ps;
			}
		}, holder);

		return holder.getKey().longValue();
	}
	
	public List<Transaction> getTransacoes(String cardNumber) {
		String sql = "SELECT DEBIT_DATE, AMOUNT FROM TRANSACTION WHERE CARD_NUMBER = ? limit 10";
		 
		List<Transaction> transacoes = new ArrayList<>();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(
				sql,  cardNumber);
		
		for(Map<String, Object> row : rows) {
			Transaction t = new Transaction();
			
			t.setDate((Timestamp)row.get("DEBIT_DATE"));
			t.setAmount((BigDecimal)row.get("AMOUNT"));
			transacoes.add(t);
		}
			
		return transacoes;
	}
	
}
