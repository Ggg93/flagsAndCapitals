package dev.gl.flagsandcapitals.listeners;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.db.entities.DbSettings;
import dev.gl.flagsandcapitals.gui.SettingsDialog;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class SettingsDialogSaveButtonListener implements ActionListener {
    
    private SettingsDialog parent;
    private Logger logger;

    public SettingsDialogSaveButtonListener(SettingsDialog parent) {
        this.parent = parent;
        logger = Logging.getLocalLogger(this.getClass());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        HyperConnection con = HyperConnection.getInstance();
        Map<String, DbSettings> settings = DbSettings.getAllSettings(con);
        
        
        DbSettings language = settings.get("lang");
        if (!parent.getSelectedLanguage().getIsoCode().equalsIgnoreCase(language.getValString())) {
            parent.needRestart();
        }
        language.setValString(parent.getSelectedLanguage().getIsoCode());
        
        DbSettings gameMode = settings.get("gameMode");
        gameMode.setValString(parent.getSelectedGameMode().getName());
        
        DbSettings difficulty = settings.get("difficulty");
        difficulty.setValString(parent.getSelectedDifficulty().getCode());
        
        DbSettings.updateRows(con, new ArrayList<>(settings.values()));
        Configuration.loadConfiguration();
        
        parent.setSaveButtonEnabled(false);
    }
    
}
