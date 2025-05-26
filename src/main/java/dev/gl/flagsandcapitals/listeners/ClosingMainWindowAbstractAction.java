package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;

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
            HyperConnection con = HyperConnection.getInstance();
            con.closeConnection();

            parent.dispose();
            System.exit(0);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
        }

    }

}
