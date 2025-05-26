package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.gui.SettingsDialog;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author gl
 */
public class SettingsDialogAbstractAction extends AbstractAction {
    
    private MainWindow mw;

    public SettingsDialogAbstractAction(MainWindow mw) {
        this.mw = mw;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        SettingsDialog dialog = new SettingsDialog(mw, true);
        dialog.setVisible(true);
    }
    
}
