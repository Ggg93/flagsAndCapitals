package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.gui.GameBoardPanel;
import dev.gl.flagsandcapitals.models.GameModel;
import dev.gl.flagsandcapitals.utils.Configuration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 *
 * @author gl
 */
public class AnswerButtonActionListener implements ActionListener {
    
    private final GameBoardPanel parent;
    private final GameModel gameModel;

    public AnswerButtonActionListener(GameBoardPanel parent, GameModel gameModel) {
        this.parent = parent;
        this.gameModel = gameModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (!parent.isAnswerButtonEnabled()) {
            return;
        }
        
        ResourceBundle rb = Configuration.getResourceBundle();
        String answerButtonText = parent.getAnswerButtonText();
        if (answerButtonText.equalsIgnoreCase(rb.getString("answerButtonOptionAnswer"))) {
//            if (!parent.areAllLettersFilled()) {
//                parent.showAnswerNotReadyMessage();
//            }
            parent.setAnswerButtonFocused();
            gameModel.checkAnswer();
        } else {
            gameModel.setNextQuestion();
        }
    }
    
}
