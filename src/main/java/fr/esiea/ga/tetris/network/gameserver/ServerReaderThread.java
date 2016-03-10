package fr.esiea.ga.tetris.network.gameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import fr.esiea.ga.tetris.network.communication.NetworkReaderInterface;
import fr.esiea.ga.tetris.network.messages.NetworkMessage;

public class ServerReaderThread implements Runnable, NetworkReaderInterface{

	String msg;
	BufferedReader in;
	PrintWriter out;
	Socket socket;

	public ServerReaderThread (Socket socket,BufferedReader in) {
		this.in = in; 
		this.socket = socket;
	}

	
	/* Methode run du thread */
	
	public void run() {
		try {
			readSocketInput(socket,in);
			closeStreams(socket,in);
		} catch (IOException e) {
			System.out.println("Problème de communication Serveur-Client");
		}

	}

	
	/* Lit le flux en entrée */
	
	public void readSocketInput(Socket socket, BufferedReader in) throws IOException {
		msg = in.readLine();
		while(msg != null && !msg.equals("quit")){			// Si le client se déconnecte
			System.out.println("Client : "+msg);
			msg = in.readLine();
			
		}
		
	}

	
	/* Ferme le flux d'entrée et la connexion */
	
	public void closeStreams(Socket socket, BufferedReader in) throws IOException {
		in.close();
		socket.close();
		System.out.println("System - Connexion fermée côté serveur");
	}

}