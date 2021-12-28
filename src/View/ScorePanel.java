package View;
import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * 
 * @author Samantha Guanzon
 *
 */

/**
 * ScorePanel constructs the display containing score attributes
 * includes: High Score, High Cell, Current Score
 */
public class ScorePanel {
    //for formatting
    private static final Insets regularInsets  =  new Insets(10, 10, 0, 10);
    private static final Insets spaceInsets =  new Insets(10, 10, 10, 10);

    private static final NumberFormat nf = NumberFormat.getInstance();
    private Model model;
    private JPanel panel;

    private JTextField highScoreField;
    private JTextField highCellField;
    private JTextField currentScoreField;

    /**
     * creates new model and calls createPartControl() and updatePartControl()
     * @param model object of Model
     */
    public ScorePanel(Model model) {
        this.model = model;
        createPartControl();
        updatePartControl();
    }

    /**
     * creates new JPanel for the scorePanel
     * creates JLabels and JTextFields for the following attributes:
     * ... high score, high cell, and current score
     * calls addComponent for each JLabel and JTextField to format the components
     */
    private void createPartControl() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        //for vertical formatting of JLabels
        int gridy = 0;

        // HIGH SCORE:
        JLabel highScoreLabel = new JLabel("High Score:");
        addComponent(panel, highScoreLabel, 0, gridy, 1, 1,  regularInsets, GridBagConstraints.LINE_START,  GridBagConstraints.HORIZONTAL);

        highScoreField = new JTextField(6);
        highScoreField.setEditable(false);
        highScoreField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, highScoreField, 1, gridy++, 1, 1, regularInsets, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        // HIGH CELL:
        JLabel highCellLabel = new JLabel("High Cell:");
        addComponent(panel, highCellLabel, 0, gridy, 1, 1,  spaceInsets, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        highCellField = new JTextField(6);
        highCellField.setEditable(false);
        highCellField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, highCellField, 1, gridy++, 1, 1, spaceInsets, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        // CURRENT SCORE:
        JLabel currentScoreLabel = new JLabel("Current Score:");
        addComponent(panel, currentScoreLabel, 0, gridy, 1, 1, regularInsets, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        currentScoreField = new JTextField(6);
        currentScoreField.setEditable(false);
        currentScoreField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, currentScoreField, 1, gridy++, 1, 1, regularInsets, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
    }

    /**
     *
     * @param container the container which holds the buttons, ie the JPanel
     * @param component the component which is being added to the container, ie the JLabel or JTextField
     * @param gridx x-coordinate where the component is being placed
     * @param gridy y-coordinate where the component is being placed
     * @param gridwidth width of the panel
     * @param gridheight height of the panel
     * @param insets borders surrounding the components
     * @param anchor determines the position of the buttons within the panel
     * @param fill determines the resizing of the buttons within the panel
     */
    private void addComponent(Container container, Component component,
                              int gridx, int gridy, int gridwidth, int gridheight,
                              Insets insets, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
                gridwidth, gridheight, 1.0D, 1.0D, anchor, fill,
                insets, 0, 0);
        container.add(component, gbc);
    }

    /**
     * updates each field as each move happens
     */
    public void updatePartControl() {
         highScoreField.setText(nf.format(model.getModelHighScore()));
         highCellField.setText(nf.format(model.getHighCell()));
         currentScoreField.setText(nf.format(model.getScore()));
    }

    /**
     *
     * @return JPanel containing the three JLabels and three JTextFields
     */
    public JPanel getPanel() {
        return panel;
    }

}
