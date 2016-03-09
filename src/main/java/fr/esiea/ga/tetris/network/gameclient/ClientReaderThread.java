package fr.esiea.ga.tetris.network.gameclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import fr.esiea.ga.tetris.network.communication.NetworkReaderInterface;

public class ClientReaderThread implements Runnable, NetworkReaderInterface {

	String msg;
	BufferedReader in;
	Socket socket;

	public ClientReaderThread (Socket socket,BufferedReader in) {
		this.in = in; 
		this.socket = socket;
	}

	public void run() {
		try {
			readSocketInput(socket,in);
			closeStreams(socket,in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readSocketInput (Socket socket, BufferedReader in) throws IOException {
		msg = in.readLine();
		while(msg != null && !msg.equals("quit")){
			System.out.println("Serveur : "+msg);
			msg = in.readLine();
		}
	}
	
	public void closeStreams(Socket socket, BufferedReader in, PrintWriter out) throws IOException {
		socket.close();
		in.close();
		System.out.println("System - Connexion fermée côté client");
	}

	public void closeStreams(Socket socket, BufferedReader in) throws IOException {
		in.close();
		socket.close();
	}
	
}