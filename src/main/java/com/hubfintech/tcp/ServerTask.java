package com.hubfintech.tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubfintech.processador.WithdrawalProcessor;


@Service
public class ServerTask {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ServerTask.class);

	@Autowired
	WithdrawalProcessor processadorSaques;
	
	public void start(){
		log.info("-- Iniciando servidor socket --");
		try (ServerSocket serverSocket = new ServerSocket(1234)) {

			ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
			while (true) {
				Socket socket = serverSocket.accept();
				processadorSaques.setSocket(socket);
				newCachedThreadPool.execute(processadorSaques);
			}
		} catch (Exception e) {
			log.info("Falha no processamento");
		}
	}

}
