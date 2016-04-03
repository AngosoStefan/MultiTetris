package fr.esiea.ga.tetris.client.Controller;

import fr.esiea.ga.tetris.client.Model.Area;
import fr.esiea.ga.tetris.client.Model.ConstantNetworkMessage;
import fr.esiea.ga.tetris.client.Model.Piece;
import fr.esiea.ga.tetris.client.View.GameVue;
import fr.esiea.ga.tetris.client.View.PieceVue;
import fr.esiea.ga.tetris.network.gameclient.GameClient;
import fr.esiea.ga.tetris.network.messages.NetworkMessage;

public class MultiGame extends Game implements ConstantNetworkMessage {
	GameClient gameClient;
	int playerId;
	NetworkMessage nm;

	Piece currentPieceOtherPlayer;

	/**************
	 * MALUS FLAG *
	 **************/

	boolean decrMvmtSpeed = false;
	boolean incrMvmtSpeed = false;;

	public MultiGame(GameClient gameClient) {
		super();
		boolean start = false;
		this.gameClient = gameClient;
		try {
			nm = gameClient.getCrt().getReceivedMsgList().take();
			playerId = nm.getPlayerId();
			do {
				if (!gameClient.getCrt().getReceivedMsgList().isEmpty()) {
					nm = gameClient.getCrt().getReceivedMsgList().take();
					System.out.println(
							"Please Player " + String.valueOf(playerId) + " wait for the other Player to start");
					System.out.println(" NetworkMessage" + nm.toString());
				}
				if (nm.getPlayerId() == 0 && nm.getActionCode() == 9 && nm.getSubActionCode() == 9)
					start = true;
			} while (start == false);
			gameClient.getCwt().getSentMsgList().add(new NetworkMessage(0, 9, 9));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		gameClient.getCwt().getSentMsgList()
//				.add(new NetworkMessage(playerId, PIECETYPE, currentPiece.pieceType.getBlockId()));
//		start = false;
//		do {
//			if (!gameClient.getCrt().getReceivedMsgList().isEmpty()) {
//				try {
//					nm = gameClient.getCrt().getReceivedMsgList().take();
//					if(nm.getActionCode() == PIECETYPE)
//						start = true;
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		} while (start == false);
//		currentPieceOtherPlayer = new Piece(nm.getSubActionCode());

	}

	public void run() {

		PieceVue.printNextPiece(c, nextPiece);

		while (currentInput != TOUCH_EXIT) {

			/**************************************
			 * PRINT TEMPORARY TO VIRTUAL CONSOLE *
			 **************************************/
			printSoloGameToVirtualConsole();
//			while (!gameClient.getCrt().getReceivedMsgList().isEmpty()) {
//				try {
//					nm = gameClient.getCrt().getReceivedMsgList().take();
//					if (nm.getActionCode() == POSITION_X) {
//						currentPieceOtherPlayer.xPrevPos = currentPieceOtherPlayer.xPos;
//						currentPieceOtherPlayer.xPos = nm.getSubActionCode();
//					}
//					if (nm.getActionCode() == POSITION_Y) {
//						currentPieceOtherPlayer.yPrevPos = currentPieceOtherPlayer.yPos;
//						currentPieceOtherPlayer.yPos = nm.getSubActionCode();
//					}
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			printOtherPlayerToVirtualConsole();

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
				// gameClient.getCwt().getSentMsgList().add(new
				// NetworkMessage(1, 6));
			}
			inputAction(currentInput);
			gameClient.getCwt().getSentMsgList().add(new NetworkMessage(playerId, POSITION_X, currentPiece.xPos));
			gameClient.getCwt().getSentMsgList().add(new NetworkMessage(playerId, POSITION_Y, currentPiece.yPos));

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
			// deleteOtherPlayerToVirtualConsole();
			c.putStringAt("                                     ", 0, 45);
			c.putStringAt("                                     ", 1, 45);
		}
		c.clearScreen();
	}

	private void printOtherPlayerToVirtualConsole() {
		GameVue.printAreaOtherPlayer(c, Area.MAP_ROW, Area.MAP_COL);
		PieceVue.printPiece(c, currentPieceOtherPlayer);
	}

	private void deleteOtherPlayerToVirtualConsole() {
		PieceVue.hidePrevPiecePosOtherPlayer(c, currentPieceOtherPlayer);
	}

}