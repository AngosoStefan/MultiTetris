package fr.esiea.ga.tetris.network.messages;

public class NetworkMessage {

	private int playerNumber;	// Numéro de joueur
	private int gameCode;		// Code de jeu : malus, partie perdue...

	public NetworkMessage(int playerNumber, int gameCode) {
		this.playerNumber = playerNumber;	
		this.gameCode = gameCode;			
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public int getGameCode() {
		return gameCode;
	}

	public void setGameCode(int gameCode) {
		this.gameCode = gameCode;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(playerNumber);
		sb.append(",");
		sb.append(gameCode);

		return sb.toString();
	}

	public static NetworkMessage strToNM (String str){
		
		String[] strArray = str.split(",");			// J'extraie les entiers de mon message
		int[] intArray = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++) {
			intArray[i] = Integer.parseInt(strArray[i]);
		}
		
		int playerCode = intArray[0];
		int gameCode = intArray[1];
				
		NetworkMessage nm = new NetworkMessage(playerCode,gameCode);	// Je crée le networkMessage correspondant

		return nm;
	}

}
