package dev.gl.flagsandcapitals.utils;

import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.listeners.ClosingMainWindowAbstractAction;

/**
 *
 * @author gl
 */
public class MainWindowListenersKeeper {
    private ClosingMainWindowAbstractAction closingMainWindowListener;

    public MainWindowListenersKeeper(MainWindow mainWindow) {
        closingMainWindowListener = new ClosingMainWindowAbstractAction(mainWindow);
    }

    public ClosingMainWindowAbstractAction getClosingMainWindowListener() {
        return closingMainWindowListener;
    }
    
}
