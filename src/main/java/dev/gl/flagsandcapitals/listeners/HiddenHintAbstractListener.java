package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.gui.GameBoardPanel;
import dev.gl.flagsandcapitals.models.GameModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author gl
 */
public class HiddenHintAbstractListener extends AbstractAction {
    
    private GameBoardPanel parent;
    private GameModel gameModel;

    public HiddenHintAbstractListener(GameBoardPanel parent, GameModel gameModel) {
        this.parent = parent;
        this.gameModel = gameModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parent.showHiddenHint();
    }
    
}
