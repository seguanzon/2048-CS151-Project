package Model;

/**
 * 
 * @author Ashley
 * Part of Strategy Pattern
 *
 */
public class seekCellsUpCol implements seekCells{
	
	 /*
     * eg. [2][8][ ][4] 		[2][8][ ][4]			[2][8][2][4]
     * 	   		^			-->					-->		[4][4][ ][8]
     * 	   [2][ ][ ][ ]			[4][4][2][8]			[ ][ ][ ][ ]
     * 	   [2][4][2][8]			[ ][ ][ ][ ]			[ ][ ][ ][ ]
     */
	
	
	/**
	 * Finds the next coordinate that the tile in position (x, y) should go to when moved up
	 * @param x An integer representing the x-coordinate on the board
	 * @param y An integer representing the y-coordinate on the board
	 * @param boardData A 2-dimensional array of Number objects representing the board
	 * @return the next x-coordinate that the given tile should move to
	 */
	@Override
	public int findNextAvailable(int x, int y, Number[][] boardData) {
		if (x == 3) return x;
		if (boardData[x+1][y] != null && !boardData[x][y].equals(boardData[x+1][y])) return x;
    	int nextX = x;
    	for (int i = x + 1; i < 4; i++) {				//find the farthest possible empty cell in that row
    		if (boardData[i][y] == null) nextX = i;
    		else {
    			nextX = i-1;
    			break;
    		}
    	}
    	if (nextX == 3) return nextX;					//if the cell is at the farthest end, return that
    	else if (boardData[x][y].equals(boardData[nextX+1][y])) return nextX+1; //if the cell after the furthest available is a match, take that as next cell
    	else return nextX; //return if not at the end or not a match
	}
}
