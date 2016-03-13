package fr.esiea.ga.tetris.client.Model;

public interface ConstantChar {
	// http://unicode-table.com/en/#control-character
	// https://en.wikipedia.org/wiki/Emoticons_(Unicode_block)
	// https://github.com/svenvc/ston/issues/11 print char like /uffff f
	
	public final String CORNER_TOP_LEFT 	= "\u2554"; // ╔
	public final String CORNER_TOP_RIGHT	= "\u2557"; // ╗
	public final String CORNER_BOTTOM_LEFT 	= "\u255A"; // ╚
	public final String CORNER_BOTTOM_RIGHT	= "\u255D"; // ╝
	public final String COLUMN				= "\u2551"; // ║
	public final String ROW					= "\u2550"; // ═
	public final String ROW_UNDER			= "\u2566"; // ╦
	public final String ROW_ABOVE			= "\u2569"; // ╩
	public final String COL_LEFT			= "\u2563"; // ╣
	public final String COL_RIGHT			= "\u2560"; // ╠
	public final String CROSS				= "\u256C"; // ╬
	public final String CHECK_MARK			= "\u2713"; // ✓
	public final String ARROW				= "\u27A4"; // ➤
}