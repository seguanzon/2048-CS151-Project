package Controller;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Samantha Guanzon
 *
 */


/**
 * Manages the attributes of each individual cell on the grid
 */
public class Cell{
    private static final int CELL_WIDTH = 120;
    private static final int FRAME_THICKNESS = 16;
    private static final int GRID_WIDTH = 4;

    private int value;
    private Point cellLocation;
    public Color cellColor;

    public Cell() {
    }

    /**
     *
     * @return width of cell
     */
    public static int getCellWidth() {
        return CELL_WIDTH;
    }

    /**
     *
     * @return value of cell
     */
    public int getValue() {
        return value;
    }

    /**
     * sets the cell's value
     * @param value cell value, ie number inside cell
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * checks if cell's value is zero
     * @return boolean result (true/false)
     */
    public boolean isZeroValue() {
        return (value == 0);
    }

    /**
     *
     * @param c Color c, the cell's color
     */
    public void setCellColor(Color c) {
    	cellColor = c;
    }

    /**
     *
     * @param x cell's x-coordinate
     * @param y cell's y-coordinate
     */
    public void setCellLocation(int x, int y) {
        setCellLocation(new Point(x, y));
    }

    /**
     *
     * @param cellLocation cell's location as a Point
     */
    public void setCellLocation(Point cellLocation) {
        this.cellLocation = cellLocation;
    }

    /**
     * formats each cell; font, centering, etc
     * if a cell is empty, its color is set to Gray
     * if a cell is not empty, its font is made bigger so the number is readable
     * calls drawImage() to draw the cell with these attributes
     */
    public void drawCell(Graphics g) {
        if (value == 0) {
            g.setColor(Color.GRAY);
            g.fillRect(cellLocation.x, cellLocation.y, CELL_WIDTH, CELL_WIDTH);
        } else {
            Font font = g.getFont();
            FontRenderContext frc = new FontRenderContext(null, true, true);

            String s = Integer.toString(value);
            BufferedImage image = createImage(font, frc, CELL_WIDTH, s);

            g.drawImage(image, cellLocation.x, cellLocation.y, null);
        }
    }

    /**
     * for formatting the dimension of the cell
     * @return Dimension of cell
     */
    public Dimension getPreferredSize() {
        int width = GRID_WIDTH * Cell.getCellWidth() + FRAME_THICKNESS * 5;
        return new Dimension(width, width);
    }

    /**
     * creates the view components of each cell
     * @param font renders the text of the cell's numeric value and styles it
     * @param frc font render context formats the text inside the cell (centers it)
     * @param width width of cell
     * @param s String value of cell's numeric value
     * @return cell's image using these components
     */
    private BufferedImage createImage(Font font, FontRenderContext frc, int width, String s) {
        Font largeFont = font.deriveFont((float) (width / 4));
        Rectangle2D r = largeFont.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r.getWidth());
        int rHeight = (int) Math.round(r.getHeight());
        int rX = (int) Math.round(r.getX());
        int rY = (int) Math.round(r.getY());

        BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);

        Graphics gg = image.getGraphics();
        gg.setColor(cellColor);
        gg.fillRect(0, 0, image.getWidth(), image.getHeight());

        int x = (width / 2) - (rWidth / 2) - rX;
        int y = (width / 2) - (rHeight / 2) - rY;

        gg.setFont(largeFont);
        gg.setColor(getTextColor());
        gg.drawString(s, x, y);
        gg.dispose();
        return image;
    }

    /**
     * if a cell's value is equal to or above 256, the text color changes to white
     * meant to ensure readability for the darker cells as the value increases
     * @return color of text within cell
     */
    private Color getTextColor() {
        return (value >= 256) ? Color.WHITE : Color.BLACK;
    }
}