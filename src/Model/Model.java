package Model;
import Controller.Settings;

import Controller.Cell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * @author Ashley Irawan and Samantha Guanzon
 *
 */

public class Model {
	
	Board game;
	private Random random;
	
	//VIEW PURPOSES
	private static final int FRAME_THICKNESS = 16;
 	private static final int GRID_WIDTH = 4;
	private Cell[][] grid;
	
	public Settings themes;
	
	//SCORES
	private int highScore;
	private int highCell;

	private boolean arrowActive;

	/**
	 * Model Constructor: Creates the Board object, 
	 * a Cell grid and initialize it by filling it with empty Cell objects, 
	 * a Settings object with all the hex codes for themes, 
	 * reads scores, like high score and high cell, saved in savedScores.txt,
	 * and sets arrow active to false
	 */
	public Model(){
		game = new Board();
		this.grid = new Cell[GRID_WIDTH][GRID_WIDTH];
		this.random = new Random();
		initializeGrid();
		themes = new Settings();
		retrieveScores();
		this.arrowActive = false;
	}
	
	/**
	 * Resets everything similar to constructor except retrieving scores
	 */
	public void resetModel() {
		game = new Board();
		this.grid = new Cell[GRID_WIDTH][GRID_WIDTH];
		this.random = new Random();
		initializeGrid();
		themes = new Settings();
		this.arrowActive = false;
	}
	
	
	/**
	 * Sets the theme of the board
	 * @param choice An integer representing which theme in Settings class
	 */
	public void setTheme(int choice) {
		themes.setTheme(choice);
	}
	
	/**
	 * Gets the 2-Dimensional Cell array
	 * @return a 2-Dimensional array of Cell objects
	 */
	public Cell[][] getGrid(){
		return grid;
	}
	
	/**
	 * Gets the high score recorded by Model class
	 * @return An Integer representing the high score recorded by Model class
	 */
	public int getModelHighScore() {
		return highScore;
	}
	
	/**
	 * Gets the Score object from Board class
	 * @return a Score object representing all the scores recorded by Board class
	 */
	public int getScore() {
		return game.getGameScore().getScore();
	}
	
	/**
	 * Gets the highest value on the Board recorded by Model class
	 * @return An integer representing the highest value on the Board recorded by Model Class
	 */
	public int getHighCell() {
		return highCell;
	}
	
	
	/**
	 * Model Move methods: Up
	 * makes call to {@link Board#moveUp() Board.moveUp()} method
	 * makes call to {@link #updateCellGrid() updateCellGrid()} method to update Cell grid with the changes
	 * makes call to {@link #updateScores() updateScores()} method to record the new scores in Model 
	 */
	public void modelMoveUp(){
		game.moveUp();
		updateCellGrid();
		updateScores();
	}
	
	/**
	 * Model Move methods: Down
	 * makes call to {@link Board#moveDown() Board.moveDown()} method
	 * makes call to {@link #updateCellGrid() updateCellGrid()} method to update Cell grid with the changes
	 * makes call to {@link #updateScores() updateScores()} method to record the new scores in Model 
	 */
	public void modelMoveDown(){
		game.moveDown();
		updateCellGrid();
		updateScores();
	}
	
	/**
	 * Model Move methods: Left
	 * makes call to {@link Board#moveLeft() Board.moveLeft()} method
	 * makes call to {@link #updateCellGrid() updateCellGrid()} method to update Cell grid with the changes
	 * makes call to {@link #updateScores() updateScores()} method to record the new scores in Model 
	 */
	public void modelMoveLeft(){
		game.moveLeft();
		updateCellGrid();
		updateScores();
	}
	
	/**
	 * Model Move methods: Right
	 * makes call to {@link Board#moveRight() Board.moveRight()} method
	 * makes call to {@link #updateCellGrid() updateCellGrid()} method to update Cell grid with the changes
	 * makes call to {@link #updateScores() updateScores()} method to record the new scores in Model 
	 */
	public void modelMoveRight(){
		game.moveRight();
		updateCellGrid();
		updateScores();
	}
	

	
	/**
	 * Fills 2-Dimensional array of Cell objects with Cell objects of value 1
	 * sets each cell location by making calls to {@link Cell#setCellLocation(java.awt.Point) Cell.setCellLocation(int, int)} method
	 * making calls to {@link Cell#getCellWidth() Cell.getCellWidth()} to set cell location 
	 */
   	public void initializeGrid() {
 		int xx = FRAME_THICKNESS;
 		for (int x = 0; x < GRID_WIDTH; x++) {
 			int yy = FRAME_THICKNESS;
 			for (int y = 0; y < GRID_WIDTH; y++) {
 				Cell cell = new Cell();
 				cell.setCellLocation(xx, yy);
 				grid[x][y] = cell;
 				yy += FRAME_THICKNESS + Cell.getCellWidth();
 			}
 			xx += FRAME_THICKNESS + Cell.getCellWidth();
 		}
 	}

 // ADDS CELL AND NUMBER TILES BY RANDOM 	
   	/**
   	 * Uses Random class to find empty spaces on the board and picks a random space to put a new tile
   	 * calls {@link Cell#setValue(int) Cell.setValue(int)} to set the value of the new tile to 2,
   	 * and {@link Cell#setCellColor(Color) Cell.setCellColor(Color)} method and {@link Settings#getTheme() Settings.getTheme()} to get the current theme and color of the tile
   	 * calls {@link Board#populateTiles(int, int) Board.populateTiles(int, int)} method to insert Number object into target coordinates in Board object
   	 */
	 public void addNewCell(){
	    	int x_rand;
	    	int y_rand;
	    	if (game.getTotalTiles() == 16) return;
	    	do {
	    	x_rand = random.nextInt(4);
	    	y_rand = random.nextInt(4);
	    	}while (game.getTile(x_rand, y_rand)!= null && grid[x_rand][y_rand].getValue() != 0);
	    	grid[x_rand][y_rand].setValue(2);
	    	grid[x_rand][y_rand].setCellColor(Color.decode(themes.getTheme()[0]));
	    	game.populateTiles(x_rand, y_rand);
	 }
	 
	 
	 /**
	  * Draws the Cell objects according to the Cell grid object
	  * makes calls to {@link #getPreferredSize() getPreferredSize()} method for Dimension object
	  * makes calls to {@link Cell#drawCell(Graphics) Cell.drawCell(Graphics)} method to draw cell
	  * @param g A Graphics object
	  */
  	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		Dimension d = getPreferredSize();
		g.fillRect(0, 0, d.width, d.height);

		for (int x = 0; x < GRID_WIDTH; x++) {
			for (int y = 0; y < GRID_WIDTH; y++) {
				grid[x][y].drawCell(g);
			}
		}
  	}

  	/**
  	 * Calculates the view size of the Cell 
  	 * @return A Dimension object with the width and height of the Cell
  	 */
   public Dimension getPreferredSize() {
	   int width = GRID_WIDTH * Number.getCellWidth() +
				FRAME_THICKNESS * 5;
		return new Dimension(width, width);
	}


   /**
    * Checks if the board is full and if there are no more possible moves to be made
    * makes call to {@link Board#isBoardFull() Board.isBoardFull()} and {@link #isMovePossible() isMovePossible()} methods
    * @return True if game is over, False if can keep going
    */
   	public boolean isGameOver() {
		if (game.isBoardFull() && !isMovePossible()){
			return true;
		}else {
			return false;
		}
	}
   	
   	/**
   	 * Checks Number objects in the Board if there are any duplicate values next to each other that could result in a move
   	 * @return True if there is a move to be made, False if no moves are possible
   	 */
 	private boolean isMovePossible() {
		for (int x = 0; x < GRID_WIDTH; x++) {
			for (int y = 0; y < (GRID_WIDTH - 1); y++) {
				int yy = y + 1;
				if (game.getTile(x, y).getNumValue() == game.getTile(x, yy).getNumValue()) return true;
			}
		}
		for (int y = 0; y < GRID_WIDTH; y++) {
			for (int x = 0; x < (GRID_WIDTH - 1); x++) {
				int xx = x + 1;
				if (game.getTile(x, y).getNumValue() == game.getTile(xx, y).getNumValue()) return true;
			}
		}
		return false;
	}
 	
	
	
 	/** 
 	 * Updates the Cell grid according to the Board object
 	 * Uses {@link #getTileColor(int) getTileColor(int)} method to decode the color from Settings object according to current theme and value of tile
 	 * Uses {@link Cell#setValue(int) Cell.setValue(int)} and {@link Cell#setCellColor(Color) Cell.setCellColor(Color)} to update every Cell object's value and color
 	 */
	public void updateCellGrid() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (game.getTile(x, y) == null) {
					grid[x][y].setValue(0);
				}
				else {
					grid[x][y].setValue(game.getTile(x, y).getNumValue());
			    	grid[x][y].setCellColor(getTileColor(grid[x][y].getValue()));

				}
			}
		}
	}
	
	
	//FOR DEBUGGING PURPOSES
	/**
	 * Prints the Cell and Board into the console for debugging
	 */
	public void printCellGridandBoard() {
		System.out.println("CELL GRID");
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				System.out.print(" [" + grid[x][y].getValue() + "] ");
			}
			System.out.print("\n");
		}
		
		System.out.println("\nBOARD");
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (game.getTile(x, y) == null) System.out.print(" [ ] ");
				else System.out.print(" [" + game.getTile(x, y).getNumValue() + "] ");
			}
			System.out.print("\n");
		}
		System.out.print("\n________________________________\n");


	}
	

	/**
	 * Gets the status of arrow
	 * @return True if Active, False if Not Active
	 */
	public boolean ArrowActive(){
		return arrowActive;
	}

	/**
	 * Sets the status of arrow
	 * @param arrowActive A boolean value, true is Active, false is Inactive
	 */
	public void setArrowActive(boolean arrowActive){
		this.arrowActive = arrowActive;
	}

	/**
	 * Uses the value of the tile to get the color of the tile according to the current theme 
	 * makes calls to {@link Settings#getTheme() Settings.getTheme()} for the colors of the current theme
	 * @param value An integer value represeting the number value of the tile
	 * @return The Color object decoded from the color hex values retrieved from Settings object
	 */
	private Color getTileColor(int value) {
      Color color = Color.WHITE;
      switch (value) {
          case 2:     color = Color.decode(themes.getTheme()[0]);
              break;
          case 4:     color = Color.decode(themes.getTheme()[1]);
              break;
          case 8:     color = Color.decode(themes.getTheme()[2]);
              break;
          case 16:    color = Color.decode(themes.getTheme()[3]);
              break;
          case 32:    color = Color.decode(themes.getTheme()[4]);
              break;
          case 64:    color = Color.decode(themes.getTheme()[5]);
              break;
          case 128:   color = Color.decode(themes.getTheme()[6]);
              break;
          case 256:   color = Color.decode(themes.getTheme()[7]);
              break;
          case 512:   color = Color.decode(themes.getTheme()[8]);
              break;
          case 1024:  color = Color.decode(themes.getTheme()[9]);
              break;
          case 2048:  color = Color.decode(themes.getTheme()[10]);
              break;
          default:    color = new Color(43, 43, 0);
              break;
      }

      return color;
  }
	

	//SCORE METHOODS
	/**
	 * Records the high score and highest value of cell from Board class in Model class
	 * Uses {@link Board#getGameScore() Board.getGameScore()} to retrieve Score object from Board class
	 * Uses {@link Score#getHighScore() Score.getHighScore()} for high score
	 * Uses {@link Board#getHighCell() Board.getHighCell()} for highest value of cell from Board 
	 */
	public void updateScores() {
		if (game.getGameScore().getHighScore() > highScore) 
			highScore = game.getGameScore().getHighScore();
		if (game.getHighCell() > highCell) 
			highCell = game.getHighCell();
	}
	
	/**
	 * Saves high score and highest value of cell of the Board in savedScores.txt
	 */
	public void saveGame() {
		try {
		      FileWriter myWriter = new FileWriter("savedScores.txt");
		      myWriter.write(String.valueOf(highScore) + "\n" + String.valueOf(highCell));
		      myWriter.close();
		      System.out.println("Saved");
		    } 
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	/**
	 * Reads savedScores.txt for high score and highest value of cell from previous games
	 */
	public void retrieveScores() {
		 try {
	            //Open the file using FileReader Object.
	            FileReader file = new FileReader("savedScores.txt");
	            BufferedReader buff = new BufferedReader(file);
	            String line = buff.readLine();
	            highScore = Integer.parseInt(line);
	            line = buff.readLine();
	            highCell = Integer.parseInt(line);
	            buff.close();
	        }
	        catch (IOException e) {
	            System.out.println("Error -- " + e.toString());
	        }
	}

}

