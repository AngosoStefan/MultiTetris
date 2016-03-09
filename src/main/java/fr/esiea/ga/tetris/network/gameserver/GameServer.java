package fr.esiea.ga.tetris.network.gameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GameServer {

	ServerSocket serverSocket; // Socket du serveur
	Socket[] socketArray;
	
	int connectedPlayers, maxPlayers = 2;
	
	final Scanner sc = new Scanner(System.in);	// Entr�es
	private Socket socket;	// Socket de connexion au client
	
	public GameServer(int port) throws IOException {
		
		socket = new Socket();
		
		try {
			
			serverSocket = new ServerSocket(port);	// On cr�e le serveur
			System.out.println("System - Le serveur est cr�� au port "+port+".");
			
			while (connectedPlayers <= maxPlayers) {
			
				socket = serverSocket.accept();			// On accepte une connexion
				connectedPlayers++;
				
				System.out.println("System - Un client s'est connect�.");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter (socket.getOutputStream());
			
				ServerReaderThread srt = new ServerReaderThread (socket,in);
				ServerWriterThread swt = new ServerWriterThread(socket,out);
				
				new Thread(srt).start();	// On lance le thread d'�coute serveur
				new Thread(swt).start();	// On lance le thread d'�criture serveur
				
			}
			
		}
		catch (IOException e) {
			System.out.println("Le serveur n'a pas pu �tre cr�� � ce num�ro de port.");
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
		//System.out.println("System - Taper le num�ro de port :");
		int port = 8000;
		//port = askPort();	// On demande le num�ro de port
		
		new GameServer(port);
	}
	
}
