package com.hubfintech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubfintech.enums.ReturnCode;
import com.hubfintech.model.AccountTransactionsRestPayload;
import com.hubfintech.model.Payload;
import com.hubfintech.model.ReturnPayload;
import com.hubfintech.model.Transaction;
import com.hubfintech.repository.WithdrawalRepository;
import com.hubfintech.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	WithdrawalRepository retiradaRepo;

	@Autowired
	TransactionRepository transacaoRepo;

	@Override
	public ReturnPayload debitar(Payload payload) {

		try {
			if (!retiradaRepo.hasCard(payload.getCardnumber())) {
				return new ReturnPayload(ReturnCode.CONTA_INVALIDA.getCodigoRetorno(), null);
			}
			if (retiradaRepo.checkFund(payload.getCardnumber(), payload.getAmount())) {
				retiradaRepo.debit(payload.getCardnumber(), payload.getAmount());
				Long authCode = transacaoRepo.associarTransacao(payload.getCardnumber(), payload.getAmount());

				return new ReturnPayload(ReturnCode.APROVADA.getCodigoRetorno(), Long.toString(authCode));
			} else {
				return new ReturnPayload(ReturnCode.SALDO_INSUFICIENTE.getCodigoRetorno(), null);
			}
		} catch (Exception e) {
			return new ReturnPayload(ReturnCode.ERRO_PROCESSAMENTO.getCodigoRetorno(), null);
		}
	}

	public String mostrarUltimasDezTransacoes(String cardNumber) throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();

		List<Transaction> transacoes = transacaoRepo.getTransacoes(cardNumber);

		AccountTransactionsRestPayload restPayload = new AccountTransactionsRestPayload();

		if(!transacoes.isEmpty()) {
			restPayload.setAvailableAmount(retiradaRepo.getAvailableAmount(cardNumber));
		}
		
		restPayload.setCardnumber(cardNumber);
		restPayload.setTransactions(transacoes);

		return objMapper.writeValueAsString(restPayload);
	}

}
