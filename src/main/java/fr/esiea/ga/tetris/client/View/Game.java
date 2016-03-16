package fr.esiea.ga.tetris.client.View;

import fr.esiea.ga.tetris.client.Model.ConstantChar;
import fr.esiea.ga.tetris.client.Model.Piece;

public class Game implements ConstantChar {
	
	public static void printPiece(Console c, Piece p) {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (p.pieceContent[row][col] == 1)
					c.putStringAt(p.pieceChar, row+p.xPos, col+p.yPos);
				else
					c.putStringAt("0", row+p.xPos, col+p.yPos);
			}
		}
	}
	
	public static void hidePrevPiecePos(Console c, Piece p) {
		for (int i = 0; i < 4; i++) {
			c.putStringAt("    ", (i+p.xPrevPos), p.yPrevPos);
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