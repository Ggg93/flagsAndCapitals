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
    FLAGS(0, "flags", "modeFlags"),
    CAPITALS(1, "capitals", "modeCapitals");

    private Integer code;
    private String name;
    private String resourceBundleKey;
    private static Map<String, GameMode> gameModeByCode;

    private GameMode(Integer code, String name, String resourceBundleKey) {
        this.code = code;
        this.name = name;
        this.resourceBundleKey = resourceBundleKey;
    }

    static {
        gameModeByCode = new HashMap<>();
        Arrays.asList(GameMode.values()).stream()
                .forEach(mode -> gameModeByCode.put(mode.name, mode));
    }
    
    public static GameMode getGameModeByCode(String name) {
        return gameModeByCode.getOrDefault(name, GameMode.FLAGS);
    }

    @Override
    public String toString() {
        return toStringLocalized();
    }

    public String toStringLocalized() {
        return Configuration.getResourceBundle().getString(resourceBundleKey);
    }

    public String getName() {
        return name;
    }
    
}
