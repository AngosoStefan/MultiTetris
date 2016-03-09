package fr.esiea.ga.tetris.network.gameclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import fr.esiea.ga.tetris.network.communication.NetworkWriterInterface;

public class ClientWriterThread implements Runnable, NetworkWriterInterface {

	String msg;
	Socket socket;
	PrintWriter out;

	public ClientWriterThread (Socket socket, PrintWriter out) {
		this.socket = socket;
		this.out = out;
	}

	/* Methode run du thread */
	
	public void run() {
		try {
			writeSocketOuput(socket,out);
			closeStreams(socket,out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Ecrit dans le flux de sortie */
	
	public void writeSocketOuput(Socket socket, PrintWriter out) {
		while(true){
			msg="Je suis 1 client";
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			out.println(msg);
			out.flush();
		}
	}

	/* Ferme les flux de sortie */
	
	public void closeStreams(Socket socket, PrintWriter out) throws IOException {
		socket.close();
		out.close();
		System.out.println("System - Connexion fermée côté client");
	}

}