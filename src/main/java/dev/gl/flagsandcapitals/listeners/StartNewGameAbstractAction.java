package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.enums.MainWindowMode;
import dev.gl.flagsandcapitals.enums.Region;
import dev.gl.flagsandcapitals.gui.ChoosingRegionDialog;
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
public class StartNewGameAbstractAction extends AbstractAction {

    private static final Logger LOGGER = Logging.getLocalLogger(StartNewGameAbstractAction.class);
    private ChoosingRegionDialog parent;
    private Region region;
    private MainWindow mw;

    public StartNewGameAbstractAction(ChoosingRegionDialog parent, MainWindow mw) {
        this.parent = parent;
        this.mw = mw;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        region = parent.getRegion();
        mw.setMainWindowMode(MainWindowMode.PLAYING);
        
        LOGGER.log(Level.FINE, parent.getName() + " closed by user");
        parent.dispose();
    }
    
}
