package dev.gl.flagsandcapitals.utils;

import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.listeners.AboutDialogAbstractAction;
import dev.gl.flagsandcapitals.listeners.ClosingMainWindowAbstractAction;
import dev.gl.flagsandcapitals.listeners.SettingsDialogAbstractAction;

/**
 *
 * @author gl
 */
public class MainWindowListenersKeeper {
    private ClosingMainWindowAbstractAction closingMainWindowListener;
    private SettingsDialogAbstractAction settingsDialogListener;
    private AboutDialogAbstractAction aboutDialogListener;

    public MainWindowListenersKeeper(MainWindow mainWindow) {
        closingMainWindowListener = new ClosingMainWindowAbstractAction(mainWindow);
        settingsDialogListener = new SettingsDialogAbstractAction(mainWindow);
        aboutDialogListener = new AboutDialogAbstractAction(mainWindow);
    }

    public ClosingMainWindowAbstractAction getClosingMainWindowListener() {
        return closingMainWindowListener;
    }

    public SettingsDialogAbstractAction getSettingsDialogListener() {
        return settingsDialogListener;
    }

    public AboutDialogAbstractAction getAboutDialogListener() {
        return aboutDialogListener;
    }
    
    
    
    
    
}
