package fr.esiea.ga.tetris.client.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import fr.esiea.ga.tetris.client.Model.ConstantChar;

public class Menu implements ConstantChar {
	
	public static void printMenu(Console c, int row, int col) {
		final String tOnline = "Multiplayer game";
		final String tSolo = "Soloplayer game";
		final String instruct = "Instruction";
		final String exit = "Exit";

		Game.printRectangle(c, row, col, 10, 18);
		c.putStringAt(tOnline, 12, 28);
		c.putStringAt(tSolo, 13, 28);
		c.putStringAt(instruct, 14, 28);
		c.putStringAt(exit, 15, 28);
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
	
	// Print current arrow cursor to choose an action
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

	public static void clearCursor(Console c) {
		for(int i = 12; i < 16; i++ )
			c.putStringAt("  ", i, 22);
	}
}
