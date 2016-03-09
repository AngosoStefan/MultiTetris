package fr.esiea.ga.tetris.network.communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public interface NetworkWriterInterface {

	public void writeSocketOuput (Socket socket, PrintWriter out) throws IOException;
}
