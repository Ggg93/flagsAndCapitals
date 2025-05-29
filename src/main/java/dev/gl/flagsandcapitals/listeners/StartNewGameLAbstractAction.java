package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JDialog;

/**
 *
 * @author gl
 */
public class StartNewGameLAbstractAction extends AbstractAction {

    private static final Logger LOGGER = Logging.getLocalLogger(OkDisposingAction.class);
    private JDialog parent;

    public StartNewGameLAbstractAction(JDialog parent) {
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        LOGGER.log(Level.FINE, parent.getName() + " closed by user");
        parent.dispose();
    }
    
}
