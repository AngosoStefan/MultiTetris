package fr.esiea.ga.tetris.client.Controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fr.esiea.ga.tetris.client.Model.Area;
import fr.esiea.ga.tetris.client.Model.ConstantInput;
import fr.esiea.ga.tetris.client.Model.Piece;
import fr.esiea.ga.tetris.client.View.Console;
import fr.esiea.ga.tetris.client.View.Game;

public class SoloGame implements Runnable, ConstantInput {
	Console c;
	Area map;
	Piece currentPiece;
	int currentInput = -2;
	long start;

	public SoloGame(Console c) {
		this.c = c;
		map = Area.getInstance();
		currentPiece = new Piece();
		// Get current time
		start = System.currentTimeMillis();
	}

	public void run() {
		while (currentInput != TOUCH_EXIT) {
			Game.printArea(c, Area.MAP_ROW, Area.MAP_COL, 0, 0);
			
			Game.hidePrevPiecePos(c, currentPiece);
			Game.printPiece(c, currentPiece);
			Game.printTime(c, start);
			// Game.printArea(c, Area.MAP_ROW, Area.MAP_COL, 0, 0);
			// Check si printPiece possible
			// Si oui
			try {
				TimeUnit.MILLISECONDS.sleep(175);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentInput = makeMove();
			switch (currentInput) {
			case (TOUCH_TOP):
				currentPiece.update(Piece.DIR_TOP);
				break;
			case (TOUCH_RIGHT):
				if (!map.detectedBorderCollision(Piece.DIR_RIGHT, currentPiece,c))
					currentPiece.update(Piece.DIR_RIGHT);
				else
					currentPiece.update(Piece.DIR_BOTTOM);
				break;
			case (TOUCH_LEFT):
				currentPiece.update(Piece.DIR_LEFT);
				break;
			default:
				if (!map.detectedBorderCollision(Piece.DIR_BOTTOM, currentPiece,c))
					currentPiece.update(Piece.DIR_BOTTOM);
				else {
					// End life Piece
					
					currentPiece = new Piece();
				}
					
				break;
			}

			c.printScreen();
			Game.hideRawCursor(c); // Marche un peu
			// DBG
						map.printMapDbg(c,currentPiece);
		}
//		c.clearScreen();
	}

	// Handle input related to the arrow cursor
	private int makeMove() {
		int charCode = -2;

		try {
			charCode = RawConsoleInput.read(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(String.valueOf(charCode));
		switch (charCode) {
		case TOUCH_TOP:
			return TOUCH_TOP;
		case TOUCH_RIGHT:
			return TOUCH_RIGHT;
		case TOUCH_BOTTOM:
			return TOUCH_BOTTOM;
		case TOUCH_LEFT:
			return TOUCH_LEFT;
		case TOUCH_EXIT:
			return TOUCH_EXIT;
		default:
			return -2;
		}
	}

}