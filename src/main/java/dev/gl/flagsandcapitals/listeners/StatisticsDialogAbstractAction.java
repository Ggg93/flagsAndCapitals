package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.gui.StatisticsDialog;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author gl
 */
public class StatisticsDialogAbstractAction extends AbstractAction {

    private MainWindow mw;

    public StatisticsDialogAbstractAction(MainWindow mw) {
        this.mw = mw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StatisticsDialog dialog = new StatisticsDialog(mw, true);
        dialog.setVisible(true);
    }
}
