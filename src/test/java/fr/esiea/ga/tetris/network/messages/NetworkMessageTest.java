package fr.esiea.ga.tetris.network.messages;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NetworkMessageTest {

	@Test
	public void shouldConvertStringToNetworkMessage() {

		final int playerId = 1;
		final int actionCode = 2;
		final int subActionCode = 5;
		
		StringBuilder sb = new StringBuilder();

		sb.append(playerId);
		sb.append(",");
		sb.append(actionCode);
		sb.append(",");
		sb.append(subActionCode);

		String str = sb.toString();
		
		NetworkMessage nm = NetworkMessage.strToNM(str);
		
		assertEquals(nm.getPlayerId(),1);
		assertEquals(nm.getActionCode(),2);
		assertEquals(nm.getSubActionCode(),5);
	}
	
	@Test
	public void shouldConvertNetworkMessageToString() {

		final int playerId = 1;
		final int actionCode = 2;
		final int subActionCode = 5;
		
		String str = new String("1,2,5");
		
		NetworkMessage nm = new NetworkMessage(playerId,actionCode,subActionCode);
		
		assertEquals(str,nm.toString());
		
	}	
}