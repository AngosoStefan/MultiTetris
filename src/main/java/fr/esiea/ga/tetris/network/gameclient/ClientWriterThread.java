package fr.esiea.ga.tetris.network.gameclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import fr.esiea.ga.tetris.client.View.Console;
import fr.esiea.ga.tetris.network.communication.NetworkWriterInterface;
import fr.esiea.ga.tetris.network.messages.NetworkMessage;

public class ClientWriterThread implements Runnable, NetworkWriterInterface {
	private boolean flag = false;
	private Socket socket;
	private PrintWriter out;
	private String msg;
	private ArrayBlockingQueue<NetworkMessage> sentMsgList = new ArrayBlockingQueue<NetworkMessage>(50);

	public ClientWriterThread(Socket socket, PrintWriter out) {
		this.socket = socket;
		this.out = out;
	}

	public ArrayBlockingQueue<NetworkMessage> getSentMsgList() {
		return sentMsgList;
	}

	/* Methode run du thread */

	public void run() {
		writeSocketOuput();
		closeStreams();
	}

	/* Ecrit dans le flux de sortie */

	public void writeSocketOuput() {
		Console c = new Console();
		while (true) {
			try {
				Thread.sleep(225);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!flag) {
				msg = new String("0,0,0");
				flag = true;
			} else {
				msg = null;
			}
			c.putStringAt("sentMsgList size : " + String.valueOf(sentMsgList.size()), 3, 45);
			c.putStringAt("sentMsgList isEmpty : " + String.valueOf(sentMsgList.isEmpty()), 4, 45);

			if (!sentMsgList.isEmpty()) {
				try {
					msg = sentMsgList.take().toString();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			out.println(msg);
			out.flush();
		}
	}

	/* Ferme les flux de sortie */

	public void closeStreams() {
		try {
			out.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("System - Probleme de fermeture des flux");
		}
		System.out.println("System - Connexion fermee cote client");
	}

}