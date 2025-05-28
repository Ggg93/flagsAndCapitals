package dev.gl.flagsandcapitals.db.entities;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.enums.GameMode;
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
public class DbGameMode {
    private static final Logger LOGGER = Logging.getLocalLogger(DbGameMode.class);
    
    private int id;
    private GameMode gameMode;

    public DbGameMode(int id, GameMode gameMode) {
        this.id = id;
        this.gameMode = gameMode;
    }
    
    public static Map<Integer, DbGameMode> getAllRows(HyperConnection con) {
        if (con == null) {
            return null;
        }

        Map<Integer, DbGameMode> rowsById = new HashMap<>();
        try (Statement stmt = con.getCon().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM GAME_MODE");
            ResultSet rs = stmt.executeQuery(sb.toString());
            while (rs.next()) {
                Integer id = rs.getInt(1);
                Integer code = rs.getInt(2);
                DbGameMode entry = new DbGameMode(id,
                        GameMode.getGameModeByCode(code));

                rowsById.put(id, entry);
            }

            LOGGER.info("read data from db: " + rowsById.size());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return rowsById;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
    
    
    
    
}
