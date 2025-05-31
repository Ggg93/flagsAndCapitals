package dev.gl.flagsandcapitals.enums;

import dev.gl.flagsandcapitals.utils.Configuration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static dev.gl.flagsandcapitals.enums.GameMode.FLAGS;
import static dev.gl.flagsandcapitals.enums.GameMode.CAPITALS;
import static dev.gl.flagsandcapitals.enums.Region.ALL;

/**
 *
 * @author gl
 */
public enum Achievement {
    FIRST_GAME(1, "FIRST_GAME", "achievementsFirstGame", null, null, 1, 0, 0, 0, 0),
    TEN_GAMES(2, "TEN_GAMES", "achievementsTenGames", null, null, 10, 0, 0, 0, 0),
    ONE_HUNDRED_GAMES(3, "ONE_HUNDRED_GAMES", "achievementsOneHundredGames", null, null, 100, 0, 0, 0, 0),
    TEN_GUESSED_FLAGS(4, "TEN_GUESSED_FLAGS", "achievementsTenGuessedFlags", FLAGS, null, 0, 0, 10, 0, 0),
    FIFTY_GUESSED_FLAGS(5, "FIFTY_GUESSED_FLAGS", "achievementsFiftyGuessedFlags", FLAGS, null, 0, 0, 50, 0, 0),
    ONE_HUNDRED_GUESSED_FLAGS(6, "ONE_HUNDRED_GUESSED_FLAGS", "achievementsOneHundredGuessedFlags", FLAGS, null, 0, 0, 100, 0, 0),
    FIVE_HUNDRED_GUESSED_FLAGS(7, "FIVE_HUNDRED_GUESSED_FLAGS", "achievementsFiveHundredGuessedFlags", FLAGS, null, 0, 0, 500, 0, 0),
    ONE_THOUSAND_GUESSED_FLAGS(8, "ONE_THOUSAND_GUESSED_FLAGS", "achievementsOneThousandGuessedFlags", FLAGS, null, 0, 0, 1000, 0, 0),
    TEN_GUESSED_CAPITALS(9, "TEN_GUESSED_CAPITALS", "achievementsTenGuessedCapitals", CAPITALS, null, 0, 0, 0, 10, 0),
    FIFTY_GUESSED_CAPITALS(10, "FIFTY_GUESSED_CAPITALS", "achievementsFiftyGuessedCapitals", CAPITALS, null, 0, 0, 0, 50, 0),
    ONE_HUNDRED_GUESSED_CAPITALS(11, "ONE_HUNDRED_GUESSED_CAPITALS", "achievementsOneHundredGuessedCapitals", CAPITALS, null, 0, 0, 0, 100, 0),
    FIVE_HUNDRED_GUESSED_CAPITALS(12, "FIVE_HUNDRED_GUESSED_CAPITALS", "achievementsFiveHundredGuessedCapitals", CAPITALS, null, 0, 0, 0, 500, 0),
    ONE_THOUSAND_GUESSED_CAPITALS(13, "ONE_THOUSAND_GUESSED_CAPITALS", "achievementsOneThousandGuessedCapitals", CAPITALS, null, 0, 0, 0, 1000, 0),
    FIRST_WIN(14, "FIRST_WIN", "achievementsFirstWin", null, null, 0, 1, 0, 0, 0),
    TEN_FLAGS_WINS(15, "TEN_FLAGS_WINS", "achievementsTenFlagsWins", FLAGS, null, 0, 10, 0, 0, 0),
    ONE_HUNDRED_FLAGS_WINS(16, "ONE_HUNDRED_FLAGS_WINS", "achievementsOneHundredFlagsWins", FLAGS, null, 0, 100, 0, 0, 0),
    TEN_CAPITALS_WINS(17, "TEN_CAPITALS_WINS", "achievementsTenCapitalsWins", CAPITALS, null, 0, 10, 0, 0, 0),
    ONE_HUNDRED_CAPITALS_WINS(18, "ONE_HUNDRED_CAPITALS_WINS", "achievementsOneHundredCapitalsWins", CAPITALS, null, 0, 100, 0, 0, 0),
    ABSOLUTE_FLAGS_WINNER(19, "ABSOLUTE_FLAGS_WINNER", "achievementsAbsoluteFlagsWinner", FLAGS, ALL, 0, 1, 0, 0, 0),
    ABSOLUTE_CAPITALS_WINNER(20, "ABSOLUTE_CAPITALS_WINNER", "achievementsAbsoluteCapitalsWinner", CAPITALS, ALL, 0, 1, 0, 0, 0),
    ONE_HUNDRED_SCORE(21, "ONE_HUNDRED_SCORE", "achievementsOneHundredScore", null, null, 0, 0, 0, 0, 100),
    ONE_THOUSAND_SCORE(22, "ONE_THOUSAND_SCORE", "achievementsOneThousandScore", null, null, 0, 0, 0, 0, 1000),
    TEN_THOUSAND_SCORE(23, "TEN_THOUSAND_SCORE", "achievementsTenThousandScore", null, null, 0, 0, 0, 0, 10000),
    ONE_HUNDRED_THOUSAND_SCORE(24, "ONE_HUNDRED_THOUSAND_SCORE", "achievementsOneHundredThousandScore", null, null, 0, 0, 0, 0, 100000),
    ONE_MILLION_SCORE(25, "ONE_MILLION_SCORE", "achievementsOneMillionScore", null, null, 0, 0, 0, 0, 1000000);

    private int code;
    private String name;
    private String localizedName;
    private GameMode gameMode;
    private Region region;
    private Integer games;
    private Integer wins;
    private Integer flags;
    private Integer capitals;
    private Integer score;
    private static Map<Integer, Achievement> achievementByCode;

    private Achievement(int code, String name, String localizedName,
            GameMode gameMode, Region region, Integer games, Integer wins,
            Integer flags, Integer capitals, Integer score) {
        this.code = code;
        this.name = name;
        this.localizedName = localizedName;
        this.gameMode = gameMode;
        this.region = region;
        this.games = games;
        this.wins = wins;
        this.flags = flags;
        this.capitals = capitals;
        this.score = score;
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
