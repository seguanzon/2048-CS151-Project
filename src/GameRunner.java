import View.GameFrame;
import Model.Model;
import javax.swing.*;

/**
 * Main class which is responsible for running the 2048 game.
 */
public class GameRunner implements Runnable {

    @Override
    /**
     * Method which instantiates the entire 2048 game.
     */
    public void run() {
    	Model game = new Model();
        new GameFrame(game);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GameRunner());
    }
}
