/*
 * The game board positions
 *
 * 01           02           03
 *     04       05       06
 *         07   08   09
 * 10  11  12        13  14  15
 *         16   17   18
 *     19       20       21
 * 22           23           24
 * 
 */

public class Rules {
	private int[] playingfield;
	private int blackMarkers;
	private int whiteMarkers;
	
	private final int EMPTY_FIELD = 0;
	private final int BLACK_CHECKER = 1;
	private final int WHITE_CHECKER = 2;
	
	private boolean whitesTurn = true; //true if white's turn, false if black's turn
	
	public Rules() {
		playingfield = new int[25];
		blackMarkers = 9;
		whiteMarkers = 9;
	}

	/**
	 * Check whether this is a legal move.
	 */
	private boolean isValidMove(int to, int from) {
		
		if(playingfield[to] != EMPTY_FIELD)  {
			return false;
		}
		
		switch (to) {
		case 1:
			return (from == 10 || from == 2);
		case 2:
			return (from == 1 || from == 3 || from == 5 );
		case 3:
			return (from == 2 || from == 15);
		case 4:
			return (from == 5 || from == 11);
		case 5:
			return (from == 2 || from == 4 || from == 8 || from == 6);
		case 6:
			return (from == 5 || from == 14);
		case 7:
			return (from == 8 || from == 12);
		case 8:
			return (from == 5 || from == 7 || from == 9);
		case 9:
			return (from == 8 || from == 13);
		case 10:
			return (from == 1 || from == 11 || from == 22);
		case 11:
			return (from == 4 || from == 10 || from == 12 || from == 19);
		case 12:
			return (from == 7 || from == 11 || from == 16);
		case 13:
			return (from == 9 || from == 14 || from == 18);
		case 14:
			return (from == 6 || from == 13 || from == 15 || from == 21);
		case 15:
			return (from == 3 || from == 14 || from == 24);
		case 16:
			return (from == 12 || from == 17);
		case 17:
			return (from == 16 || from == 18 || from == 20);
		case 18:
			return (from == 13 || from == 17);
		case 19:
			return (from == 11 || from == 20);
		case 20:
			return (from == 17 || from == 19 || from == 21 || from == 23);
		case 21:
			return (from == 14 || from == 20);
		case 22:
			return (from == 10 || from == 23);
		case 23:
			return (from == 20 || from == 22 || from == 24);
		case 24:
			return (from == 15 || from == 23);
		}
		return false;
	}
}
