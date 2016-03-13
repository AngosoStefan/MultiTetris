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
//		printSysouPiece(test);
	}
	
	public static int[][] rotatePieceRight(Piece p) {
		int[][] pieceRotated = new int[4][4];
		int[] rowTmp = new int[4];
		
		for (int col = 0; col < 4; col++) {
			for (int row = 0; row < 4; row++) {
				rowTmp[row] = p.pieceContent[row][col];
//				System.out.print(String.valueOf(rowTmp[row]));
			}
//			pieceRotated[col] = rowTmp.clone();
			if (col == 0)
				pieceRotated[3] = rowTmp.clone();
			else
				pieceRotated[col-1] = rowTmp.clone();
		}
		
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (pieceRotated[row][col] == 1)
					System.out.print(p.pieceChar + " ");
				else
					System.out.print(String.valueOf(pieceRotated[row][col] + " "));
			}
			System.out.println();
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
