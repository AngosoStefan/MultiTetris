package fr.esiea.ga.tetris.client.View;

import fr.esiea.ga.tetris.client.Model.Area;
import fr.esiea.ga.tetris.client.Model.Piece;

public class DbgVue {
	public static void printMapDbg(Console c, Area map) {
		int fakeArea[][] = map.area.clone();

		for (int row = 0; row < 20 + 1; row++) {
			for (int col = 0; col < 16 + 1; col++) {
				c.putStringAt(String.valueOf(fakeArea[row][col]), row, col + 28);
			}
		}
	}

	public static void printPieceDBG(Console c, Piece p) {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (p.pieceContent[row][col] == 1)
					c.putStringAt("1", row + p.xPos, col + p.yPos + 28);
				else
					c.putStringAt("2", row + p.xPos, col + p.yPos + 28);
			}
		}
	}

	public static void printInfoDBG(Console c, Piece p, int lol) {
		c.putStringAt("xPrevPos : " + String.valueOf(p.xPrevPos), 0, 48);
		c.putStringAt("yPrevPos : " + String.valueOf(p.yPrevPos), 1, 48);
		c.putStringAt("xPos : " + String.valueOf(p.xPos), 2, 48);
		c.putStringAt("yPos : " + String.valueOf(p.yPos), 3, 48);
		c.putStringAt("IndexLeft : " + String.valueOf(p.getLastBorderIndex(Piece.DIR_LEFT)), 4, 48);
		c.putStringAt("IndexRight : " + String.valueOf(p.getLastBorderIndex(Piece.DIR_RIGHT)), 5, 48);
		// c.putStringAt("\033[31;1mHello\033[0m", 6, 48);
		// c.putStringAt(ANSI_RED + "LOL : " + ANSI_RESET + " " +
		// String.valueOf(lol % 225), 7, 48);
	}

	public static void hidePrintInfoDBG(Console c) {
		for (int i = 0; i < 6; i++) {
			c.putStringAt("             ", i, 48);
		}
	}

	public static void hidePrevPiecePosDBG(Console c, Piece p, boolean top) {
		Piece pCopy = p;

		if (top)
			pCopy.rotatePieceRight();
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (pCopy.pieceContent[row][col] == 1)
					c.putStringAt(" ", row + pCopy.xPrevPos, col + pCopy.yPrevPos + 26);
			}
		}
	}
}
