package View;

import Controller.DownAction;
import Controller.LeftAction;
import Controller.RightAction;
import Controller.UpAction;
import Model.Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 * @author Samantha Guanzon
 *
 */

/**
 * GameFrame constructs the display of the entire game window
 * includes: ControlPanel, GridPanel, and ScorePanel
 */
public class GameFrame extends JPanel {

    private ControlPanel controlPanel;
    private Model model;
    private GridPanel gridPanel;
    private JFrame frame;
    private ScorePanel scorePanel;

    /**
     * GameFrame Constructor makes new object of Model
     * Calls createPartControl() to create new objects of the three other panels
     * @param model
     */
    public GameFrame(Model model) {
        this.model = model;
        createPartControl();
    }

    /**
     * Creates new objects of: GridPanel, ScorePanel, and ControlPanel
     * Creates JFrame containing all 3 panels
     */
    private void createPartControl() {
        gridPanel = new GridPanel(model);
        scorePanel = new ScorePanel(model);
        controlPanel = new ControlPanel(this, model);

        frame = new JFrame();
        frame.setTitle("2048");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });

        setKeyBindings();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        mainPanel.add(createSidePanel());
        mainPanel.add(gridPanel);

        frame.add(mainPanel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates the left sidebar
     * Adds scorePanel and controlPanel to the left sidebar
     * This also implies that gridPanel will be added to the right
     * @return sidePanel which contains the
     */
    private JPanel createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel,
                BoxLayout.PAGE_AXIS));
        sidePanel.add(scorePanel.getPanel());
        sidePanel.add(Box.createVerticalStrut(30));
        sidePanel.add(controlPanel.getPanel());
        return sidePanel;
    }

    /**
     * Creates key bindings for each arrows
     * Allows the user to operate the game using the WASD keys or the arrow keys on their keyboard
     */
    private void setKeyBindings() {
        InputMap inputMap =
                gridPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("W"), "up arrow");
        inputMap.put(KeyStroke.getKeyStroke("S"), "down arrow");
        inputMap.put(KeyStroke.getKeyStroke("A"), "left arrow");
        inputMap.put(KeyStroke.getKeyStroke("D"), "right arrow");

        inputMap.put(KeyStroke.getKeyStroke("UP"), "up arrow");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down arrow");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left arrow");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right arrow");

        gridPanel.getActionMap().put("up arrow",
                new UpAction(this, model));
        gridPanel.getActionMap().put("down arrow",
                new DownAction(this, model));
        gridPanel.getActionMap().put("left arrow",
                new LeftAction(this, model));
        gridPanel.getActionMap().put("right arrow",
                new RightAction(this, model));
    }

    /**
     * saves the game attributes by calling saveGame()
     * clears JFrame and exits
     */
    public void exitProcedure() {
    	model.saveGame();
        frame.dispose();
        System.exit(0);
    }

    /**
     * repaints gridPanel -- udpates the view
     */
    public void repaintGridPanel() {
        gridPanel.repaint();
    }

    /**
     * updates the score in scorePanel
     * calls updatePartControl() in ScorePanel class
     */
    public void updateScorePanel() {
        scorePanel.updatePartControl();
    }


} //end GameFrame class
