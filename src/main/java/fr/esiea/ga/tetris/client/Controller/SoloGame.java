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
	Piece nextPiece;
	Piece dbgPiece;
	boolean pieceDie = false;
	int currentInput = -2;
	long start;

	public SoloGame(Console c) {
		this.c = c;
		map = Area.getInstance();
		currentPiece = new Piece();
		nextPiece = new Piece();
		// Get current time
		start = System.currentTimeMillis();
	}

	public void run() {
		
		boolean dbg = true;
		Game.printNextPiece(c, nextPiece);
		dbgPiece = currentPiece;

		while (currentInput != TOUCH_EXIT) {

			/*
			 * PRINT
			 */
			Game.printArea(c, Area.MAP_ROW, Area.MAP_COL, 0, 0);
			Game.printPiece(c, currentPiece);
			Game.printTime(c, start);

			if (dbg) {
				Game.printMapDbg(c, map);
				Game.printPieceDBG(c, dbgPiece);
				Game.printInfoDBG(c, currentPiece, (int) (System.currentTimeMillis() - start));
			}

			/*
			 * TIMELOOP
			 */
			try {
				TimeUnit.MILLISECONDS.sleep(175);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			/*
			 * PRINT GAME
			 */
			c.printScreen();

			currentInput = makeMove();
			switch (currentInput) {
			case (TOUCH_TOP):
				if (!map.detecteCollision(Piece.DIR_TOP, currentPiece)) {
					currentPiece.update(Piece.DIR_TOP);
				}
				break;
			case (TOUCH_RIGHT):
				if (!map.detecteCollision(Piece.DIR_RIGHT, currentPiece)) {
					currentPiece.update(Piece.DIR_RIGHT);
				}
				break;
			case (TOUCH_LEFT):
				if (!map.detecteCollision(Piece.DIR_LEFT, currentPiece)) {
					currentPiece.update(Piece.DIR_LEFT);
				}
				break;
			default:
				if (!map.detecteCollision(Piece.DIR_BOTTOM, currentPiece)) {
					currentPiece.update(Piece.DIR_BOTTOM);
				} else {
					pieceDie = true;
				}

				break;
			}
			if (pieceDie) {
				map.updateArea(currentPiece);
				currentPiece = nextPiece;
				nextPiece = new Piece();
				Game.hideNextPiece(c);
				Game.printNextPiece(c, nextPiece);
				if (dbg) 
					dbgPiece = currentPiece;
				pieceDie = false;
			}

			/*
			 * HIDE TRASH
			 */
			Game.hidePrevPiecePos(c, currentPiece, currentInput, map);
			Game.hidePrevPiecePosDBG(c, dbgPiece, false);
			Game.hideRawCursor(c); // Do not work perfectly
			if (dbg)
				Game.hidePrintInfoDBG(c);

			// Specific case if rotation
			// if (currentInput == TOUCH_TOP) {
			// Game.hidePrevPiecePos(c, currentPiece, true);
			// Game.hidePrevPiecePosDBG(c, dbgPiece, true);
			// } else {

			// }

		}
	    System.out.println("\033[31;1mHello\033[0m, \033[32;1;2mworld!\033[0m");
	    System.out.println("\033[31mRed\033[32m, Green\033[33m, Yellow\033[34m, Blue\033[0m");
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