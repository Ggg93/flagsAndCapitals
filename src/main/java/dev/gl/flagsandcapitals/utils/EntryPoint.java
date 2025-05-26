package dev.gl.flagsandcapitals.utils;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.listeners.GlobalWindowEventListener;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author gl
 */
public class EntryPoint {

    public static void main(String[] args) {
        try {
            // set DB connection
            HyperConnection con = HyperConnection.getInstance();
            con.setConnection();
            
            // load configuration from DB
            Configuration.loadConfiguration();
            
            // add listener to close db connection in any case
            Toolkit.getDefaultToolkit().addAWTEventListener(new GlobalWindowEventListener(), AWTEvent.WINDOW_EVENT_MASK);
            
            // start main window
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            SwingUtilities.invokeLater(() -> {
                MainWindow mw = new MainWindow();
                mw.setVisible(true);
            });
        } catch (Exception ex) {
            Logger.getLogger(EntryPoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
