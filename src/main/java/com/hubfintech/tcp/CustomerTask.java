package com.hubfintech.tcp;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class CustomerTask {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomerTask.class);


	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 9999)) {
			log.info("Connection established.");

			PrintStream out = new PrintStream(socket.getOutputStream());

			out.println("{\"action\": \"withdraw\",\"cardnumber\":\"3333444455556666\",\"amount\": \"10\"}");
			
			Scanner serverReturn = new Scanner(socket.getInputStream());
			while(serverReturn.hasNextLine()) {
				log.info("Return code = " + serverReturn.nextLine());
			}
		    
			serverReturn.close();
			out.close();
		} catch (Exception e) {}
	}
}
