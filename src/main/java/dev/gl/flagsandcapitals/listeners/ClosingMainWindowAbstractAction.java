package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.enums.MainWindowMode;
import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author gl
 */
public class ClosingMainWindowAbstractAction extends AbstractAction {

    private static Logger logger = Logging.getLocalLogger(HyperConnection.class);
    private MainWindow parent;

    public ClosingMainWindowAbstractAction(MainWindow parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // blocks short cut if the player is not playng now
            if (parent.getMainWindowMode() == MainWindowMode.PLAYING) {
                int answer = JOptionPane.showConfirmDialog(parent,
                        Configuration.getResourceBundle().getString("backToMainMenuDualogQuestion"),
                        Configuration.getResourceBundle().getString("title"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (answer != 0) {
                    return;
                }

                parent.getGameModel().lose(false);
            }
            
            HyperConnection con = HyperConnection.getInstance();
            con.closeConnection();

            parent.dispose();
            System.exit(0);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
        }

    }

}
