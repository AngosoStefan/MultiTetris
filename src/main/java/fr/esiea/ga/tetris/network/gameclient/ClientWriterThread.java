package fr.esiea.ga.tetris.network.gameclient;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriterThread extends Thread {

	String msg;
	Socket socket;
	PrintWriter out;
	Scanner sc;
	
	public ClientWriterThread (Socket socket, PrintWriter out, Scanner sc) {
		this.socket = socket;
		this.out = out;
		this.sc = sc;
	}
   
	@Override
   public void run() {
	   while(true){
		   msg = sc.nextLine();
		   out.println(msg);
		   out.flush();
	   }
   }
	
}