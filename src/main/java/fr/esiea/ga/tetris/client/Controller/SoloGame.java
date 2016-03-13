package fr.esiea.ga.tetris.client.Controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fr.esiea.ga.tetris.client.Model.ConstantInput;
import fr.esiea.ga.tetris.client.Model.Piece;
import fr.esiea.ga.tetris.client.View.Console;
import fr.esiea.ga.tetris.client.View.Game;

public class SoloGame implements Runnable, ConstantInput {
	Console c;
	Piece currentPiece;
	int currentInput = -2;
	int directionPiece;
	long start;
	
	public SoloGame(Console c) {
		this.c = c;
		currentPiece = new Piece();
		// Get current time
		start = System.currentTimeMillis();
	}

	public void run() {
		
		while (currentInput != TOUCH_EXIT) {
			Game.printArea(c,15,13,0,0);
			// Check si printPiece possible
			// Si oui
			Game.hidePrevPiecePos(c, currentPiece);
			Game.printPiece(c, currentPiece);
			currentInput = makeMove();
			switch (currentInput) {
			case (TOUCH_TOP):
				currentPiece.update(Piece.DIR_TOP);
				break;
			case (TOUCH_RIGHT):
				directionPiece = Piece.DIR_RIGHT;
				break;
			case (TOUCH_LEFT):
				directionPiece = Piece.DIR_LEFT;
				break;
			default:
				currentPiece.update(Piece.DIR_BOTTOM);
				break;
			}
			Game.printTime(c, start);
			c.printScreen();
			Game.hideRawCursor(c); // Marche un peu
		}
		c.clearScreen();
	}
	
	// Handle input related to the arrow cursor
	private int makeMove() {
		int charCode = -2;

		try {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
				charCode = RawConsoleInput.read(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println(String.valueOf(charCode));
			switch (charCode) {
			case TOUCH_EXIT:
				return TOUCH_EXIT;
			case TOUCH_ENTER:
				return TOUCH_ENTER;
			case TOUCH_LEFT:
				return TOUCH_LEFT;
			case TOUCH_RIGHT:
				return TOUCH_RIGHT;
			default:
				return -2;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return charCode;
	}

}