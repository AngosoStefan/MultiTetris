package fr.esiea.ga.tetris.client.Controller;

import java.io.IOException;

import fr.esiea.ga.tetris.client.Model.ConstantChar;
import fr.esiea.ga.tetris.client.Model.ConstantInput;
import fr.esiea.ga.tetris.client.View.Console;
import fr.esiea.ga.tetris.client.View.Menu;

public class Tetris implements ConstantInput, ConstantChar {

	public static void main(String[] args) {
		int choice = -2;
		int currentInput = 4;

		Console c = new Console();

//		Menu.printLogo(c);
//		c.putStringAt("Press Enter", 12, 31);
//		c.printScreen(true);
//		pressEnter(true);
//		c.clearScreen();

		/********
		 * MENU *
		 ********/

//		while (currentInput != EXIT) {
//			do {
//				Menu.printLogo(c);
//				Menu.printMenu(c, 8, 40);
//				currentInput = Menu.printCursor(c, currentInput);
//				c.printScreen();
//
//				choice = makeChoice();
//				switch (choice) {
//				case 2:
//					currentInput++;
//					Menu.clearCursor(c);
//					break;
//				case 1:
//					currentInput--;
//					Menu.clearCursor(c);
//					break;
//				default:
//					break;
//				}
//			} while (choice != 0);
//
//			switch (currentInput) {
//			case MULTI_PLAYER:
//
//				break;
//			case SOLO_PLAYER:
			     SoloGame sGame = new SoloGame(c);
			     Thread tGame = new Thread(sGame);
			     tGame.start();
			     try {
			    	 tGame.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				break;
//			case INSTRUCTION:
//
//				break;
//			default:
//				break;
//			}
//		}
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
	
	// Handle input related to the arrow cursor
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
}
