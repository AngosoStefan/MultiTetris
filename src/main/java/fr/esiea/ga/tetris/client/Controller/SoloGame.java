package fr.esiea.ga.tetris.client.Controller;

import fr.esiea.ga.tetris.client.Model.Piece;
import fr.esiea.ga.tetris.client.View.DbgVue;
import fr.esiea.ga.tetris.client.View.PieceVue;

public class SoloGame extends Game {
	Piece dbgPiece;

	public SoloGame() {
		super();
	}

	public void run() {

		boolean dbg = false;
		PieceVue.printNextPiece(c, nextPiece);
		
		if (dbg) {
			dbgPiece = currentPiece;
		}

		while (currentInput != TOUCH_EXIT) {

			/**************************************
			 * PRINT TEMPORARY TO VIRTUAL CONSOLE *
			 **************************************/
			printSoloGameToVirtualConsole();

			if (dbg) {
				DbgVue.printMapDbg(c, map);
				DbgVue.printPieceDBG(c, dbgPiece);
				DbgVue.printInfoDBG(c, currentPiece, (int) (System.currentTimeMillis() - start));
			}

			/************
			 * TIMELOOP *
			 ************/
			gameSpeed();

			/*************************
			 * PRINT VIRTUAL CONSOLE *
			 *************************/
			c.printScreen();
			
			/*********************
			 * HANDLE USER INPUT *
			 *********************/
			currentInput = makeMove();
			inputAction(currentInput);

			if (pieceDie) {
				map.updateArea(currentPiece);
				updateGame();
				if (dbg)
					dbgPiece = currentPiece;
			}

			/****************
			 * DELETE TRACE *
			 ****************/
			deleteSoloGameToVirtualConsole();
			if (dbg) {
				DbgVue.hidePrevPiecePosDBG(c, dbgPiece, false);
				DbgVue.hidePrintInfoDBG(c);
			}
		}
		c.clearScreen();
	}

}