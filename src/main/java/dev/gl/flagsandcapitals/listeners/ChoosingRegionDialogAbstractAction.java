package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.gui.ChoosingRegionDialog;
import dev.gl.flagsandcapitals.gui.MainWindow;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author gl
 */
public class ChoosingRegionDialogAbstractAction extends AbstractAction {

    private MainWindow mw;

    public ChoosingRegionDialogAbstractAction(MainWindow mw) {
        this.mw = mw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ChoosingRegionDialog dialog = new ChoosingRegionDialog(mw, true);
        dialog.setVisible(true);
    }

}
