package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.gui.AboutDialog;
import dev.gl.flagsandcapitals.gui.MainWindow;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author gl
 */
public class AboutDialogAbstractAction extends AbstractAction {

    private MainWindow mw;

    public AboutDialogAbstractAction(MainWindow mw) {
        this.mw = mw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutDialog dialog = new AboutDialog(mw, true);
        dialog.setVisible(true);
    }
}
