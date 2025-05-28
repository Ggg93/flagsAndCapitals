package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.gui.AchievementsDialog;
import dev.gl.flagsandcapitals.gui.MainWindow;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author gl
 */
public class AchievementsDialogAbstractAction extends AbstractAction {

    private MainWindow mw;

    public AchievementsDialogAbstractAction(MainWindow mw) {
        this.mw = mw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AchievementsDialog dialog = new AchievementsDialog(mw, true);
        dialog.setVisible(true);
    }
}
