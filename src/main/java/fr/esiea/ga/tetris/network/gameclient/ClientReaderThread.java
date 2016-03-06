package fr.esiea.ga.tetris.network.gameclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientReaderThread extends Thread{

	String msg;
	BufferedReader in;
	PrintWriter out;
	Socket socket;

	public ClientReaderThread (Socket socket,BufferedReader in, PrintWriter out) {
		this.in = in; 
		this.out = out;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			msg = in.readLine();
			while(msg != null && !msg.equals("quit")){
				System.out.println("Serveur : "+msg);
				msg = in.readLine();
			}
			out.close();
			socket.close();
			System.out.println("System - Connexion fermée côté client");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}