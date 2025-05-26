package dev.gl.flagsandcapitals.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author gl
 */
public class Configuration {

    private static String lang = "en"; // An ISO 639 alpha-2 or alpha-3 language code
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("dict", Locale.US);

    public static String getLang() {
        return lang;
    }

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
