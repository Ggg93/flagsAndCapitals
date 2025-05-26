package dev.gl.flagsandcapitals.enums;

import dev.gl.flagsandcapitals.utils.Configuration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gl
 */
public enum Language {
    EN("en", "English", "languageEnglish"),
    RU("ru", "Russian", "languageRussian");
    
    private String isoCode;
    private String description;
    private String resourceBundleKey;
    private static Map<String, Language> languagesByIsoCodes;
    
    private Language(String isoCode, String description, String resourceBundleKey) {
        this.isoCode = isoCode;
        this.description = description;
        this.resourceBundleKey = resourceBundleKey;
    }
    
    static {
        languagesByIsoCodes = new HashMap<>();
        Arrays.asList(Language.values()).stream()
                .forEach(lang -> languagesByIsoCodes.put(lang.isoCode, lang));
    }
    
    public static Language getLanguageByIsoCode(String code) {
        return languagesByIsoCodes.getOrDefault(code, Language.EN);
    }
    
    @Override
    public String toString() {
        return toStringLocalized();
    }
    
    public String toStringLocalized() {
        return Configuration.getResourceBundle().getString(resourceBundleKey);
    }

    public String getIsoCode() {
        return isoCode;
    }
    
}
