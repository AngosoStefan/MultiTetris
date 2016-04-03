package fr.esiea.ga.tetris.client.View;

import fr.esiea.ga.tetris.client.Model.Area;
import fr.esiea.ga.tetris.client.Model.ConstantChar;
import fr.esiea.ga.tetris.client.Model.ConstantGame;
import fr.esiea.ga.tetris.client.Model.ConstantInput;
import fr.esiea.ga.tetris.client.Model.Piece;

public class PieceVue implements ConstantChar, ConstantInput, ConstantGame {
	
	public static void printPiece(Console c, Piece p) {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (p.pieceContent[row][col] == 1) {
//					c.putStringAt(ANSI_RED, row + p.xPos, col + p.yPos);
					c.putStringAt(p.pieceChar, row + p.xPos, col + p.yPos);
//					c.putStringAt(ANSI_RESET, row + p.xPos, col + p.yPos);
				}
			}
		}
	}

	public static void hidePrevPiecePos(Console c, Piece p, int top, Area map) {
		Piece pCopy = new Piece();
		pCopy.xPrevPos = p.xPrevPos;
		pCopy.yPrevPos = p.yPrevPos;
		pCopy.pieceContent = p.pieceContent;

		if (top == TOUCH_TOP) {
			pCopy.rotatePieceRight();
		}

		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (pCopy.pieceContent[row][col] == 1)
					if (map.area[row + pCopy.xPrevPos][col + pCopy.yPrevPos] != 1)
						c.putStringAt(" ", row + pCopy.xPrevPos, col + pCopy.yPrevPos);
			}
		}
	}
	
	public static void hidePrevPiecePosOtherPlayer(Console c, Piece p) {
		Piece pCopy = new Piece();
		pCopy.xPrevPos = p.xPrevPos;
		pCopy.yPrevPos = p.yPrevPos;
		pCopy.pieceContent = p.pieceContent;
		
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (pCopy.pieceContent[row][col] == 1)
						c.putStringAt(" ", row + pCopy.xPrevPos, col + pCopy.yPrevPos);
			}
		}
	}
	
	public static void printNextPiece(Console c, Piece p) {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (p.pieceContent[row][col] == 1) {
					c.putStringAt(p.pieceChar, row + 1, col + MAP_COL + 3);
//					c.putStringAt(ANSI_RED + p.pieceChar + ANSI_RESET, row + 1, col + MAP_COL + 3);
				}
			}
		}
	}

	public static void hideNextPiece(Console c) {
		for (int row = 0; row < 4; row++) {
			c.putStringAt("      ", row + 1, MAP_COL + 2);
		}
	}

}
