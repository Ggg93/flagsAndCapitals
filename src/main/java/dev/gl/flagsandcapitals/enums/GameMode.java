package dev.gl.flagsandcapitals.enums;

import dev.gl.flagsandcapitals.utils.Configuration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gl
 */
public enum GameMode {
    FLAGS("flags", "modeFlags"),
    CAPITALS("capitals", "modeCapitals");

    private String code;
    private String resourceBundleKey;
    private static Map<String, GameMode> gameModeByCode;

    private GameMode(String code, String resourceBundleKey) {
        this.code = code;
        this.resourceBundleKey = resourceBundleKey;
    }
    
    static {
        gameModeByCode = new HashMap<>();
        Arrays.asList(GameMode.values()).stream()
                .forEach(mode -> gameModeByCode.put(mode.code, mode));
    }
    
    public static GameMode getGameModeByCode(String code) {
        return gameModeByCode.getOrDefault(code, GameMode.FLAGS);
    }

    @Override
    public String toString() {
        return toStringLocalized();
    }

    public String toStringLocalized() {
        return Configuration.getResourceBundle().getString(resourceBundleKey);
    }

    public String getCode() {
        return code;
    }
    
}
