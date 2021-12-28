package Controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import Model.Model;
import View.GameFrame;

/**
 * 
 * @author Atsuya Yano
 *
 */
public class UpAction extends AbstractAction {

    private GameFrame frame;
    private Model model;

    /**
     * @param frame object of View
     * @param model object of Model
     */
    public UpAction(GameFrame frame, Model model){
        this.frame = frame;
        this.model = model;
    }

    /**
     * Method runs when user press up arrow key
     * @param event for key action
     */
    public void actionPerformed(ActionEvent event){
        if(model.ArrowActive()){
            if (model.isGameOver() == true){
                model.setArrowActive(false);
                System.out.println("GAME OVER");
            }else {
                model.modelMoveUp();

                frame.updateScorePanel();
                frame.repaintGridPanel();

                model.addNewCell();
                frame.repaintGridPanel();
            }
        }

    }
}
