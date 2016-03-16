package fr.esiea.ga.tetris.client.Model;

public class Piece {
	public static final int DIR_TOP = 4;
	public static final int DIR_RIGHT = 3;
	public static final int DIR_BOTTOM = 2;
	public static final int DIR_LEFT = 1;

	public PieceType pieceType;
	public int[][] pieceContent;
	public String pieceChar;
	public int xPos, xPrevPos, yPos, yPrevPos;

	public Piece() {
		pieceType = PieceType.randomPiece();
		pieceContent = pieceType.blockContent;
		pieceChar = pieceType.blockID;
		xPrevPos = xPos = 0;
		yPrevPos = yPos = 5;
	}

	public void update(int direction) {
		switch (direction) {
		case (Piece.DIR_BOTTOM):
			movePieceDown();
			break;
		case (Piece.DIR_RIGHT):
			xPrevPos = xPos;
			yPrevPos = yPos;
			yPos++;
			movePieceDown();
			break;
		case (Piece.DIR_LEFT):
			xPrevPos = xPos;
			yPrevPos = yPos;
			yPos--;
			movePieceDown();
			break;
		case (Piece.DIR_TOP):
			rotatePieceLeft();
			movePieceDown();
			break;
		default:
			movePieceDown();
			break;
		}
	}

	public void movePieceDown() {
		xPrevPos = xPos;
		yPrevPos = yPos;
		xPos++;
	}

	/*
	 * TODO Suppr row 0 0 0 0 tout en haut de chaque pieceContent SI present
	 */
	public void rotatePieceLeft() {
		int[][] pieceRotated = new int[4][4];
		int rowRotate = 0;

		for (int col = 3; col >= 0; col--) {
			for (int row = 0; row < 4; row++) {
				pieceRotated[rowRotate][row] = pieceContent[row][col];
			}
			rowRotate++;
		}

		pieceContent = pieceRotated.clone();
	}

	public static int[][] rotatePieceRight(Piece p) {
		int[][] pieceRotated = new int[4][4];
		int rowRotate = 0;

		for (int row = 3; row >= 0; row--) {
			for (int col = 0; col < 4; col++) {
				pieceRotated[col][rowRotate] = p.pieceContent[row][col];
			}
			rowRotate++;
		}

		return pieceRotated;
	}
	
	public static int getPieceBorderIndex(Piece p, int direction) {
		int i;
		boolean flag = false;
		
		switch (direction) {
		case Piece.DIR_RIGHT:
			i = 3;
			while(i != -1) {
				for (int j = 0; j < 4; j++) {
					if (p.pieceContent[j][i] == 1)
						flag = true;
				}
				if (flag) return i;
				i--;
			}
			break;
		case Piece.DIR_BOTTOM:
			i = 3;
			while(i != -1) {
				for (int j = 0; j < 4; j++) {
					if (p.pieceContent[i][j] == 1)
						flag = true;
				}
				if (flag) return i;
				i--;
			}
			break;
		case Piece.DIR_LEFT:
			i = 0;
			while(i != 4) {
				for (int j = 0; j < 4; j++) {
					if (p.pieceContent[j][i] == 1)
						flag = true;
				}
				if (flag) return i;
				i++;
			}
			break;

		default:
			break;
		}
		
		return 0;
	}

//	DBG
//	public static void getPieceBorder(Piece p, int direction) {
//		int i = 3;
//		boolean flag = false;
//		switch (direction) {
//		case Piece.DIR_RIGHT:
//			while(i != -1) {
//				for (int j = 0; j < 4; j++) {
//					System.out.print(String.valueOf(p.pieceContent[j][i]));
//					if (p.pieceContent[j][i] == 1)
//						flag = true;
//				}
//				System.out.println();
//				if (flag) break;
//				i--;
//			}
//			
//			System.out.println("Clone");
//			for (int j = 0; j < 4; j++) {
//				System.out.print(String.valueOf(p.pieceContent[j][i]));
//			}
//			System.out.println();
//			break;
//		default:
//			break;
//		}
//	}
	
	// public static void printSysouPiece(Piece p) {
	// for (int row = 0; row < 4; row++) {
	// for (int col = 0; col < 4; col++) {
	// if (p.pieceContent[row][col] == 1)
	// System.out.print(p.pieceChar + " ");
	// else
	// System.out.print(String.valueOf(p.pieceContent[row][col] + " "));
	// }
	// System.out.println();
	// }
	// }
}