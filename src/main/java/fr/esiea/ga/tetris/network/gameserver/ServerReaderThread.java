package fr.esiea.ga.tetris.network.gameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import fr.esiea.ga.tetris.network.communication.NetworkReaderInterface;
import fr.esiea.ga.tetris.network.communication.ReaderCloser;
import fr.esiea.ga.tetris.network.messages.NetworkMessage;

public class ServerReaderThread implements Runnable, NetworkReaderInterface{

	private BufferedReader in;
	private Socket socket;
	private String msg;
	private ArrayBlockingQueue<NetworkMessage> sharedMsgList;

	
	public ServerReaderThread (Socket socket,BufferedReader in, ArrayBlockingQueue<NetworkMessage> sharedMsgList) {
		this.in = in; 
		this.socket = socket;
		this.sharedMsgList = sharedMsgList;
		msg = new String("0,0");
	}


	/* Methode run du thread */

	public void run() {
		readSocketInput();
		ReaderCloser.closeStreams(socket,in);
	}


	/* Lit le flux en entrée */

	public void readSocketInput() {
		while(msg != null && !msg.equals("quit")){			// Si le client se déconnecte
			System.out.println("Client : "+msg);
			try {
				msg = in.readLine();
			} catch (IOException e) {
				System.out.println("System - Problème de communication Client-Serveur");
			}
			sharedMsgList.add(NetworkMessage.strToNM(msg));
		}

	}


}