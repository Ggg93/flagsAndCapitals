package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.gui.SettingsDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author gl
 */
public class SettingsDialogElementsStateListener implements ActionListener {
    
    private SettingsDialog parent;

    public SettingsDialogElementsStateListener(SettingsDialog parent) {
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        parent.setSaveButtonEnabled(true);
    }
    
}
