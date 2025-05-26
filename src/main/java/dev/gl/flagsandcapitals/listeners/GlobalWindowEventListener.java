package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author gl
 */
public class GlobalWindowEventListener implements AWTEventListener {

    private static Logger logger = Logging.getLocalLogger(HyperConnection.class);

    @Override
    public void eventDispatched(AWTEvent event) {
        if (event instanceof WindowEvent && event.getID() == WindowEvent.WINDOW_CLOSING) {
            JFrame window = (JFrame) event.getSource();
            if (window instanceof MainWindow) {
                try {
                    HyperConnection con = HyperConnection.getInstance();
                    con.closeConnection();
                    window.dispose();
                    System.exit(0);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
                }
            } else {
                window.dispose();
            }
        }
    }

}
