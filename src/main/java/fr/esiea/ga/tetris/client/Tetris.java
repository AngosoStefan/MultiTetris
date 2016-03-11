package fr.esiea.ga.tetris.client;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.Charset;

public class Tetris {

	public static int widthAreaGame = 40;
	public static int heigthAreaGame = 20;

	public static void main(String[] args) {

		Console c = new Console();
		printLogo();
		System.out.println("Press Enter");
		
		int charCode;
		try {
			charCode = RawConsoleInput.read(true);
			System.out.println("Read : " + String.valueOf(charCode));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void printLogo() {
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

	public static void printArea(Console c) {
		int rowCursor, colCursor;

		for (rowCursor = 0; rowCursor <= heigthAreaGame; rowCursor++) {
			for (colCursor = 0; colCursor <= widthAreaGame; colCursor++) {
				// Border
				if ((rowCursor == 0 && colCursor == 0) || (rowCursor == 0 && colCursor == widthAreaGame)
						|| (rowCursor == heigthAreaGame && colCursor == 0)
						|| (rowCursor == heigthAreaGame && colCursor == widthAreaGame)) {

					c.putStringAt("\u25A0", rowCursor, colCursor);
				}
			}
		}
		c.printScreen();
	}
}
