package fr.esiea.ga.tetris.client.Controller;

import fr.esiea.ga.tetris.client.Model.Area;
import fr.esiea.ga.tetris.client.View.GameVue;
import fr.esiea.ga.tetris.client.View.PieceVue;
import fr.esiea.ga.tetris.network.gameclient.GameClient;
import fr.esiea.ga.tetris.network.messages.NetworkMessage;

public class MultiGame extends Game {
	GameClient gameClient;
	NetworkMessage nm;

	/**************
	 * MALUS FLAG *
	 **************/

	boolean decrMvmtSpeed = false;
	boolean incrMvmtSpeed = false;;

	public MultiGame(GameClient gameClient) {
		super();
		this.gameClient = gameClient;
	}

	public void run() {

		PieceVue.printNextPiece(c, nextPiece);

		while (currentInput != TOUCH_EXIT) {

			/**************************************
			 * PRINT TEMPORARY TO VIRTUAL CONSOLE *
			 **************************************/
			printSoloGameToVirtualConsole();
			printOtherPlayerToVirtualConsole();

			// nm = gameClient.getCrt().getReceivedMsgList().poll();
			c.putStringAt(
					"ClientReaderThread SIZE : " + String.valueOf(gameClient.getCrt().getReceivedMsgList().size()), 1,
					45);
			if (!gameClient.getCrt().getReceivedMsgList().isEmpty()) {
				try {
					c.putStringAt(
							"ClientReaderThread NM : " + gameClient.getCrt().getReceivedMsgList().take().toString(), 0,
							45);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				c.putStringAt("EMPTY ", 0, 45);
			}


			/********************
			 * TIMELOOP & MALUS *
			 ********************/
			gameSpeed();
			// try {
			// if (incrMvmtSpeed)
			// TimeUnit.MILLISECONDS.sleep(250);
			// else if (decrMvmtSpeed)
			// TimeUnit.MILLISECONDS.sleep(100);
			// else
			// TimeUnit.MILLISECONDS.sleep(175);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }

			/*********************
			 * HANDLE USER INPUT *
			 *********************/
			currentInput = makeMove();
			if (97 == currentInput) {
				c.putStringAt("MSG SENT", 1, 45);
				gameClient.getCwt().getSentMsgList().add(new NetworkMessage(1, 6));
			}
			inputAction(currentInput);

			if (pieceDie) {
				map.updateArea(currentPiece);
				updateGame();
			}

			/*************************
			 * PRINT VIRTUAL CONSOLE *
			 *************************/
			c.printScreen();

			/****************
			 * DELETE TRACE *
			 ****************/
			deleteSoloGameToVirtualConsole();
			c.putStringAt("                                     ", 0, 45);
			c.putStringAt("                                     ", 1, 45);
		}
		c.clearScreen();
	}

	private void printOtherPlayerToVirtualConsole() {
		GameVue.printAreaOtherPlayer(c, Area.MAP_ROW, Area.MAP_COL);
		// PieceVue.printPiece(c, currentPiece);
	}

}