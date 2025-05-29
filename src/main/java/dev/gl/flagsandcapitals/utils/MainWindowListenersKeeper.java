package dev.gl.flagsandcapitals.utils;

import dev.gl.flagsandcapitals.gui.MainWindow;
import dev.gl.flagsandcapitals.listeners.AboutDialogAbstractAction;
import dev.gl.flagsandcapitals.listeners.AchievementsDialogAbstractAction;
import dev.gl.flagsandcapitals.listeners.BackToMainMenuAbstractAction;
import dev.gl.flagsandcapitals.listeners.ChoosingRegionDialogAbstractAction;
import dev.gl.flagsandcapitals.listeners.ClosingMainWindowAbstractAction;
import dev.gl.flagsandcapitals.listeners.SettingsDialogAbstractAction;
import dev.gl.flagsandcapitals.listeners.StatisticsDialogAbstractAction;

/**
 *
 * @author gl
 */
public class MainWindowListenersKeeper {
    private ChoosingRegionDialogAbstractAction choosingRegionDialogListener;
    private BackToMainMenuAbstractAction backToMainMenuListener;
    private ClosingMainWindowAbstractAction closingMainWindowListener;
    private SettingsDialogAbstractAction settingsDialogListener;
    private AboutDialogAbstractAction aboutDialogListener;
    private StatisticsDialogAbstractAction statisticsDialogListener;
    private AchievementsDialogAbstractAction achievementsDialogListener;

    public MainWindowListenersKeeper(MainWindow mainWindow) {
        choosingRegionDialogListener = new ChoosingRegionDialogAbstractAction(mainWindow);
        backToMainMenuListener = new BackToMainMenuAbstractAction(mainWindow);
        closingMainWindowListener = new ClosingMainWindowAbstractAction(mainWindow);
        settingsDialogListener = new SettingsDialogAbstractAction(mainWindow);
        aboutDialogListener = new AboutDialogAbstractAction(mainWindow);
        statisticsDialogListener = new StatisticsDialogAbstractAction(mainWindow);
        achievementsDialogListener = new AchievementsDialogAbstractAction(mainWindow);
    }

    public ChoosingRegionDialogAbstractAction getChoosingRegionDialogListener() {
        return choosingRegionDialogListener;
    }

    public BackToMainMenuAbstractAction getBackToMainMenuListener() {
        return backToMainMenuListener;
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

    public StatisticsDialogAbstractAction getStatisticsDialogListener() {
        return statisticsDialogListener;
    }

    public AchievementsDialogAbstractAction getAchievementsDialogListener() {
        return achievementsDialogListener;
    }
    
}
