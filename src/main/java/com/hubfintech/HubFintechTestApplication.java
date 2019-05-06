package com.hubfintech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hubfintech.tcp.ServerTask;


@SpringBootApplication
public class HubFintechTestApplication implements CommandLineRunner {

	@Autowired
	ServerTask servidorTarefas;
	
	public static void main(String[] args) {
		SpringApplication.run(HubFintechTestApplication.class, args);
	}
 
	
	@Override
    public void run(String... strings) throws Exception {
		servidorTarefas.start();
	}
}

