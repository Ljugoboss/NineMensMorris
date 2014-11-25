package Utils;
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
	private int turn;
	//Markers not on the playing field
	private int blackMarkers;
	private int whiteMarkers;
	
	private final int EMPTY_FIELD = 0;
	
	public Rules() {
		playingfield = new int[25];
		blackMarkers = 9;
		whiteMarkers = 9;
		turn = Constants.WHITE; //Random who will begin?
	}
	
	public boolean validMove(int from, int to) {
			
		if(turn == Constants.BLACK && blackMarkers > 0) {
			playingfield[to] = Constants.BLACK;
			blackMarkers--;
			turn = Constants.WHITE;
			return true;
		}
		if(turn == Constants.WHITE && whiteMarkers > 0) {
			playingfield[to] = Constants.WHITE;
			whiteMarkers--;
			turn = Constants.BLACK;
			return true;
		}
		
		if(playingfield[from] != turn) {
			return false;
		}
		
		if(!isValidMove(from, to)) {
			return false;
		}
		
		playingfield[to] = playingfield[from];
		playingfield[from] = EMPTY_FIELD;
		
		if(turn == Constants.WHITE) {
			turn = Constants.BLACK;
		} else {
			turn = Constants.WHITE;
		}
		
		return true;
	}
	
	private boolean isValidMove(int from, int to) {
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
	
	public boolean canRemove(int partOfLine) {
		if(playingfield[partOfLine] == EMPTY_FIELD) {
			return false;
		}
		
		if((partOfLine == 1 || partOfLine == 2 || partOfLine == 3) && (playingfield[1] == playingfield[2] && playingfield[2] == playingfield[3])) {
			return true;
		}
		if((partOfLine == 4 || partOfLine == 5 || partOfLine == 6) && (playingfield[4] == playingfield[5] && playingfield[5] == playingfield[6])) {
			return true;
		}
		if((partOfLine == 7 || partOfLine == 8 || partOfLine == 9) && (playingfield[7] == playingfield[8] && playingfield[8] == playingfield[9])) {
			return true;
		}
		if((partOfLine == 10 || partOfLine == 11 || partOfLine == 12) && (playingfield[10] == playingfield[11] && playingfield[11] == playingfield[12])) {
			return true;
		}
		if((partOfLine == 13 || partOfLine == 14 || partOfLine == 15) && (playingfield[13] == playingfield[14] && playingfield[14] == playingfield[15])) {
			return true;
		}
		if((partOfLine == 16 || partOfLine == 17 || partOfLine == 18) && (playingfield[16] == playingfield[17] && playingfield[17] == playingfield[18])) {
			return true;
		}
		if((partOfLine == 19 || partOfLine == 20 || partOfLine == 21) && (playingfield[19] == playingfield[20] && playingfield[20] == playingfield[21])) {
			return true;
		}
		if((partOfLine == 22 || partOfLine == 23 || partOfLine == 24) && (playingfield[22] == playingfield[23] && playingfield[23] == playingfield[24])) {
			return true;
		}
		if((partOfLine == 1 || partOfLine == 10 || partOfLine == 22) && (playingfield[1] == playingfield[10] && playingfield[10] == playingfield[22])) {
			return true;
		}
		if((partOfLine == 4 || partOfLine == 11 || partOfLine == 19) && (playingfield[4] == playingfield[11] && playingfield[11] == playingfield[19])) {
			return true;
		}
		if((partOfLine == 7 || partOfLine == 12 || partOfLine == 16) && (playingfield[7] == playingfield[12] && playingfield[12] == playingfield[16])) {
			return true;
		}
		if((partOfLine == 2 || partOfLine == 5 || partOfLine == 8) && (playingfield[2] == playingfield[5] && playingfield[5] == playingfield[8])) {
			return true;
		}
		if((partOfLine == 17 || partOfLine == 20 || partOfLine == 23) && (playingfield[17] == playingfield[20] && playingfield[20] == playingfield[23])) {
			return true;
		}
		if((partOfLine == 9 || partOfLine == 13 || partOfLine == 18) && (playingfield[9] == playingfield[13] && playingfield[13] == playingfield[18])) {
			return true;
		}
		if((partOfLine == 6 || partOfLine == 14 || partOfLine == 21) && (playingfield[6] == playingfield[14] && playingfield[14] == playingfield[21])) {
			return true;
		}
		if((partOfLine == 3 || partOfLine == 15 || partOfLine == 24) && (playingfield[3] == playingfield[15] && playingfield[15] == playingfield[24])) {
			return true;
		}
		return false;
	}
	
	public boolean remove(int from, int color) {
		if (playingfield[from] == color) {
			playingfield[from] = EMPTY_FIELD;
			return true;
		} else
			return false;
	}
	
	/**
	 * Check if color 'color' has won
	 * @param color 
	 * @return
	 */
	public boolean isItAWin(int color) {
		if(whiteMarkers > 0 || blackMarkers > 0) {
			return false;
		}
		int count = 0;
		for(int i : playingfield) {
			if(i == color) {
				count++;
			}
		}
		return (count < 3);
	}
	
	public int fieldColor(int field) {
		return playingfield[field];
	}
}
