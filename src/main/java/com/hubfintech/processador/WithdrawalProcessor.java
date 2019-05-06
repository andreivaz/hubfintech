package com.hubfintech.processador;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubfintech.enums.ReturnCode;
import com.hubfintech.model.Payload;
import com.hubfintech.model.ReturnPayload;
import com.hubfintech.service.TransactionServiceImpl;
import com.hubfintech.tcp.CustomerTask;


@Component
public class WithdrawalProcessor implements Runnable { 
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WithdrawalProcessor.class);

	@Autowired
	private TransactionServiceImpl transacaoService;
	
	private Socket socket;
	
	public WithdrawalProcessor() {super();}
	
	public WithdrawalProcessor(Socket socket) {
		this.socket = socket;
	}
	
	
	@Override
	public void run() {
		PrintStream saida = null;
		ReturnCode codRetorno = ReturnCode.ERRO_PROCESSAMENTO;
		try {
			log.info("Conectado na porta {}", socket.getPort());

			Scanner entrada;

			entrada = new Scanner(socket.getInputStream());
			Payload retirada = null;
			ReturnPayload retorno = null;
			saida = new PrintStream(socket.getOutputStream());
		
			ObjectMapper mapper = new ObjectMapper();
		
			if (entrada.hasNextLine()) {
				
				retirada = mapper.readValue(entrada.nextLine(), Payload.class);
				retorno = transacaoService.debitar(retirada);
				log.info(retirada.toString());
			}

			saida.println(mapper.writeValueAsString(retorno));
			
			entrada.close();
			log.info("Terminou processo");
		} catch (IOException e) {
			saida.println(codRetorno.getCodigoRetorno());
			log.info("Houve um erro ao processar a transferencia");
		}
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
