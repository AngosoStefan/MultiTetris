package fr.esiea.ga.tetris.client.Model;

public class Piece {
	public PieceType pieceType;
	public int[][] pieceContent;
	public String pieceChar;

	public Piece() {
		pieceType 		= PieceType.randomPiece();
		pieceContent 	= pieceType.blockContent;
		pieceChar 		= pieceType.blockID;
	}

	public static void main(String[] args) {
		Piece test = new Piece();
		System.out.println();
		printSysouPiece(test);
		System.out.println();
		test.pieceContent = rotatePieceRight(test);
		printSysouPiece(test);
		System.out.println();
		test.pieceContent = rotatePieceRight(test);
		printSysouPiece(test);
		System.out.println();
		test.pieceContent = rotatePieceRight(test);
		printSysouPiece(test);
		System.out.println();
	}
	
	public static int[][] rotatePieceLeft(Piece p) {
		int[][] pieceRotated = new int[4][4];
		int rowRotate = 0;
		
		for (int col = 3; col >= 0; col--) {
			for (int row = 0; row < 4; row++) {
				pieceRotated[rowRotate][row] = p.pieceContent[row][col];
			}
			rowRotate++;
		}
		
		return pieceRotated;
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
	
	public static void printSysouPiece(Piece p) {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (p.pieceContent[row][col] == 1)
					System.out.print(p.pieceChar + " ");
				else
					System.out.print(String.valueOf(p.pieceContent[row][col] + " "));
			}
			System.out.println();
		}
	}
}
