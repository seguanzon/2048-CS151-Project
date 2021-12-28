package View;
import Controller.Cell;
import Model.Model;


import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Samantha Guanzon
 *
 */

/**
 * GridPanel constructs the display of the game grid of numbers
 */
public class GridPanel extends JPanel{
    private static final int FRAME_THICKNESS = 16;
    private static final int GRID_WIDTH = 4;

    private Model model;

    /**
     *
     * @param model creates new object of model
     * calls to setPreferredSize() for formatting
     */
    public GridPanel(Model model) {
        this.model = model;
        this.setPreferredSize(model.getPreferredSize());
    }

    /**
     * purpose: for easier formatting of each cell
     * formats each cell in the grid panel
     * @return Dimension
     */
    public Dimension getPreferredSize() {
        int width = GRID_WIDTH * Cell.getCellWidth() +
                FRAME_THICKNESS * 5;
        return new Dimension(width, width);
    }

    /**
     * draws the graphics on the panel
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        model.draw(g);
    }
}
