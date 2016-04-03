package fr.esiea.ga.tetris.client.Model;

public class Piece implements ConstantNetworkMessage {
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
		// pieceType = PieceType.PIECE3;
		pieceContent = pieceType.blockContent;
		pieceChar = pieceType.blockID;
		xPrevPos = xPos = 1;
		yPrevPos = yPos = 5;
	}
	
	public Piece(int typePiece) {
		switch (typePiece) {
		case 1:
			pieceType = PieceType.PIECE1;
			break;
		case BLOCK2:
			pieceType = PieceType.PIECE2;
			break;
		case BLOCK3:
			pieceType = PieceType.PIECE3;
			break;
		case BLOCK4:
			pieceType = PieceType.PIECE4;
			break;
		case BLOCK5:
			pieceType = PieceType.PIECE5;
			break;
		case BLOCK6:
			pieceType = PieceType.PIECE6;
			break;
		case BLOCK7:
			pieceType = PieceType.PIECE7;
			break;
		default:
			break;
		}
		pieceContent = pieceType.blockContent;
		pieceChar = pieceType.blockID;
		xPrevPos = xPos = 1;
		yPrevPos = yPos = 32;
	}

	public void update(int direction) {
		switch (direction) {
		case (Piece.DIR_BOTTOM):
			savePrevPosPiece();
			xPos++;
			break;
		case (Piece.DIR_RIGHT):
			savePrevPosPiece();
			yPos++;
			break;
		case (Piece.DIR_LEFT):
			savePrevPosPiece();
			yPos--;
			break;
		case (Piece.DIR_TOP):
			savePrevPosPiece();
			rotatePieceLeft();
			break;
		default:
//			savePrevPosPiece();
//			xPos++;
			break;
		}
	}
	
	private void savePrevPosPiece() {
		xPrevPos = xPos;
		yPrevPos = yPos;
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

	public void rotatePieceRight() {
		int[][] pieceRotated = new int[4][4];
		int rowRotate = 0;

		for (int row = 3; row >= 0; row--) {
			for (int col = 0; col < 4; col++) {
				pieceRotated[col][rowRotate] = pieceContent[row][col];
			}
			rowRotate++;
		}
		pieceContent = pieceRotated.clone();
	}
	
	public int getLastBorderIndex(int direction) {
		int i;
		boolean flag = false;
		// We are looking for index not empty DEPENDING ON piece direction
		switch (direction) {
		case Piece.DIR_RIGHT:
			i = 3;
			while(i != -1) {
				for (int j = 0; j < 4; j++) {
					if (pieceContent[j][i] == 1)
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
					if (pieceContent[i][j] == 1)
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
					if (pieceContent[j][i] == 1)
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
