package fr.esiea.ga.tetris.client.Controller;

import java.io.IOException;

import fr.esiea.ga.tetris.client.Model.ConstantChar;
import fr.esiea.ga.tetris.client.Model.ConstantInput;
import fr.esiea.ga.tetris.client.View.Console;
import fr.esiea.ga.tetris.client.View.Menu;
import fr.esiea.ga.tetris.network.gameclient.GameClient;

public class Tetris implements ConstantInput, ConstantChar {
	static SoloGame soloGame;
	static MultiGame multiGame;
	static GameClient gameClient;
	static Thread tGame;
	static Console c = new Console();

	static int choice = -2;
	static int currentInput = 4;

	public static void main(String[] args) {

		Menu.printLogo(c);
		c.putStringAt("Press Enter", 12, 31);
		c.printScreen(true);
		pressEnter(true);
		c.clearScreen();

		/********
		 * MENU *
		 ********/

		while (currentInput != EXIT) {
			do {
				Menu.printLogo(c);
				Menu.printMenu(c, 8, 40);
				currentInput = Menu.printCursor(c, currentInput);
				c.printScreen();

				choice = makeChoice();
				switch (choice) {
				case 2:
					currentInput++;
					Menu.clearCursor(c);
					break;
				case 1:
					currentInput--;
					Menu.clearCursor(c);
					break;
				default:
					break;
				}
			} while (choice != 0);

			switch (currentInput) {
			case MULTI_PLAYER:
				gameClient = new GameClient("127.0.0.1",8000);
				multiGame = new MultiGame(gameClient);
				tGame = new Thread(multiGame);
				tGame.start();
				try {
					tGame.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// multiGame = new GameClient("127.0.0.1", 8000);
				break;
			case SOLO_PLAYER:
				soloGame = new SoloGame();
				tGame = new Thread(soloGame);
				tGame.start();
				try {
					tGame.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case INSTRUCTION:

				break;
			default:
				break;
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
