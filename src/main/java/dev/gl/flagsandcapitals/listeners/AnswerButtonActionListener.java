package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.gui.GameBoardPanel;
import dev.gl.flagsandcapitals.models.GameModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }
    
}
