package fr.esiea.ga.tetris.network.gameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerReaderThread extends Thread{

	String msg;
	BufferedReader in;
	PrintWriter out;
	Socket socket;

	public ServerReaderThread (Socket socket,BufferedReader in, PrintWriter out) {
		this.in = in; 
		this.out = out;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			msg = in.readLine();
			while(msg != null && !msg.equals("quit")){			// Si le client se d�connecte
				System.out.println("Client : "+msg);
				msg = in.readLine();
			}
			out.close();
			socket.close();
			System.out.println("System - Connexion ferm�e c�t� serveur");
		} catch (IOException e) {
			System.out.println("Probl�me de communication Serveur-Client");
		}

	}

}