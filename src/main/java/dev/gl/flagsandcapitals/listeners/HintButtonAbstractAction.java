package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.db.entities.DbGeography;
import dev.gl.flagsandcapitals.gui.GameBoardPanel;
import dev.gl.flagsandcapitals.models.GameModel;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;

/**
 *
 * @author gl
 */
public class HintButtonAbstractAction extends AbstractAction {
    
    private static final Logger LOGGER = Logging.getLocalLogger(HintButtonAbstractAction.class);
    private final GameBoardPanel gameBoardPanel;
    private final GameModel gameModel;

    public HintButtonAbstractAction(GameBoardPanel gameBoardPanel, GameModel gameModel) {
        this.gameBoardPanel = gameBoardPanel;
        this.gameModel = gameModel;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (!gameBoardPanel.isHintButtonEnabled()) {
            return;
        }
        
        DbGeography answer = gameModel.useHint();
        if (answer == null) {
            LOGGER.log(Level.FINE, "Player has no hints at the moment");
            return;
        }
        
        gameBoardPanel.setAnswer(answer);
        gameBoardPanel.setHintsNumber(gameModel.getHints());
    }

}
