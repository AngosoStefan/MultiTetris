package fr.esiea.ga.tetris.client.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PieceType implements ConstantBlock {
	// http://stackoverflow.com/questions/1972392/
	// java-pick-a-random-value-from-an-enum
	PIECE1(BLOCK1, new int[][] { { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }), 
	PIECE2(BLOCK2, new int[][] { { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } }), 
	PIECE3(BLOCK3, new int[][] { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } }), 
	PIECE4(BLOCK4, new int[][] { { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }), 
	PIECE5(BLOCK5, new int[][] { { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }), 
	PIECE6(BLOCK6, new int[][] { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }), 
	PIECE7(BLOCK7, new int[][] { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } });

	public String blockID;
	public int[][] blockContent;

	private PieceType(String blockID, int[][] blockContent) {
		this.blockID = blockID;
		this.blockContent = blockContent;
	}
	
	public int getBlockId() {
		if (this.blockID.equals(BLOCK1))
			return 1;
		if (this.blockID.equals(BLOCK2))
			return 2;
		if (this.blockID.equals(BLOCK3))
			return 3;
		if (this.blockID.equals(BLOCK4))
			return 4;
		if (this.blockID.equals(BLOCK5))
			return 5;
		if (this.blockID.equals(BLOCK6))
			return 6;
		if (this.blockID.equals(BLOCK7))
			return 7;
		return 0;
	}

	private static final List<PieceType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static PieceType randomPiece() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

	@Override
	public String toString() {
		return this.blockID;
	}
}
