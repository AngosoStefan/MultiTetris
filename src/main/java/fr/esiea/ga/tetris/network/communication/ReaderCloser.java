package fr.esiea.ga.tetris.network.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ReaderCloser {

	public static void closeStreams(Socket socket, BufferedReader in) {
		try {
			in.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("System - Problème de fermeture des flux");
		}
		System.out.println("System - Connexion fermée côté client");
	}
	
}
