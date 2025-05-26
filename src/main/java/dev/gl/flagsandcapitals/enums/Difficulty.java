package dev.gl.flagsandcapitals.enums;

import dev.gl.flagsandcapitals.utils.Configuration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gl
 */
public enum Difficulty {
    EASY("easy", "difficultyEasy", 10, 1),
    MEDIUM("medium", "difficultyMedium", 5, 3),
    HARD("hard", "difficultyHard", 3, 5);

    private String code;
    private String resourceBundleKey;
    private Integer lives;
    private Integer hintRate;
    private static Map<String, Difficulty> difficultyByCode;

    private Difficulty(String code, String resourceBundleKey, Integer lives, Integer hintRate) {
        this.code = code;
        this.resourceBundleKey = resourceBundleKey;
        this.lives = lives;
        this.hintRate = hintRate;
    }

    static {
        difficultyByCode = new HashMap<>();
        Arrays.asList(Difficulty.values()).stream()
                .forEach(difficulty -> difficultyByCode.put(difficulty.code, difficulty));
    }

    public static Difficulty getDifficultyByCode(String code) {
        return difficultyByCode.getOrDefault(code, Difficulty.MEDIUM);
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
