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
    private static Map<String, GameMode> gameModeByName;
    private static Map<Integer, GameMode> gameModeByCode;

    private GameMode(Integer code, String name, String resourceBundleKey) {
        this.code = code;
        this.name = name;
        this.resourceBundleKey = resourceBundleKey;
    }

    static {
        gameModeByName = new HashMap<>();
        gameModeByCode = new HashMap<>();
        Arrays.asList(GameMode.values()).stream()
                .forEach(mode -> {
                    gameModeByName.put(mode.name, mode);
                    gameModeByCode.put(mode.code, mode);
                });
    }

    public static GameMode getGameModeByName(String name) {
        return gameModeByName.getOrDefault(name, GameMode.FLAGS);
    }
    
    public static GameMode getGameModeByCode(Integer code) {
        return gameModeByCode.getOrDefault(code, GameMode.FLAGS);
    }

    @Override
    public String toString() {
        return toStringLocalized();
    }

    public String toStringLocalized() {
        return Configuration.getResourceBundle().getString(resourceBundleKey);
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
