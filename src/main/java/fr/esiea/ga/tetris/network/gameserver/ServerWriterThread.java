package fr.esiea.ga.tetris.network.gameserver;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import fr.esiea.ga.tetris.network.communication.NetworkWriterInterface;
import fr.esiea.ga.tetris.network.communication.WriterCloser;
import fr.esiea.ga.tetris.network.messages.NetworkMessage;

public class ServerWriterThread implements Runnable, NetworkWriterInterface {

	private Socket socket;
	private PrintWriter out;
	private ArrayBlockingQueue<NetworkMessage> sharedMsgList;
	private NetworkMessage nm;
	private int serverId;

	public ServerWriterThread(Socket socket, PrintWriter out, ArrayBlockingQueue<NetworkMessage> sharedMsgList,
			int serverId) {
		this.socket = socket;
		this.out = out;
		this.sharedMsgList = sharedMsgList;
		this.serverId = serverId;
	}

	public void run() {
		writeSocketOuput();
		System.out.println("Tout est ferme avant");
		WriterCloser.closeStreams(socket, out);
		System.out.println("Tout est ferme");
	}

	public void writeSocketOuput() {
		// On attribue le numero de joueur
		nm = NetworkMessage.strToNM(String.valueOf(serverId) + ",5");
		System.out.println("NetworkMessage Initialise : " + nm.toString());
		out.println(nm.toString());
		out.flush();

		while (true) {
			try {
				Thread.sleep(225);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("SERVER_sharedMsgList SIZE : " + String.valueOf(sharedMsgList.size()));
			if (!sharedMsgList.isEmpty()) {
				try {
					nm = sharedMsgList.take(); // Prend les messages de la liste
					System.out.println("J'ai pris le message suivant QUE J'ENVOIE : " + nm.toString());
					out.println(nm.toString()); // Les envoie a tout le monde
					out.flush();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("System - Probleme d'acquisition du message reseau");
				}
			}
		}
	}
}