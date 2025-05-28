package dev.gl.flagsandcapitals.db.entities;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.enums.GameMode;
import dev.gl.flagsandcapitals.enums.Region;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class DbGames {

    private static final Logger LOGGER = Logging.getLocalLogger(DbGames.class);

    private Integer id;
    private GameMode gameMode;
    private Region region;
    private Boolean isWin;
    private Integer score;
    private Integer mistakes;
    private Integer keysUsed;

    public DbGames(Integer id, GameMode gameMode, Region region,
            Boolean isWin, Integer score, Integer mistakes, Integer keysUsed) {
        this.id = id;
        this.gameMode = gameMode;
        this.region = region;
        this.isWin = isWin;
        this.score = score;
        this.mistakes = mistakes;
        this.keysUsed = keysUsed;
    }

    public static Map<Integer, DbGames> getAllRows(HyperConnection con) {
        if (con == null) {
            return null;
        }

        Map<Integer, DbGames> rowsById = new HashMap<>();
        try (Statement stmt = con.getCon().createStatement()) {
            String sql = """
                         SELECT g.*
                            , m.code AS mode_code
                            , r.code AS region_code
                         FROM GAMES AS g
                         INNER JOIN GAME_MODE AS m ON g.game_mode_id = m.id
                         INNER JOIN REGION AS r ON g.region_id = r.id
                         """;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt(1);
                Boolean isWin = rs.getBoolean(4);
                Integer scores = rs.getInt(5);
                Integer mistakes = rs.getInt(6);
                Integer keysUsed = rs.getInt(7);
                Integer modeCode = rs.getInt(8);
                Integer regionCode = rs.getInt(9);
                DbGames entry = new DbGames(id,
                        GameMode.getGameModeByCode(modeCode),
                        Region.getRegionByCode(regionCode),
                        isWin,
                        scores,
                         mistakes,
                        keysUsed);

                rowsById.put(id, entry);
            }

            LOGGER.info("read data from db: " + rowsById.size());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return rowsById;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Boolean getIsWin() {
        return isWin;
    }

    public void setIsWin(Boolean isWin) {
        this.isWin = isWin;
    }
    
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMistakes() {
        return mistakes;
    }

    public void setMistakes(Integer mistakes) {
        this.mistakes = mistakes;
    }

    public Integer getKeysUsed() {
        return keysUsed;
    }

    public void setKeysUsed(Integer keysUsed) {
        this.keysUsed = keysUsed;
    }

}
