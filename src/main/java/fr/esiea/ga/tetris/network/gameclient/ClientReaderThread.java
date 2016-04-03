package fr.esiea.ga.tetris.network.gameclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import fr.esiea.ga.tetris.network.communication.NetworkReaderInterface;
import fr.esiea.ga.tetris.network.communication.ReaderCloser;
import fr.esiea.ga.tetris.network.messages.NetworkMessage;

public class ClientReaderThread implements Runnable, NetworkReaderInterface {

	private BufferedReader in;
	private Socket socket;
	private int clientId;
	private ArrayBlockingQueue<NetworkMessage> receivedMsgList = new ArrayBlockingQueue<NetworkMessage>(50);
	
	public ClientReaderThread (Socket socket,BufferedReader in) {
		this.in = in; 
		this.socket = socket;
	}
	
	public ArrayBlockingQueue<NetworkMessage> getReceivedMsgList() {
		return receivedMsgList;
	}

	public void run() {
		readSocketInput();						// je lis
		ReaderCloser.closeStreams(socket,in);	// je ferme
	}

	public void readSocketInput () {
		String msg = new String("0,0,0");
		NetworkMessage nm;
		while(msg != null && !msg.equals("quit")){			// Si le client se deconnecte
			try {
				msg = in.readLine();
				// System.out.println("ReadLine : "+ msg);
			} catch (IOException e) {
				System.out.println("System - Probleme de communication Client-Serveur");
			}
			if (msg.contains(",")) {
				// Ajoute le message lu a la liste
				receivedMsgList.add(NetworkMessage.strToNM(msg));
			}
		}
	}

	public void closeStreams(Socket socket, BufferedReader in) throws IOException {
		in.close();
		socket.close();
	}

	public void readSocketInput (Socket socket, BufferedReader in) throws IOException {
		String msg = in.readLine();
		while(msg != null && !msg.equals("quit")){
			System.out.println("Serveur : "+msg);
			msg = in.readLine();
		}

	}

	public void closeStreams(Socket socket, BufferedReader in, PrintWriter out) throws IOException {
		socket.close();
		in.close();
		System.out.println("System - Connexion fermee cote client");
	}

}