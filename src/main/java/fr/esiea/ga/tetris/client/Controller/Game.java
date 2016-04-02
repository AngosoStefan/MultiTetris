package fr.esiea.ga.tetris.client.Controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fr.esiea.ga.tetris.client.Model.Area;
import fr.esiea.ga.tetris.client.Model.ConstantInput;
import fr.esiea.ga.tetris.client.Model.Piece;
import fr.esiea.ga.tetris.client.View.Console;
import fr.esiea.ga.tetris.client.View.GameVue;
import fr.esiea.ga.tetris.client.View.PieceVue;

public abstract class Game implements Runnable, ConstantInput {
	Console c = new Console();
	Area map;
	Piece currentPiece;
	Piece nextPiece;

	static boolean pieceDie = false;
	int indexFullRow = 0;
	int currentInput = -2;
	long start;
	
	public Game() {
		c.clearScreen();
		map = new Area();
		currentPiece = new Piece();
		nextPiece = new Piece();
		// Get current time
		start = System.currentTimeMillis();
	}
	
	public abstract void run();
	
	// PRINT TEMPORARY TO VIRTUAL CONSOLE
	protected void printSoloGameToVirtualConsole() {
		GameVue.printArea(c, Area.MAP_ROW, Area.MAP_COL, 0, 0);
		PieceVue.printPiece(c, currentPiece);
		GameVue.printTime(c, start);
	}
	
	// DELETE TRACE
	protected void deleteSoloGameToVirtualConsole() {
		PieceVue.hidePrevPiecePos(c, currentPiece, currentInput, map);
		GameVue.hideRawCursor(c); // Do not work perfectly
	}
	
	// TIMELOOP
	protected void gameSpeed() {
		try {
			TimeUnit.MILLISECONDS.sleep(175);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// VERIFICATION AFTER PIECE DIED
	protected void updateGame() {
		map.updateArea(currentPiece);

		do {
			indexFullRow = map.detectFullRow();
			map.deleteFullRow(c, indexFullRow);
		} while (indexFullRow != 0);

		currentPiece = nextPiece;
		nextPiece = new Piece();
		PieceVue.hideNextPiece(c);
		PieceVue.printNextPiece(c, nextPiece);
		pieceDie = false;	
	}
	
	protected void inputAction(int currentInput) {
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
	}
	
	// Handle input related to the arrow cursor
	protected int makeMove() {
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
