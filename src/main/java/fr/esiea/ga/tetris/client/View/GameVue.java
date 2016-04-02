package fr.esiea.ga.tetris.client.View;

import fr.esiea.ga.tetris.client.Model.ConstantChar;
import fr.esiea.ga.tetris.client.Model.ConstantGame;
import fr.esiea.ga.tetris.client.Model.ConstantInput;

public class GameVue implements ConstantChar, ConstantInput, ConstantGame {

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
		c.putStringAt(COL_RIGHT, 5, col);
		c.putStringAt(COL_LEFT, 5, col + 9);
		for (int i = col + 1; i < col + 9; i++)
			c.putStringAt(ROW, 5, i);
	}
	
	public static void printAreaOtherPlayer(Console c, int row, int col) {
		printRectangle(c, row, col, 0, col + 9 + 3);
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