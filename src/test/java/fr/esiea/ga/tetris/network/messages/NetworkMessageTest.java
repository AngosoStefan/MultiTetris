package fr.esiea.ga.tetris.network.messages;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NetworkMessageTest {

	@Test
	public void shouldConvertStringToNetworkMessage() {

		final int playerNumber = 1;
		final int gameCode = 10;
		
		StringBuilder sb = new StringBuilder();

		sb.append(playerNumber);
		sb.append(",");
		sb.append(gameCode);

		String str = sb.toString();
		
		NetworkMessage nm = NetworkMessage.strToNM(str);
		
		assertEquals(nm.getPlayerNumber(),1);
		assertEquals(nm.getGameCode(),10);
	}
	
	@Test
	public void shouldConvertNetworkMessageToString() {

		final int playerNumber = 1;
		final int gameCode = 10;
		
		String str = new String("1,10");
		
		NetworkMessage nm = new NetworkMessage(playerNumber,gameCode);
		
		assertEquals(str,nm.toString());
		
	}	
}