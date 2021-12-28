package Model;
/**
 * 
 * @author Ashley
 * Part of Strategy Pattern
 *
 */
public class seekCellsRightRow implements seekCells{
	
	/*
     * eg. [2][4][ ][4] --> [ ][ ][2][8]
     */
	
	/**
	 * Finds the next coordinate that the tile in position (x, y) should go to when moved right
	 * @param x An integer representing the x-coordinate on the board
	 * @param y An integer representing the y-coordinate on the board
	 * @param boardData A 2-dimensional array of Number objects representing the board
	 * @return the next y-coordinate that the given tile should move to
	 */
	public int findNextAvailable(int x, int y, Number[][] boardData) {
    	if (y == 3) return y;
		if (boardData[x][y+1] != null && !boardData[x][y].equals(boardData[x][y+1])) return y;
    	int nextY = y;
    	for (int i = y + 1; i < 4; i++) {				//find the farthest possible empty cell in that row
    		if (boardData[x][i] == null) {
    			nextY = i;
    		}
    		else {
    			nextY = i - 1;
    			break;
    		}
    	}
    	if (nextY == 3) return nextY;					//if the cell is at the furthest end, return that
    	if (boardData[x][y].equals(boardData[x][nextY+1])) return nextY+1; //if the cell after the furthest available is a match, take that as next cell
    	else return nextY; //return if not at the end or not a match
    }

}
