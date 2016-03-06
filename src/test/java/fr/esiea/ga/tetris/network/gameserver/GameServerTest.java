package fr.esiea.ga.tetris.network.gameserver;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class GameServerTest {

	@Test(expected=NullPointerException.class)
	public void shouldNotCreateServer() throws IOException {
		new GameServer(70000);
	}
	
}
