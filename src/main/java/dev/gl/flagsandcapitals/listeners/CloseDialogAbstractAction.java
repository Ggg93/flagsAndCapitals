package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.gui.SettingsDialog;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.EntryPoint;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author gl
 */
public class CloseDialogAbstractAction extends AbstractAction {

    private Logger logger = Logging.getLocalLogger(this.getClass());
    private JDialog parent;

    public CloseDialogAbstractAction(JDialog parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (parent instanceof SettingsDialog) {
            SettingsDialog settingsDialog = (SettingsDialog) parent;
            if (settingsDialog.isSaveButtonEnabled()) {
                String message = Configuration.getResourceBundle().getString("settingsConfirmMessage");
                String title = Configuration.getResourceBundle().getString("settingsTitle");
                int choice = JOptionPane.showConfirmDialog(parent,
                        message, title,
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                logger.log(Level.SEVERE, "choice = " + choice);
                if (choice != 0) {
                    return;
                }
            }

            if (settingsDialog.getNeedRestart()) {
                MainWindow mw = ((MainWindow) parent.getParent());
                parent.dispose();
                mw.dispose();
                EntryPoint.main(null);
            }
        }
        logger.log(Level.FINE, parent.getName() + " closed by user");
        parent.dispose();
    }

}
