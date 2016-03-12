package fr.esiea.ga.tetris.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class Tetris implements ConstantInput, ConstantChar {

	public static int widthAreaGame = 40;
	public static int heigthAreaGame = 20;

	public static void main(String[] args) {
		int choice = -2;
		int currentInput = 4;

		Console c = new Console();

		printLogo(c);
		c.putStringAt("Press Enter", 12, 31);
		c.printScreen(true);
		pressEnter(true);
		c.clearScreen();

		/********
		 * MENU *
		 ********/

		do {
			printLogo(c);
			printMenu(c, 8, 40);
			currentInput = printCursor(c, currentInput);
			c.printScreen();
			
			choice = makeChoice();
			switch (choice) {
			case 2:
				currentInput++;
				c.clearScreen();
				break;
			case 1:
				currentInput--;
				c.clearScreen();
				break;
			default:
				break;
			}
		} while (choice != 0);
	}

	public static int printCursor(Console c, int currentInput) {
		if (currentInput == 0)
			currentInput = 4;
		if (currentInput == 5)
			currentInput = 1;
		switch (currentInput) {
		case 4:
			c.putStringAt(ARROW, 12, 22);
			break;
		case 3:
			c.putStringAt(ARROW, 13, 22);
			break;
		case 2:
			c.putStringAt(ARROW, 14, 22);
			break;
		case 1:
			c.putStringAt(ARROW, 15, 22);
			break;
		default:
			break;
		}
		return currentInput;
	}

	public static int makeChoice() {
		int charCode = -2;
		try {
			charCode = RawConsoleInput.read(true);
			switch (charCode) {
			case TOUCH_ENTER:
				return 0;
			case TOUCH_TOP:
				return 2;
			case TOUCH_BOTTOM:
				return 1;
			default:
				return -2;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return charCode;
	}

	public static void printMenu(Console c, int row, int col) {
		final String tOnline = "Multiplayer game";
		final String tSolo = "Soloplayer game";
		final String instruct = "Instruction";
		final String exit = "Exit";

		printRectangle(c, row, col, 10, 18);
		c.putStringAt(tOnline, 12, 28);
		c.putStringAt(tSolo, 13, 28);
		c.putStringAt(instruct, 14, 28);
		c.putStringAt(exit, 15, 28);
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

	public static void pressEnter(boolean wait) {
		int charCode;

		try {
			do {
				charCode = RawConsoleInput.read(wait);
				// DEBUG
				// System.out.println("Key pressed : " +
				// String.valueOf(charCode));
			} while (charCode != TOUCH_ENTER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printLogo(Console c) {
		int nbLine = 0;
		String line;
		try {
			// Work only into the external Console
			InputStream fis = new FileInputStream("../../resources/tetris_logo");
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);

			while ((line = br.readLine()) != null) {
				c.putStringAt(line, nbLine, 0);
				nbLine++;
			}
			fis.close();
			isr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
