package fr.esiea.ga.tetris.client.Controller;

import fr.esiea.ga.tetris.client.Model.Area;
import fr.esiea.ga.tetris.client.View.GameVue;
import fr.esiea.ga.tetris.client.View.PieceVue;

public class MultiGame extends Game {

	/**************
	 * MALUS FLAG *
	 **************/

	boolean decrMvmtSpeed = false;
	boolean incrMvmtSpeed = false;;

	public MultiGame() {
		super();
	}

	public void run() {

		PieceVue.printNextPiece(c, nextPiece);

		while (currentInput != TOUCH_EXIT) {

			/**************************************
			 * PRINT TEMPORARY TO VIRTUAL CONSOLE *
			 **************************************/
			printSoloGameToVirtualConsole();
			printOtherPlayerToVirtualConsole();

			/********************
			 * TIMELOOP & MALUS *
			 ********************/
			gameSpeed();
//			try {
//				if (incrMvmtSpeed)
//					TimeUnit.MILLISECONDS.sleep(250);
//				else if (decrMvmtSpeed)
//					TimeUnit.MILLISECONDS.sleep(100);
//				else
//					TimeUnit.MILLISECONDS.sleep(175);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}

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
			}

			/****************
			 * DELETE TRACE *
			 ****************/
			deleteSoloGameToVirtualConsole();
		}
		c.clearScreen();
	}
	
	private void printOtherPlayerToVirtualConsole() {
		GameVue.printAreaOtherPlayer(c, Area.MAP_ROW, Area.MAP_COL);
		// PieceVue.printPiece(c, currentPiece);
	}

}