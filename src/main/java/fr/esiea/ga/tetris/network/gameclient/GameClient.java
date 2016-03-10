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

	final Scanner sc = new Scanner(System.in);
	
	public GameClient(String adr, int port) {
		try {
			clientSocket = new Socket(adr,port);		// On tente de se connecter au serveur
			System.out.println("System - Connexion �tablie");
			
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	// On r�cup�re les flux d'entr�e et sortie
			out = new PrintWriter(clientSocket.getOutputStream());
			
			ClientReaderThread crt = new ClientReaderThread (clientSocket,in);	// On lance les threads de lecture et d'�criture
			ClientWriterThread cwt = new ClientWriterThread(clientSocket,out);
			
			new Thread(crt).start();	// On lance le thread d'�coute
			new Thread(cwt).start();	// On lance le thread d'�criture
			
		} catch (UnknownHostException e) {
			System.out.println("Cet h�te est inconnu");
		} catch (IOException e) {
			System.out.println("Pas de serveur actif � cette adresse et/ou port.");
		}
	}
	
	public static void main(String argv[]){
		new GameClient("127.0.0.1",8000);
	}
	
}
