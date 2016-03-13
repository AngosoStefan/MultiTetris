package fr.esiea.ga.tetris.client.Model;

public class Piece {
	public static final int DIR_TOP			= 4;
	public static final int DIR_RIGHT		= 3;
	public static final int DIR_BOTTOM		= 2;
	public static final int DIR_LEFT		= 1;
	
	public PieceType pieceType;
	public int[][] pieceContent;
	public String pieceChar;
	public int xPos, xPrevPos, yPos, yPrevPos;

	public Piece() {
		pieceType 		= PieceType.randomPiece();
		pieceContent 	= pieceType.blockContent;
		pieceChar 		= pieceType.blockID;
		xPrevPos = xPos = 0;
		yPrevPos = yPos	= 5;
	}

	public void update(int direction) {
		switch (direction) {
		case (Piece.DIR_BOTTOM):
			xPrevPos = xPos;
			yPrevPos = yPos;
			xPos++;
			rotatePieceLeft();
			break;
		case (Piece.DIR_RIGHT):
//			xPrevPos = xPos;
//			yPrevPos = yPos;
//			yPos++;
			break;
		case (Piece.DIR_LEFT):
//			xPrevPos = xPos;
//			yPrevPos = yPos;
//			yPos--;
			break;
		case (Piece.DIR_TOP):
//			xPrevPos = xPos;
//			yPrevPos = yPos;
//			yPos--;
			break;
		default:
//			xPrevPos = xPos;
//			yPrevPos = yPos;
//			this.xPos++;
			break;
		}
	}
	
	/* TODO
	 * Suppr row 0 0 0 0 tout en haut de chaque pieceContent SI present
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
	
//	public static void printSysouPiece(Piece p) {
//		for (int row = 0; row < 4; row++) {
//			for (int col = 0; col < 4; col++) {
//				if (p.pieceContent[row][col] == 1)
//					System.out.print(p.pieceChar + " ");
//				else
//					System.out.print(String.valueOf(p.pieceContent[row][col] + " "));
//			}
//			System.out.println();
//		}
	// }
}
