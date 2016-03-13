package fr.esiea.ga.tetris.client.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import fr.esiea.ga.tetris.client.Model.ConstantInput;
import fr.esiea.ga.tetris.client.View.Console;
import fr.esiea.ga.tetris.client.View.Game;

public class SoloGame implements Runnable, ConstantInput {
	Console c;
	int currentInput = -2;
	long start;
	
	public SoloGame(Console c) {
		this.c = c;
		
		// Get current time
		start = System.currentTimeMillis();
	}

	public void run() {
		c.clearScreen();
		Game.printArea(c,15,13,0,0);
		c.printScreen();
		
		while (currentInput != TOUCH_EXIT) {
			currentInput = makeMove();
			Game.printTime(c, start);
			// hideRawCursor();
			c.printScreen();
		}
		c.clearScreen();
	}
	
	// Handle input related to the arrow cursor
	private int makeMove() {
		int charCode = -2;

		try {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
				charCode = RawConsoleInput.read(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// System.out.println(String.valueOf(charCode));
			switch (charCode) {
			case TOUCH_EXIT:
				return TOUCH_EXIT;
			case TOUCH_ENTER:
				return TOUCH_ENTER;
			case TOUCH_LEFT:
				return TOUCH_LEFT;
			case TOUCH_RIGHT:
				return TOUCH_RIGHT;
			default:
				return -2;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return charCode;
	}

}