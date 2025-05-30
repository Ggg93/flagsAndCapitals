package dev.gl.flagsandcapitals.utils;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.db.entities.DbSettings;
import dev.gl.flagsandcapitals.enums.Difficulty;
import dev.gl.flagsandcapitals.enums.GameMode;
import dev.gl.flagsandcapitals.enums.Language;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class Configuration {

    private static final Logger LOGGER = Logging.getLocalLogger(Configuration.class);
    private static Language lang;
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("dict", Locale.US);
    private static GameMode gameMode;
    private static Difficulty difficulty;
    
    /**
     * loading configuration parameters from db
     */
    public static void loadConfiguration() {
        HyperConnection con = HyperConnection.getInstance();
        Map<String, DbSettings> allSettings = DbSettings.getAllSettings(con);
        
        DbSettings installedLanguage = allSettings.get("lang");
        lang = Language.getLanguageByIsoCode(installedLanguage.getValString());
        
        if (lang.equals(Language.EN)) {
            resourceBundle = ResourceBundle.getBundle("dict", Locale.ENGLISH);
        } else if (lang.equals(Language.RU)) {
            resourceBundle = ResourceBundle.getBundle("dict", new Locale.Builder().setLanguageTag("ru").build());
        }
        
        DbSettings installedGameMode = allSettings.get("gameMode");
        gameMode = GameMode.getGameModeByName(installedGameMode.getValString());
        
        DbSettings installedDifficulty = allSettings.get("difficulty");
        difficulty = Difficulty.getDifficultyByCode(installedDifficulty.getValString());
        
        LOGGER.log(Level.FINE, "Configuration has been read");
    }

    public static Language getLang() {
        return lang;
    }

    public static GameMode getGameMode() {
        return gameMode;
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }
    
    /**
     * need to redo: should update data in db and read from there after that
     * @param lang 
     */
    public static void setLang(String lang) {
//        Configuration.lang = lang;
        if (lang.equals("en")) {
            resourceBundle = ResourceBundle.getBundle("dict", Locale.ENGLISH);
        } else if (lang.equals("ru")) {
            resourceBundle = ResourceBundle.getBundle("dict", new Locale.Builder().setLanguageTag("ru").build());
        }
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
    
}
