package fr.esiea.ga.tetris.network.gameclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import fr.esiea.ga.tetris.network.communication.NetworkWriterInterface;
import fr.esiea.ga.tetris.network.messages.NetworkMessage;

public class ClientWriterThread implements Runnable, NetworkWriterInterface {

	private Socket socket;
	private PrintWriter out;
	private String msg;
	private ArrayBlockingQueue<NetworkMessage> sentMsgList = new ArrayBlockingQueue<NetworkMessage>(50);
	
	public ClientWriterThread (Socket socket, PrintWriter out) {
		this.socket = socket;
		this.out = out;
		msg = new String("0,0");
	}
	
	public ArrayBlockingQueue<NetworkMessage> getSentMsgList() {
		return sentMsgList;
	}

	
	/* Methode run du thread */

	public void run() {
		writeSocketOuput();
		closeStreams();
	}

	
	/* Ecrit dans le flux de sortie */

	public void writeSocketOuput() {
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			out.println(msg);
			out.flush();
		}
	}

	
	/* Ferme les flux de sortie */

	public void closeStreams() {
		try {
			out.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("System - Problème de fermeture des flux");
		}
		System.out.println("System - Connexion fermée côté client");
	}


}