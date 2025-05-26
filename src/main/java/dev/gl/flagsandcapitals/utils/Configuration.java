package dev.gl.flagsandcapitals.utils;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.db.entities.DbSettings;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author gl
 */
public class Configuration {

    private static String lang = "en"; // An ISO 639 alpha-2 or alpha-3 language code
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("dict", Locale.US);
    
    /**
     * loading configuration parameters from db
     */
    public static void loadConfiguration() {
        HyperConnection con = HyperConnection.getInstance();
        Map<String, DbSettings> allSettings = DbSettings.getAllSettings(con);
        
        DbSettings installedLanguage = allSettings.get("lang");
        lang = installedLanguage.getValString();
    }

    public static String getLang() {
        return lang;
    }

    /**
     * need to redo: should update data in db and read from there after that
     * @param lang 
     */
    public static void setLang(String lang) {
        Configuration.lang = lang;
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
