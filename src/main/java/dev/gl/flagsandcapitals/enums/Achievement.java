package dev.gl.flagsandcapitals.enums;

import dev.gl.flagsandcapitals.utils.Configuration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gl
 */
public enum Achievement {
    FIRST_GAME(1, "FIRST_GAME", "achievementsFirstGame"),
    TEN_GAMES(2, "TEN_GAMES", "achievementsTenGames"),
    ONE_HUNDRED_GAMES(3, "ONE_HUNDRED_GAMES", "achievementsOneHundredGames"),
    FIRST_WIN(4, "FIRST_WIN", "achievementsFirstWin"),
    TEN_FLAGS_WINS(5, "TEN_FLAGS_WINS", "achievementsTenFlagsWins"),
    ONE_HUNDRED_FLAGS_WINS(6, "ONE_HUNDRED_FLAGS_WINS", "achievementsOneHundredFlagsWins"),
    TEN_CAPITALS_WINS(7, "TEN_CAPITALS_WINS", "achievementsTenCapitalsWins"),
    ONE_HUNDRED_CAPITALS_WINS(8, "ONE_HUNDRED_CAPITALS_WINS", "achievementsOneHundredCapitalsWins"),
    ABSOLUTE_FLAGS_WINNER(9, "ABSOLUTE_FLAGS_WINNER", "achievementsAbsoluteFlagsWinner"),
    ABSOLUTE_CAPITALS_WINNER(10, "ABSOLUTE_CAPITALS_WINNER", "achievementsAbsoluteCapitalsWinner");
    
    private int code;
    private String name;
    private String localizedName;
    private static Map<Integer, Achievement> achievementByCode;

    private Achievement(int code, String name, String localizedName) {
        this.code = code;
        this.name = name;
        this.localizedName = localizedName;
    }
    
    static {
        achievementByCode = new HashMap<>();
        Arrays.asList(Achievement.values()).stream()
                .forEach(achievement -> {
                    achievementByCode.put(achievement.code, achievement);
                });
    }


    public static Achievement getAchievementByCode(Integer code) {
        return achievementByCode.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return Configuration.getResourceBundle().getString(localizedName);
    }
    
    
}
