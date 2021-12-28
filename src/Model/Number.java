package Model;
/**
 * 
 * @author Ashley Irawan
 *
 */
public class Number{
	private int numValue;
	private static final int CELL_WIDTH = 120;

	/**
	 * Initializes the Number object value to 2
	 */
	public Number(){
		numValue = 2;
	}
	
	/**
	 * Gets the view width of the cell
	 * @return An Integer representing the view width of the cell
	 */
	public static int getCellWidth() {
        return CELL_WIDTH;
    }
	
	/**
	 * Gets the value of the Number object
	 * @return An Integer representing the value
	 */
	public int getNumValue() {
		return numValue;
	}
	
	/**
	 * Doubles the value of the Number object
	 */
	public void updateNumber() { //set new NumValue
		numValue += numValue;
	}
	
	/**
	 * Checks if a Number tile's value is equal to this Number tile's value
	 * @param that The other Number object to be compared to
	 * @return True if equals, False if not
	 */
	public boolean equals(Number that) {
		return (that.numValue == this.numValue);
	}

}

