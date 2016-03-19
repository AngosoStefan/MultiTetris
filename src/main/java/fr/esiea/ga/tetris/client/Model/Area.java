package fr.esiea.ga.tetris.client.Model;

public class Area implements ConstantGame {
	private int map_row_backend, map_col_backend;
	public int[][] area;
	public String[][] area2;

	private static Area map = null;

	private Area() {
		map_row_backend = MAP_ROW + 1;
		map_col_backend = MAP_COL + 1;
		area = new int[map_row_backend][map_col_backend];
		area2 = new String[map_row_backend][map_col_backend];
		initAreaBorder();
	}

	public static Area getInstance() {
		if (map == null)
			map = new Area();

		return map;
	}

	private void initAreaBorder() {
		for (int row = 0; row < MAP_ROW + 1; row++) {
			for (int col = 0; col < MAP_COL + 1; col++) {
				if (col == 0 || col == MAP_COL)
					area[row][col] = 1;
				if (row == MAP_ROW)
					area[row][col] = 1;
			}
		}
	}

	public void updateArea(Piece p) {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (p.pieceContent[row][col] == 1)
					area[p.xPos + row][p.yPos + col] = 1;
			}
		}
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (p.pieceContent[row][col] == 1)
					area2[p.xPos + row][p.yPos + col] = p.pieceType.blockID;
			}
		}
	}

	public synchronized boolean detecteCollision(int direction, Piece p) {
		int lastBorderIndex;
		int xTmp, yTmp;

		switch (direction) {
		case Piece.DIR_RIGHT:
			lastBorderIndex = p.getLastBorderIndex(Piece.DIR_RIGHT);
			for (int row = 0; row < 4; row++) {
				if ((p.pieceContent[row][lastBorderIndex] == 1)
						&& (area[p.xPos + row][p.yPos + lastBorderIndex + 1] == 1)) {
					return true;
				}
			}
			return false;
		case Piece.DIR_BOTTOM:
			lastBorderIndex = p.getLastBorderIndex(Piece.DIR_BOTTOM);
			for (int col = 0; col < 4; col++) {
				if ((p.pieceContent[lastBorderIndex][col] == 1)
						&& (area[p.xPos + lastBorderIndex + 1][p.yPos + col] == 1)) {
					return true;
				}
			}
			return false;
		case Piece.DIR_LEFT:
			lastBorderIndex = p.getLastBorderIndex(Piece.DIR_LEFT);
			for (int row = 0; row < 4; row++) {
				if ((p.pieceContent[row][lastBorderIndex] == 1)
						&& (area[p.xPos + row][p.yPos + lastBorderIndex - 1] == 1)) {
					return true;
				}
			}
			return false;
		case Piece.DIR_TOP:
			Piece pCopy = new Piece();
			pCopy.xPos = p.xPos;
			pCopy.yPos = p.yPos;
			pCopy.pieceContent = p.pieceContent;
			pCopy.rotatePieceLeft();
			
			for (int col = 0; col < 4; col++) {
				for (int row = 0; row < 4; row++) {
					if ((pCopy.pieceContent[row][col] == 1)
							&& (area[pCopy.xPos + row][pCopy.yPos + col] == 1)) {
						return true;
					}
				}
			}
			return false;
		default:
			break;
		}

		return true;
	}
}
