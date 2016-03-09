package fr.esiea.ga.tetris.network.messages;

public class ClientMessage {

	private int playerNumber;	// Numéro de joueur
	private int gameCode;		// Code de jeu : malus, partie perdue...
	
	public ClientMessage(int playerNumber, int gameCode) {
		this.playerNumber = playerNumber;	
		this.gameCode = gameCode;			
	}
	
}
