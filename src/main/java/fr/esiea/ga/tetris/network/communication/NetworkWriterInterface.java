package fr.esiea.ga.tetris.network.communication;

import java.io.IOException;

public interface NetworkWriterInterface {

	public void writeSocketOuput () throws IOException, InterruptedException;
	
}
