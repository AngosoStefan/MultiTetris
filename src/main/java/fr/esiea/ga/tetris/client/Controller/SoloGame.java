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
	Piece dbgPiece;
	boolean pieceDie = false;
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
		boolean test = true;
		while (currentInput != TOUCH_EXIT) {
			// DBG PURPOSE
			dbgPiece = currentPiece;

			Game.printArea(c, Area.MAP_ROW, Area.MAP_COL, 0, 0);
			if (test) map.printMapDbg(c);

			Game.printPiece(c, currentPiece);
			if (test) Game.printPieceDBG(c, dbgPiece);
			
			if(test) Game.printInfoDBG(c, currentPiece);

			Game.printTime(c, start);

			try {
				TimeUnit.MILLISECONDS.sleep(175);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			currentInput = makeMove();
			switch (currentInput) {
			case (TOUCH_TOP):
//				currentPiece.update(Piece.DIR_TOP);
				if (!map.detecteCollision(Piece.DIR_BOTTOM, currentPiece)) {
					currentPiece.update(Piece.DIR_BOTTOM);
				}
				else {
					pieceDie = true;
				}
				break;
			case (TOUCH_RIGHT):
				if (!map.detecteCollision(Piece.DIR_RIGHT, currentPiece)) {
					currentPiece.update(Piece.DIR_RIGHT);
				}
//				if (!map.detecteCollision(Piece.DIR_BOTTOM, currentPiece)) {
//					currentPiece.update(Piece.DIR_BOTTOM);
//				} 
//				else {
//					pieceDie = true;
//				}
				break;
			case (TOUCH_LEFT):
				if (!map.detecteCollision(Piece.DIR_LEFT, currentPiece)) {
					currentPiece.update(Piece.DIR_LEFT);
				}
//				if (!map.detecteCollision(Piece.DIR_BOTTOM, currentPiece)) {
//					currentPiece.update(Piece.DIR_BOTTOM);
//				} else {
//					pieceDie = true;
//				}
				break;
			default:
//				if (!map.detecteCollision(Piece.DIR_BOTTOM, currentPiece)) {
//					currentPiece.update(Piece.DIR_BOTTOM);
//				} else {
//					pieceDie = true;
//				}
				break;
			}
			if (pieceDie) {
				map.updateArea(currentPiece);
				currentPiece = new Piece();
				pieceDie = false;
				test = true;
			}
			
			c.printScreen();
			// Specific case if rotation
//			if (currentInput == TOUCH_TOP) {
//				Game.hidePrevPiecePos(c, currentPiece, true);
//				Game.hidePrevPiecePosDBG(c, dbgPiece, true);
//			} else {
				Game.hidePrevPiecePos(c, currentPiece, false);
				Game.hidePrevPiecePosDBG(c, dbgPiece, false);
//			}
			if (test) Game.hidePrintInfoDBG(c);
			Game.hideRawCursor(c); // Do not work perfectly
		}
		// c.clearScreen();
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
		// System.out.println(String.valueOf(charCode));
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