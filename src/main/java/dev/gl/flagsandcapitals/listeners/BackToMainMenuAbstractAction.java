package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.enums.MainWindowMode;
import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.utils.Configuration;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author gl
 */
public class BackToMainMenuAbstractAction extends AbstractAction {

    private MainWindow mw;

    public BackToMainMenuAbstractAction(MainWindow mw) {
        this.mw = mw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // blocks short cut if the player is not playng now
        if (mw.getMainWindowMode() == MainWindowMode.IDLE) {
            return;
        }

        if (!mw.isGameFinished()) {
            int answer = JOptionPane.showConfirmDialog(mw,
                    Configuration.getResourceBundle().getString("backToMainMenuDualogQuestion"),
                    Configuration.getResourceBundle().getString("title"),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (answer != 0) {
                return;
            }
            
            mw.getGameModel().lose(false);
        }
        
        mw.setMainWindowMode(MainWindowMode.IDLE);
    }
}
