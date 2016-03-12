package fr.esiea.ga.tetris.network.communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class WriterCloser {

	public static void closeStreams(Socket socket, PrintWriter out) {
		try {
			socket.close();
			out.close();
		} catch (IOException e) {
			System.out.println("System - Probl�me de fermeture des flux");
		}
		System.out.println("System - Connexion ferm�e c�t� client");
	}
	
}
