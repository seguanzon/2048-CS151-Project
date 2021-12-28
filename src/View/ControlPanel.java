package View;

import Controller.*;
import Controller.StartGameActionListener;
import Controller.Theme1Listener;
import Model.Model;
import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author Samantha Guanzon
 *
 */

/**
 * ControlPanel constructs the display of buttons: START GAME, CLASSIC THEME, SKY THEME, FOREST THEME
 */
public class ControlPanel {
    private static final Insets regularInsets = new Insets(10, 10, 0, 10); //sets padding
    private GameFrame frame;
    private Model model;
    private JPanel panel;

    /**
     * @param frame creates an object of GameFrame
     * @param model creates an object of Model
     * Calls createPartControl() to create new objects of the buttons' action listeners
     */
    public ControlPanel(GameFrame frame, Model model) {
        this.frame = frame;
        this.model = model;
        createPartControl();
    }

    /**
     * Creates the four JButtons: startGameButton, theme1Button, theme2Button, and theme3Button
     * Adds respective action listeners to these JButtons
     * Creates the JPanel where these buttons will be
     * Calls to addComponent() method which further formats the JPanel of JButtons
     */
    private void createPartControl() {
        StartGameActionListener listener = new StartGameActionListener(frame, model);
        Theme1Listener theme1Listener = new Theme1Listener(frame, model);
        Theme2Listener theme2Listener = new Theme2Listener(frame, model);
        Theme3Listener theme3Listener = new Theme3Listener(frame, model);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        int gridY = 0;  //for formatting y-coord of the buttons in the panel

        JButton startGameButton = new JButton("START GAME");
        startGameButton.addActionListener(listener);
        addComponent(panel, startGameButton, 0, gridY++, 1, 1, regularInsets, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        JButton theme1Button = new JButton("CLASSIC THEME");
        theme1Button.addActionListener(theme1Listener);
        addComponent(panel, theme1Button, 0, gridY++, 1, 1, regularInsets, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        JButton theme2Button = new JButton("SKY THEME");
        theme2Button.addActionListener(theme2Listener);
        addComponent(panel, theme2Button, 0, gridY++, 1, 1, regularInsets, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        JButton theme3Button = new JButton("FOREST THEME");
        theme3Button.addActionListener(theme3Listener);
        addComponent(panel, theme3Button, 0, gridY++, 1, 1, regularInsets, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
    }

    /**
     *
     * @param container the container which holds the buttons, ie the JPanel
     * @param component the component which is being added to the container, ie the JButton
     * @param gridX x-coordinate where the button is being placed
     * @param gridY y-coordinate where the button is being placed
     * @param gridWidth width of the panel
     * @param gridHeight height of the panel
     * @param insets borders surrounding the buttons
     * @param anchor determines the position of the buttons within the panel, ie LINE_START makes them flush to the left
     * @param fill determines the resizing of the buttons within the panel, ie HORIZONTAL sets the buttons to 100% the width of the panel
     */
    private void addComponent(Container container, Component component, int gridX, int gridY, int gridWidth, int gridHeight,
            Insets insets, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridX, gridY, gridWidth, gridHeight, 1, 1, anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }

    /**
     *
     * @return JPanel containing the four buttons
     */
    public JPanel getPanel() {return panel;}
}
