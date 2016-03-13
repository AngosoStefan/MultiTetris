package fr.esiea.ga.tetris.client.Model;

public class Area {
	public int[][] area;

	public Area() {
		area = new int[15][14];
		initAreaBorder();
	}

	private void initAreaBorder() {
		for (int row = 0; row < 15; row++) {
			for (int col = 0; col < 14; col++) {
				if (col == 0 || col == 13)
					area[row][col] = 1;
				if (row == 14)
					area[row][col] = 1;
			}
		}
	}
}
