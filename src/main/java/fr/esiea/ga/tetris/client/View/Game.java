package fr.esiea.ga.tetris.client.View;

import fr.esiea.ga.tetris.client.Model.ConstantChar;
import fr.esiea.ga.tetris.client.Model.Piece;

public class Game implements ConstantChar {
	
	public static void printPiece(Console c, Piece p) {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (p.pieceContent[row][col] == 1)
					c.putStringAt(p.pieceChar, row+p.xPos, col+p.yPos);
//				else
//					c.putStringAt("2", row+p.xPos, col+p.yPos);
			}
		}
	}
	
	public static void printPieceDBG(Console c, Piece p) {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (p.pieceContent[row][col] == 1)
					c.putStringAt("1", row+p.xPos, col+p.yPos+26);
				else
					c.putStringAt("2", row+p.xPos, col+p.yPos+26);
			}
		}
	}

	public static void printInfoDBG(Console c, Piece p) {
		c.putStringAt("xPrevPos : " + String.valueOf(p.xPrevPos), 0, 48);
		c.putStringAt("yPrevPos : " + String.valueOf(p.yPrevPos), 1, 48);
		c.putStringAt("xPos : " + String.valueOf(p.xPos), 2, 48);
		c.putStringAt("yPos : " + String.valueOf(p.yPos), 3, 48);
		c.putStringAt("IndexLeft : " + String.valueOf(p.getLastBorderIndex(Piece.DIR_LEFT)), 4, 48);
		c.putStringAt("IndexRight : " + String.valueOf(p.getLastBorderIndex(Piece.DIR_RIGHT)), 5, 48);
	}
	public static void hidePrintInfoDBG(Console c) {
		for (int i = 0; i < 6; i++) {
			c.putStringAt("             ", i, 48);
		}
	}
	
	public static void hidePrevPiecePos(Console c, Piece p, boolean top) {
		Piece pCopy = p;
		
		if (top)
			pCopy.rotatePieceRight();
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (pCopy.pieceContent[row][col] == 1)
					c.putStringAt(" ", row+pCopy.xPrevPos, col+pCopy.yPrevPos);
			}
		}
	}
	
	public static void hidePrevPiecePosDBG(Console c, Piece p, boolean top) {
		Piece pCopy = p;
		
		if (top)
			pCopy.rotatePieceRight();
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (pCopy.pieceContent[row][col] == 1)
					c.putStringAt(" ", row+pCopy.xPrevPos, col+pCopy.yPrevPos+26);
			}
		}
	}
	
	public static void hideRawCursor(Console c) {
		// Good row but didn't work
		c.putStringAt("   ", 23, 0);
	}
	
	public static void printTime(Console c, long start) {
		// http://stackoverflow.com/questions/692569/
		// how-can-i-count-the-time-it-takes-a-function-to-complete-in-java
		long elapsedTimeMillis = System.currentTimeMillis()-start;
		int min = (int) (elapsedTimeMillis/(60*1000));
		int sec = (int) (elapsedTimeMillis/1000);
		c.putStringAt(String.valueOf(min) + " min ", 5, 15); 
		c.putStringAt(String.valueOf((min != 0) ? sec-min*60 : sec) + " sec", 6, 15);
	}
	
	public static void printArea(Console c, int row, int col, int xPos, int yPos) {
		// Print area
		printRectangle(c, row, col, xPos, yPos);
		printRectangle(c, row, 8, xPos, yPos+col);
		
		// Handle specific part
		c.putStringAt(ROW_UNDER, 0, col);
		c.putStringAt(ROW_ABOVE, row, col);
		c.putStringAt(COL_RIGHT, 4, col);
		c.putStringAt(COL_LEFT, 4, col+8);
		for (int i = col+1; i < col+8; i++)
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