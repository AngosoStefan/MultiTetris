package fr.esiea.ga.tetris.client.Controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fr.esiea.ga.tetris.client.Model.Area;
import fr.esiea.ga.tetris.client.Model.ConstantInput;
import fr.esiea.ga.tetris.client.Model.Piece;
import fr.esiea.ga.tetris.client.View.Console;
import fr.esiea.ga.tetris.client.View.DbgVue;
import fr.esiea.ga.tetris.client.View.Game;
import fr.esiea.ga.tetris.client.View.PieceVue;

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
		c.clearScreen();
		map = Area.getInstance();
		currentPiece = new Piece();
		nextPiece = new Piece();
		// Get current time
		start = System.currentTimeMillis();
	}

	public void run() {

		boolean dbg = false;
		int fullRow = 0;
		PieceVue.printNextPiece(c, nextPiece);
		dbgPiece = currentPiece;

		while (currentInput != TOUCH_EXIT) {

			/*
			 * PRINT
			 */
			Game.printArea(c, Area.MAP_ROW, Area.MAP_COL, 0, 0);
			PieceVue.printPiece(c, currentPiece);
			Game.printTime(c, start);

			if (dbg) {
				DbgVue.printMapDbg(c, map);
				DbgVue.printPieceDBG(c, dbgPiece);
				DbgVue.printInfoDBG(c, currentPiece, (int) (System.currentTimeMillis() - start));
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

				do {
					fullRow = map.detectFullRow();
					map.deleteFullRow(c, fullRow);
				} while (fullRow != 0);

				currentPiece = nextPiece;
				nextPiece = new Piece();
				PieceVue.hideNextPiece(c);
				PieceVue.printNextPiece(c, nextPiece);
				if (dbg)
					dbgPiece = currentPiece;
				pieceDie = false;
			}

			/*
			 * HIDE TRASH
			 */
			PieceVue.hidePrevPiecePos(c, currentPiece, currentInput, map);
			DbgVue.hidePrevPiecePosDBG(c, dbgPiece, false);
			Game.hideRawCursor(c); // Do not work perfectly
			if (dbg)
				DbgVue.hidePrintInfoDBG(c);

		}
		c.clearScreen();
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