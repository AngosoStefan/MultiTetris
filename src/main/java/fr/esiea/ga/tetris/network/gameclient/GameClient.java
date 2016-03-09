package fr.esiea.ga.tetris.network.gameclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {

	Socket clientSocket;
	
	BufferedReader in;
	PrintWriter out;
	
	int player = 0;
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	final Scanner sc = new Scanner(System.in);
	
	public GameClient(String adr, int port) {
		try {
					
			clientSocket = new Socket(adr,port);
			System.out.println("System - Connexion établie");
			
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream());
			
			ClientReaderThread crt = new ClientReaderThread (clientSocket,in);
			ClientWriterThread cwt = new ClientWriterThread(clientSocket,out);
			
			new Thread(crt).start();	// On lance le thread d'écoute
			new Thread(cwt).start();	// On lance le thread d'écriture
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Pas de serveur actif à cette adresse et/ou port.");
		}
	}
	
	public static void main(String argv[]){
		new GameClient("127.0.0.1",8000);
	}
	
}
