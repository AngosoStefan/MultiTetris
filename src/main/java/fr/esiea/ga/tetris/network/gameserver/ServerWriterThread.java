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

	public ServerWriterThread (Socket socket, PrintWriter out, ArrayBlockingQueue<NetworkMessage> sharedMsgList, int serverId) {
		this.socket = socket;
		this.out = out;
		this.sharedMsgList = sharedMsgList;
		this.serverId = serverId;
	}


	public void run() {
		writeSocketOuput();
		System.out.println("Tout est fermé avant");
		WriterCloser.closeStreams(socket,out);
		System.out.println("Tout est fermé");
	}


	public void writeSocketOuput() {
		
		nm = NetworkMessage.strToNM(String.valueOf(serverId)+",5");		// On attribue le numéro de joueur
		out.println(nm.toString());			
		out.flush();
		
		while(true){
			try {
				nm = sharedMsgList.take();
			} catch (InterruptedException e) {
				System.out.println("System - Problème d'acquisition du message réseau");
			}
			out.println(nm.toString());			
			out.flush();
		}
	}

}