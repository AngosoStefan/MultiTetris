package fr.esiea.ga.tetris.network.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public interface NetworkReaderInterface {

	public void readSocketInput (Socket socket,BufferedReader in) throws IOException;
}
