package Model;

/**
 * 
 * @author Ashley
 * Part of Strategy Pattern
 */
public class SeekCellsContext {
	private seekCells methods;
	
	/**
	 * Sets the SeekCellsContext to accept any method that implements seekCells interface
	 * @param methods The object of the class that implements seekCells interface
	 */
	public SeekCellsContext(seekCells methods) {
		this.methods = methods;
	}
	
	/**
	 * Sets up the method of the class passed into seekCellsContext class
	 * @param x An integer representing the x-coordinate of the board
	 * @param y An integer representing the y-coordinate of the board
	 * @param board A 2-dimensional array of Number objects representing the board
	 * @return the method from the class passed into seekCellsContext class.
	 */
	public int executeMethod(int x, int y, Number[][] board) {
		return methods.findNextAvailable(x, y, board);
	}
}
