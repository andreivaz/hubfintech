package com.hubfintech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hubfintech.service.TransactionServiceImpl;

@RestController
public class CardProcessorRestController {

	@Autowired
	TransactionServiceImpl transacaoService;

	@GetMapping(value = "/card/balance/{cardNumber}")
	public String retirar(@PathVariable String cardNumber) throws JsonProcessingException {

		return transacaoService.mostrarUltimasDezTransacoes(cardNumber);
	}
}
