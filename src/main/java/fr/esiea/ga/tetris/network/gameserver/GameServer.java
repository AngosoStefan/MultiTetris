package fr.esiea.ga.tetris.network.gameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import fr.esiea.ga.tetris.network.messages.NetworkMessage;

public class GameServer {

	private ServerSocket serverSocket; // Socket du serveur
	private ArrayBlockingQueue<NetworkMessage> sharedMsgList = new ArrayBlockingQueue<NetworkMessage>(50);
	
	int connectedPlayers = 0;
	int maxPlayers = 2;
	
	final Scanner sc = new Scanner(System.in);	// Entrees
	private Socket socket;	// Socket de connexion au client
	
	private ServerReaderThread srt;
	private ServerWriterThread swt;
	
	public GameServer(int port) throws IOException {
		
		socket = new Socket();
		
		try {
			serverSocket = new ServerSocket(port);	// On cree le serveur
			System.out.println("System - Le serveur est cree au port "+port+".");
			
			while (connectedPlayers < maxPlayers) {
			
				socket = serverSocket.accept();			// On accepte une connexion
				connectedPlayers++;
				
				System.out.println("System - Un client s'est connect�. Id : " + String.valueOf(connectedPlayers));
				
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter (socket.getOutputStream());

				srt = new ServerReaderThread (socket,in,sharedMsgList);
				swt = new ServerWriterThread (socket,out,sharedMsgList, connectedPlayers);
				
				new Thread(srt).start();	// On lance le thread d'ecoute serveur
				new Thread(swt).start();	// On lance le thread d'ecriture serveur
			}
		}
		catch (IOException e) {
			System.out.println("Le serveur n'a pas pu etre cree a ce numero de port.");
		}
	}
	
	public static int askPort(){
		Scanner sc = new Scanner(System.in);
		int port = -1;
		
		while (port < 0 || port > 65535){
			port = sc.nextInt();
		}
		
		sc.close();
		
		return port;
	}
	
	public static void main (String argv[]) throws IOException, NullPointerException {
		//System.out.println("System - Taper le numero de port :");
		int port = 8000;
		//port = askPort();	// On demande le numero de port
		
		new GameServer(port);
	}
	
}
