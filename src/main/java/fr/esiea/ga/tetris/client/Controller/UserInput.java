package fr.esiea.ga.tetris.client.Controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.annotation.Nullable;

import fr.esiea.ga.tetris.client.Model.Area;
import fr.esiea.ga.tetris.client.Model.ConstantInput;
import fr.esiea.ga.tetris.client.Model.Piece;
import fr.esiea.ga.tetris.client.View.Console;
import fr.esiea.ga.tetris.client.View.Game;

//Responsable Input LEFT RIGHT et MALUS
public class UserInput implements Runnable, ConstantInput {
	Console c;
	Area map;
	private volatile boolean done = false;
	private volatile boolean running = true;

	private Piece currentPiece;
	private Piece dbgPiece;
	boolean pieceDie = false;

	private int currentInput = -2;

	public UserInput(Console c, Area map, Piece currentPiece, @Nullable Piece dbgPiece) {
		this.c = c;
		this.map = map;
		this.currentPiece = currentPiece;
		this.dbgPiece = dbgPiece;
	}

	public void shutdown() {
		done = true;
	}

	public void setNewPiece(Piece currentPiece) {
		this.currentPiece = currentPiece;
		this.pieceDie = false;
	}

	public boolean getPieceDie() {
		return pieceDie;
	}

	public void setDone(boolean run) {
		this.done = run;
	}
	
	public boolean getDone() {
		return done;
	}
	
    public void pauseThread() throws InterruptedException
    {
        running = false;
    }

    public void resumeThread()
    {
        running = true;
    }
	

	public void run() {
		int i = 0;
		int prevCharCode = currentInput;
		
		while (!done) {
			try {
				TimeUnit.MILLISECONDS.sleep(75);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (dbgPiece != null) {
				c.putStringAt(String.valueOf(i), 8, 48);
				c.putStringAt("prevInput : " + String.valueOf(prevCharCode), 9, 48);
				c.putStringAt("currentInput : " + String.valueOf(currentInput), 10, 48);
			}

			i++;
			Game.printPiece(c, currentPiece);

			// REAL VALUE
			if (running) {
				currentInput = listenMove(prevCharCode);
				prevCharCode = currentInput;
				// pieceDie = actionInput(currentInput);
				//if (actionInput(currentInput) == 0)
				actionInput(currentInput);	
			} else {
				
			}
			
			c.printScreen();
			
			// Specific case if rotation
			if (currentInput == TOUCH_TOP) {
				Game.hidePrevPiecePos(c, currentPiece, currentInput);
				if (dbgPiece != null)
					Game.hidePrevPiecePosDBG(c, dbgPiece, true);
			}
			if (currentInput == TOUCH_RIGHT || currentInput == TOUCH_LEFT) {
				// c.printScreen();
				Game.hidePrevPiecePos(c, currentPiece, currentInput);
				if (dbgPiece != null)
					Game.hidePrevPiecePosDBG(c, dbgPiece, false);
			}
			if (dbgPiece != null) {
//				c.putStringAt(String.valueOf(i), 8, 48);
//				c.putStringAt("                 ", 9, 48);
//				c.putStringAt("                 ", 10, 48);
			}
		}
	}

	private void actionInput(int currentInput) {
		boolean pieceDie = false;

		switch (currentInput) {
		case (TOUCH_TOP):
			// TODO COLLISION TOP RETURN TRUE
			currentPiece.update(Piece.DIR_TOP);
			break;
		case (TOUCH_RIGHT):
			if (!map.detecteCollision(Piece.DIR_RIGHT, currentPiece)) {
				currentPiece.update(Piece.DIR_RIGHT);
			}
			break;
		case (TOUCH_LEFT):
			if (!map.detecteCollision(Piece.DIR_LEFT, currentPiece)) {
				currentPiece.update(Piece.DIR_LEFT);
			}
			break;
		case (TOUCH_EXIT):
			done = true;
			break;
		default:
			 break;
		}
		// return 1;

		// return pieceDie;
	}

	private int listenMove(int prevCharCode) {
		int charCode = -2;

		try {
			charCode = RawConsoleInput.read(false);
			if (prevCharCode == charCode)
				charCode = -2;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(String.valueOf(charCode));
		switch (charCode) {
		case TOUCH_TOP:
			return TOUCH_TOP;
		case TOUCH_RIGHT:
			return TOUCH_RIGHT;
		case TOUCH_BOTTOM:
			return TOUCH_BOTTOM;
		case TOUCH_LEFT:
			return TOUCH_LEFT;
		case TOUCH_EXIT:
			return TOUCH_EXIT;
		default:
			return -2;
		}
	}

}