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
    EASY("easy", "difficultyEasy", 10, 1, 3),
    MEDIUM("medium", "difficultyMedium", 5, 3, 1),
    HARD("hard", "difficultyHard", 3, 5, 0);

    private String code;
    private String resourceBundleKey;
    private Integer lives; // how many wrong answers can user make
    private Integer hintRate; // how many right answers user should give to get a new hint
    private Integer initialAvailableHints; // how many hints does player have at the start of the game
    private static Map<String, Difficulty> difficultyByCode;

    private Difficulty(String code, String resourceBundleKey, Integer lives, Integer hintRate, Integer initialAvailableHints) {
        this.code = code;
        this.resourceBundleKey = resourceBundleKey;
        this.lives = lives;
        this.hintRate = hintRate;
        this.initialAvailableHints = initialAvailableHints;
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

    public Integer getLives() {
        return lives;
    }

    public Integer getHintRate() {
        return hintRate;
    }

    public Integer getInitialAvailableHints() {
        return initialAvailableHints;
    }
    
}
