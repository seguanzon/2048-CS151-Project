package Model;

/**
 * 
 * @author Ashley Irawan
 * 
 *
 */
public class Board{
	/**
	 * 2Dimensional array of Number tiles representing the board grid
	 */
    private Number [][]boardData = new Number[4][4];
    
    
    /**
     * Represents all the scores relating to the game
     */
    private Score gameScore;
    
    private int totalTiles;
    private int highCell;
    

    /**
     * Creates a Board with score and total tiles on the board initialized to 0
     */
    public Board() {
		gameScore = new Score();
		totalTiles = 0;
    }
    
    /**
     * Inserts Number object into the board at (x, y) coordinate
     * @param x The x-coordinate on the board
     * @param y The y-coordinate on the board
     */
    public void populateTiles(int x, int y) {
    	boardData[x][y] = new Number();
    	totalTiles++;
    }
    
    
    /**
     * Gets the Number object stored in (x, y) coordinate on the board
     * @param x The x-coordinate on the board
     * @param y The y-coordinate on the board
     * @return A Number object
     */
    public Number getTile(int x, int y) {
    	return boardData[x][y];
    }
    
    /**
     * Gets the Score object of the class
     * @return A Score object
     */
    public Score getGameScore() {
    	return gameScore;
    }
    
    
    /**
     * Gets the highest value among all the Number tiles on the board
     * @return An integer representing the highest value on the board
     */
    public int getHighCell() {
    	return highCell;
    }
    
    /**
     * Sets the highest value on the board
     * @param hc An integer representing the highest value on the board
     */
    public void setHighCell(int hc) {
    	highCell = hc;
    }
    
    
    /**
     * Gets the number of tiles currently on the board
     * @return An integer representing the number of tiles on the board
     */
    public int getTotalTiles() {
    	return totalTiles;
    }
    
    /**
     * Checks the number of tiles on the board 
     * @return true if board is full and false if board is not
     */
    public boolean isBoardFull()
    {
    	return (totalTiles >= 16);
    }
    
    /**
     * Add two tiles together and makes updates using the Integer value from {@link Number#getNumValue() Number.getNumValue()}:
     * Makes call to {@link Number#updateNumber() updateNumber()} to update the value of the tile, 
     * Makes call to {@link Score#updateScore(int) updateScore(int)} to update score of the game, 
     * updates the highest value on the board (if applicable),
     * and updates the total tile count.
     * @param tile The Number object where the value will be used to do the calculation
     */
    public void combineMatch(Number tile) {
    	tile.updateNumber();
		gameScore.updateScore(tile.getNumValue());
		if (highCell < tile.getNumValue()) highCell = tile.getNumValue();
    	totalTiles--;
    }
    

   /**
    * Move Method: Down
    * Moves any applicable tiles on the board down
    * Uses Strategy Pattern: seekCells interface is implemented by seekCellsDownCol class using SeekCellsContext class
    * Makes calls to respective {@link seekCellsDownCol#findNextAvailable(int, int, Number[][]) findNextAvailable} methods to find the next available coordinate
    * Checks if the next coordinate passed back by findNextAvailable is:
    * an empty tile (moves number tile to new coordinate), 
    * a matched tile (moves number tile to new coordinate and calls combineMatch(Number) to update value), 
    * or the same coordinate (does nothing)
    * if moved, changes previously occupied cell to null
    */
    public void moveDown() {
		SeekCellsContext context = new SeekCellsContext(new seekCellsRightRow());
		for (int j = 3; j >= 0; j--) {						//from left to right
			int next;
			for (int i = 0; i < 4; i++) {					//top to bottom
				if (boardData[i][j] == null) continue;
				next = context.executeMethod(i, j, boardData);
				if (j == next) continue; 					//does not move tile
				else if (boardData[i][next] == null) {		//moves to empty cell
					boardData[i][next] = boardData[i][j];
				}
				else{										//moves to matched tile
					boardData[i][next] = boardData[i][j];
					combineMatch(boardData[i][next]);
				}
				boardData[i][j] = null;
			}
		}
    }
    
    /**
     * Move Method: Up
     * Moves any applicable tiles on the board up
     * Uses Strategy Pattern: seekCells interface is implemented by seekCellsUpCol class using SeekCellsContext class
     * Makes calls to respective {@link seekCellsUpCol#findNextAvailable(int, int, Number[][]) findNextAvailable} methods to find the next available coordinate
     * Checks if the next coordinate passed back by findNextAvailable is:
     * an empty tile (moves number tile to new coordinate), 
     * a matched tile (moves number tile to new coordinate and calls combineMatch(Number) to update value), 
     * or the same coordinate (does nothing)
     * if moved, changes previously occupied cell to null     
     */
    public void moveUp() {
		SeekCellsContext context = new SeekCellsContext(new seekCellsLeftRow());
		for (int j = 0; j < 4; j++) {						//from right to left
			int next;
			for (int i = 0; i < 4; i++) {					//top to bottom
				if (boardData[i][j] == null) continue;
				next = context.executeMethod(i, j, boardData);;
				if (j == next) continue; 					//does not move tile
				else if (boardData[i][next] == null) {		//move to empty cell
					boardData[i][next] = boardData[i][j];
				}
				else{										//move to matched tile
					boardData[i][next] = boardData[i][j];
					combineMatch(boardData[i][next]);
				}
				boardData[i][j] = null;
			}
		}
    }
    
    /**
     * Move Method: Right
     * Moves any applicable tiles on the board right
     * Uses Strategy Pattern: seekCells interface is implemented by seekCellsRightRow class using SeekCellsContext class
     * Makes calls to respective {@link seekCellsRightRow#findNextAvailable(int, int, Number[][]) findNextAvailable} methods to find the next available coordinate
     * Checks if the next coordinate passed back by findNextAvailable is:
     * an empty tile (moves number tile to new coordinate), 
     * a matched tile (moves number tile to new coordinate and calls combineMatch(Number) to update value), 
     * or the same coordinate (does nothing)
     * if moved, changes previously occupied cell to null          
     */
    public void moveRight() {

		SeekCellsContext context = new SeekCellsContext(new seekCellsUpCol());
		for (int i = 3; i >= 0; i--) {						//from bottom up
			int next;
			for (int j = 0; j < 4; j++) {					//left to right
				if (boardData[i][j] == null) continue;
				next = context.executeMethod(i, j, boardData);

				if (i == next) continue; 					//does not move tile
				else if (boardData[next][j] == null) {		//moves to empty cell
					boardData[next][j] = boardData[i][j];
				}
				else{										//moves to matched tile
					boardData[next][j] = boardData[i][j];
					combineMatch(boardData[next][j]);
				}
				boardData[i][j] = null;
			}
		}
    }
    
    /**
     * Move Method: Left
     * Moves any applicable tiles on the board left
     * Uses Strategy Pattern: seekCells interface is implemented by seekCellsLeftRow class using SeekCellsContext class
     * Makes calls to respective {@link seekCellsLeftRow#findNextAvailable(int, int, Number[][]) findNextAvailable} methods to find the next available coordinate
     * Checks if the next coordinate passed back by findNextAvailable is:
     * an empty tile (moves number tile to new coordinate), 
     * a matched tile (moves number tile to new coordinate and calls combineMatch(Number) to update value), 
     * or the same coordinate (does nothing)
     * if moved, changes previously occupied cell to null          
     */
    public void moveLeft() {
    	SeekCellsContext context = new SeekCellsContext(new seekCellsDownCol());
		for (int i = 0; i < 4; i++) {						//from up to down
			int next;
			for (int j = 0; j < 4; j++) {					//left to right
				if (boardData[i][j] == null) continue;
				next = context.executeMethod(i, j, boardData);

				if (i == next) continue; 					//does not move tile
				else if (boardData[next][j] == null) {		//moves to empty cell
					boardData[next][j] = boardData[i][j];
				}
				else{										//moves to matched tile
					boardData[next][j] = boardData[i][j];
					combineMatch(boardData[next][j]);
				}
				boardData[i][j] = null;
			}
		}
    }

}
