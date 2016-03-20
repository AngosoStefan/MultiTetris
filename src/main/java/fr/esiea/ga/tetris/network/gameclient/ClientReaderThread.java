package fr.esiea.ga.tetris.network.gameclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import fr.esiea.ga.tetris.network.communication.NetworkReaderInterface;
import fr.esiea.ga.tetris.network.communication.ReaderCloser;
import fr.esiea.ga.tetris.network.messages.NetworkMessage;

public class ClientReaderThread implements Runnable, NetworkReaderInterface {

	private BufferedReader in;
	private Socket socket;
	private String msg;
	private int clientId;
	private NetworkMessage nm;
	
	public ClientReaderThread (Socket socket,BufferedReader in) {
		this.in = in; 
		this.socket = socket;
		msg = new String("0,0");
	}

	public void run() {
		readSocketInput();
		ReaderCloser.closeStreams(socket,in);
	}

	public void readSocketInput () {
		while(msg != null && !msg.equals("quit")){
			try {
				msg = in.readLine();
				nm = NetworkMessage.strToNM(msg);
				handleAction();
				System.out.println("Mon numéro de joueur est "+clientId);
				try {
					Thread.sleep(90000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				System.out.println("System - Problème de communication Client-Serveur");
			}
		}
	}
	
	public void handleAction () {
		if (nm.getGameCode() == 5)
			clientId = nm.getPlayerNumber();
	}

	// DURING MERGING NO CONFLICT ON THAT
	// public void closeStreams(Socket socket, BufferedReader in) throws IOException {
	// 	in.close();
	// 	socket.close();
	// }

	// public void readSocketInput (Socket socket, BufferedReader in) throws IOException {
	// 	msg = in.readLine();
	// 	while(msg != null && !msg.equals("quit")){
	// 		System.out.println("Serveur : "+msg);
	// 		msg = in.readLine();
	// 	}
		
	// }
	
	// public void closeStreams(Socket socket, BufferedReader in, PrintWriter out) throws IOException {
	// 	socket.close();
	// 	in.close();
	// 	System.out.println("System - Connexion fermée côté client");
	// }
	
}