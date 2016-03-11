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

		Console c = new Console();
		c.clearScreen();

		printLogo();
		pressEnter();
		
		c.clearScreen();
		printLogo();

		printRectangle(c, 8, 40);
	}
	
	public static void printRectangle(Console c, int row, int col) {
		for (int rowCursor = 0; rowCursor <= row; rowCursor++) {
			for (int colCursor = 0; colCursor <= col; colCursor++) {
				if (rowCursor == 0 && colCursor == 0) {
					c.putStringAt(CORNER_TOP_LEFT, rowCursor, colCursor);
				} else if (rowCursor == 0 && colCursor == col) {
					c.putStringAt(CORNER_TOP_RIGHT, rowCursor, colCursor);
				} else if (rowCursor == row && colCursor == 0) {
					c.putStringAt(CORNER_BOTTOM_LEFT, rowCursor, colCursor);
				} else if (rowCursor == row && colCursor == col) {
					c.putStringAt(CORNER_BOTTOM_RIGHT, rowCursor, colCursor);
				} else if (rowCursor == 0 && colCursor != 0 && colCursor != col) {
					c.putStringAt(ROW, rowCursor, colCursor);
				} else if (rowCursor == row && colCursor != 0 && colCursor != col) {
					c.putStringAt(ROW, rowCursor, colCursor);
				} else if (colCursor == 0 && rowCursor != 0 && rowCursor != row) {
					c.putStringAt(COLUMN, rowCursor, colCursor);
				} else if (colCursor == col && rowCursor != 0 && rowCursor != row) {
					c.putStringAt(COLUMN, rowCursor, colCursor);
				}
			}
		}
		c.printScreen();
	}

	public static void pressEnter() {
		int charCode;
		for (int space = 0; space <= 28; space++)
			System.out.print(" ");
		System.out.println("Press Enter");

		try {
			do {
				charCode = RawConsoleInput.read(true);
				// DEBUG System.out.println("Key pressed : " + String.valueOf(charCode));
			} while (charCode != TOUCH_ENTER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printLogo() {
		String line;
		try {
			// Work only into the external Console
			InputStream fis = new FileInputStream("../../resources/tetris_logo");
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);

			while ((line = br.readLine()) != null) {
				// Deal with the line
				for (int i = 0; i < line.length(); i++) {
					try {
						TimeUnit.MILLISECONDS.sleep(1);
						if (line.charAt(i) == 'a')
							System.out.print("\n");
						else
							System.out.print(line.charAt(i));
					} catch (InterruptedException e) {
						// Handle exception
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
