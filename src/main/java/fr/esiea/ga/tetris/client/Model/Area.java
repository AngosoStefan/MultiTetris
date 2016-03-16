package fr.esiea.ga.tetris.client.Model;

import fr.esiea.ga.tetris.client.View.Console;

public class Area {
	public static final int MAP_ROW	= 18;
	public static final int MAP_COL	= 13;
	
	private int map_row_backend, map_col_backend;
	public int[][] area;
	
	private static Area map = null;

	private Area() {
		map_row_backend = MAP_ROW+1;
		map_col_backend = MAP_COL+1;
		area = new int[map_row_backend][map_col_backend];
		initAreaBorder();
	}
	
	public static Area getInstance() {
		if (map == null)
			map = new Area();

		return map;
	}

	private void initAreaBorder() {
		for (int row = 0; row < MAP_ROW+1; row++) {
			for (int col = 0; col < MAP_COL+1; col++) {
				if (col == 0 || col == MAP_COL)
					area[row][col] = 1;
				if (row == MAP_ROW)
					area[row][col] = 1;
			}
		}
	}
	
	public boolean detectedBorderCollision(int direction, Piece p, Console c) {
		int indexBorder;
		int xTmp, yTmp;

		switch (direction) {
		case Piece.DIR_RIGHT:
			indexBorder = Piece.getPieceBorderIndex(p, Piece.DIR_RIGHT);
			//	Position courante + le bord dans la direction + le deplacement dans la direction
			yTmp = p.yPos + indexBorder + 2;
			if (map.area[0][yTmp] == 1)
				return true;
			else
				return false;
		case Piece.DIR_BOTTOM:
			indexBorder = Piece.getPieceBorderIndex(p, Piece.DIR_BOTTOM);
			xTmp = p.xPos + indexBorder + 1;
//			c.putStringAt("indexBorder : " + String.valueOf(indexBorder), 0, 25);
//			c.putStringAt("p.xPos : " + String.valueOf(p.xPos), 1, 25);
//			c.putStringAt("xTmp : " + String.valueOf(xTmp), 2, 25);
			if (map.area[xTmp][1] == 1)
				return true;
			else
				return false;			
		case Piece.DIR_LEFT:
//			i = 0;
//			while(i != 4) {
//				for (int j = 0; j < 4; j++) {
//					if (p.pieceContent[j][i] == 1)
//						flag = true;
//				}
//				if (flag) return i;
//				i++;
//			}
			break;

		default:
			break;
		}
		
		return true;
	}
	
	public void printMapDbg(Console c, Piece p) {
		int fakeArea[][] = this.area.clone();

		for (int row = 0; row < MAP_ROW+1; row++) {
			for (int col = 0; col < MAP_COL+1; col++) {
				c.putStringAt(String.valueOf(fakeArea[row][col]), row, col + 48);
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				fakeArea[p.xPos+i][p.yPos+j] = p.pieceContent[i][j];
			}
		}
		
		for (int row = 0; row < MAP_ROW+1; row++) {
			for (int col = 0; col < MAP_COL+1; col++) {
				c.putStringAt(String.valueOf(fakeArea[row][col]), row, col + 26);
			}
		}
		// 1 0 0 0 0 0 0 0 0 0 0 0 0 1 douze 0
		// 1════════════1 douze 0
	}
}
