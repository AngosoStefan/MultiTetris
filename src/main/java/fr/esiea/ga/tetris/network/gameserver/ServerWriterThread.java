package fr.esiea.ga.tetris.network.gameserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import fr.esiea.ga.tetris.network.communication.NetworkWriterInterface;

public class ServerWriterThread implements Runnable, NetworkWriterInterface {

	String msg;
	Socket socket;
	PrintWriter out;

	public ServerWriterThread (Socket socket, PrintWriter out) {
		this.socket = socket;
		this.out = out;
	}

	
	public void run() {
		try {
			writeSocketOuput(socket,out);
			closeStreams(socket,out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void writeSocketOuput(Socket socket, PrintWriter out) {
		while(true){
			msg="toto";
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			out.println(msg);
			out.flush();
		}
	}
	

	public void closeStreams(Socket socket, PrintWriter out) throws IOException {
		socket.close();
		out.close();
		System.out.println("System - Connexion fermée côté client");
	}

}