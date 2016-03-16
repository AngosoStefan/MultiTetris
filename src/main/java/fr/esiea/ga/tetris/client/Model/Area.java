package fr.esiea.ga.tetris.client.Model;

import fr.esiea.ga.tetris.client.View.Console;

public class Area {
	public static final int MAP_ROW = 18;
	public static final int MAP_COL = 13;

	private int map_row_backend, map_col_backend;
	public int[][] area;

	private static Area map = null;

	private Area() {
		map_row_backend = MAP_ROW + 1;
		map_col_backend = MAP_COL + 1;
		area = new int[map_row_backend][map_col_backend];
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
	}

	public boolean detecteCollision(int direction, Piece p) {
		int indexBorder;
		int xTmp, yTmp;

		switch (direction) {
		case Piece.DIR_RIGHT:
			indexBorder = p.getPieceBorderIndex(Piece.DIR_RIGHT);
			for (int row = 0; row < 4; row++) {
				if ((p.pieceContent[row][indexBorder] == 1) && (area[p.xPos][p.yPos + indexBorder + 1] == 1)) {
					return true;
				}
			}
			return false;
		case Piece.DIR_BOTTOM:
			indexBorder = p.getPieceBorderIndex(Piece.DIR_BOTTOM);
			for (int col = 0; col < 4; col++) {
				if ((p.pieceContent[indexBorder][col] == 1) && (area[p.xPos + indexBorder + 1][p.yPos + col] == 1)) {
					return true;
				}
			}
			return false;
		case Piece.DIR_LEFT:
			indexBorder = p.getPieceBorderIndex(Piece.DIR_LEFT);
			for (int row = 0; row < 4; row++) {
				if ((p.pieceContent[row][indexBorder] == 1) && (area[p.xPos][p.yPos + indexBorder - 1] == 1)) {
					return true;
				}
			}
			return false;
		default:
			break;
		}

		return true;
	}

	public void printMapDbg(Console c) {
		int fakeArea[][] = this.area.clone();

		for (int row = 0; row < MAP_ROW + 1; row++) {
			for (int col = 0; col < MAP_COL + 1; col++) {
				c.putStringAt(String.valueOf(fakeArea[row][col]), row, col + 26);
				// if(fakeArea[row][col] == 1)
				// c.putStringAt(String.valueOf(fakeArea[row][col]), row, col +
				// 26);
				// else
				// c.putStringAt(" ", row, col + 26);
			}
		}
		// 1 0 0 0 0 0 0 0 0 0 0 0 0 1 douze 0
		// 1════════════1 douze 0
	}
}
