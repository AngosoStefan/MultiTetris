package fr.esiea.ga.tetris.network.messages;

public class ClientMessage {

	private int playerNumber;	// Num�ro de joueur
	private int gameCode;		// Code de jeu : malus, partie perdue...
	
	public ClientMessage(int playerNumber, int gameCode) {
		this.playerNumber = playerNumber;	
		this.gameCode = gameCode;			
	}
	
}
