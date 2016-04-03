package fr.esiea.ga.tetris.network.messages;

public class NetworkMessage {

	private int playerId;
	private int actionCode;
	private int subActionCode;

	public NetworkMessage(int playerId, int actionCode, int subActionCode) {
		this.playerId = playerId;	
		this.actionCode = actionCode;
		this.subActionCode = subActionCode;
	}

	public int getPlayerId() {
		return playerId;
	}

	public int getActionCode() {
		return actionCode;
	}
	
	public int getSubActionCode() {
		return subActionCode;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(playerId);
		sb.append(",");
		sb.append(actionCode);
		sb.append(",");
		sb.append(subActionCode);

		return sb.toString();
	}

	public static NetworkMessage strToNM (String str){
		String[] strArray = str.split(",");			// J'extraie les entiers de mon message
		int[] intArray = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++) {
			intArray[i] = Integer.parseInt(strArray[i]);
		}
		
		int playerId = intArray[0];
		int actionCode = intArray[1];
		int subActionCode = intArray[2];
				
		NetworkMessage nm = new NetworkMessage(playerId,actionCode,subActionCode);	// Je cree le networkMessage correspondant

		return nm;
	}

}
