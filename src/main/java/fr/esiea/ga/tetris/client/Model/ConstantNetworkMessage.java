package fr.esiea.ga.tetris.client.Model;

public interface ConstantNetworkMessage {
	public static final int START = 0;
	public static final int STOP = 1;
		
	// actionCode
	public static final int PIECETYPE = 2;
	
	// subActionCode
	public static final int BLOCK1 = 1;
	public static final int BLOCK2 = 2;
	public static final int BLOCK3 = 3;
	public static final int BLOCK4 = 4;
	public static final int BLOCK5 = 5;
	public static final int BLOCK6 = 6;
	public static final int BLOCK7 = 7;
	
	// actionCode
	public static final int Malus = 3;
	
	// subActionCode
	public static final int AccelerateTime = 1;
	public static final int AddBottomRow = 2;
	
	// actionCode
	public static final int POSITION_X = 4;
	public static final int POSITION_Y = 5;
	
	// subActionCode mais il sera dynamique du coup X_POS / Y_POS = ?;
}
