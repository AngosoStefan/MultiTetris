package fr.esiea.ga.tetris.client.View;

import fr.esiea.ga.tetris.client.Model.Area;
import fr.esiea.ga.tetris.client.Model.ConstantChar;
import fr.esiea.ga.tetris.client.Model.ConstantGame;
import fr.esiea.ga.tetris.client.Model.ConstantInput;
import fr.esiea.ga.tetris.client.Model.Piece;

public class Game implements ConstantChar, ConstantInput, ConstantGame {

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
//		c.putStringAt("\033[31;1mHello\033[0m", 6, 48);
//		c.putStringAt(ANSI_RED + "LOL : " + ANSI_RESET + " " + String.valueOf(lol % 225), 7, 48);
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

	public static void hideRawCursor(Console c) {
		// Good row but didn't work
		c.putStringAt("   ", 23, 0);
	}

	public static void printTime(Console c, long start) {
		// http://stackoverflow.com/questions/692569/
		// how-can-i-count-the-time-it-takes-a-function-to-complete-in-java
		long elapsedTimeMillis = System.currentTimeMillis() - start;
		int min = (int) (elapsedTimeMillis / (60 * 1000));
		int sec = (int) (elapsedTimeMillis / 1000);
		c.putStringAt(String.valueOf(min) + " min ", 7, 18);
		c.putStringAt(String.valueOf((min != 0) ? sec - min * 60 : sec) + " sec", 8, 18);
	}

	public static void printArea(Console c, int row, int col, int xPos, int yPos) {
		// Print area
		printRectangle(c, row, col, xPos, yPos);
		printRectangle(c, row, 9, xPos, yPos + col);

		// Handle specific part
		c.putStringAt(ROW_UNDER, 0, col);
		c.putStringAt(ROW_ABOVE, row, col);
		c.putStringAt(COL_RIGHT, 4, col);
		c.putStringAt(COL_LEFT, 4, col + 9);
		for (int i = col + 1; i < col + 9; i++)
			c.putStringAt(ROW, 4, i);
	}

	public static void printRectangle(Console c, int row, int col, int xPos, int yPos) {
		for (int rowCursor = xPos; rowCursor <= row + xPos; rowCursor++) {
			for (int colCursor = yPos; colCursor <= col + yPos; colCursor++) {
				if (rowCursor == 0 + xPos && colCursor == 0 + yPos) {
					c.putStringAt(CORNER_TOP_LEFT, rowCursor, colCursor);
				} else if (rowCursor == 0 + xPos && colCursor == col + yPos) {
					c.putStringAt(CORNER_TOP_RIGHT, rowCursor, colCursor);
				} else if (rowCursor == row + xPos && colCursor == 0 + yPos) {
					c.putStringAt(CORNER_BOTTOM_LEFT, rowCursor, colCursor);
				} else if (rowCursor == row + xPos && colCursor == col + yPos) {
					c.putStringAt(CORNER_BOTTOM_RIGHT, rowCursor, colCursor);
				} else if (rowCursor == 0 + xPos && colCursor != 0 + yPos && colCursor != col + yPos) {
					c.putStringAt(ROW, rowCursor, colCursor);
				} else if (rowCursor == row + xPos && colCursor != 0 + yPos && colCursor != col + yPos) {
					c.putStringAt(ROW, rowCursor, colCursor);
				} else if (colCursor == 0 + yPos && rowCursor != 0 + xPos && rowCursor != row + xPos) {
					c.putStringAt(COLUMN, rowCursor, colCursor);
				} else if (colCursor == col + yPos && rowCursor != 0 + xPos && rowCursor != row + xPos) {
					c.putStringAt(COLUMN, rowCursor, colCursor);
				}
			}
		}
	}
}