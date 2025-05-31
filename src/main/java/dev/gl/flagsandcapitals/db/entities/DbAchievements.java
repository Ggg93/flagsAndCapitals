package dev.gl.flagsandcapitals.db.entities;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.enums.Achievement;
import dev.gl.flagsandcapitals.utils.DateUtils;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class DbAchievements {

    private static final Logger LOGGER = Logging.getLocalLogger(DbAchievements.class);

    private Integer id;
    private Achievement achievement;
    private LocalDate achievedDate;

    public DbAchievements(Integer id, Achievement achievement, LocalDate achievedDate) {
        this.id = id;
        this.achievement = achievement;
        this.achievedDate = achievedDate;
    }

    public static Map<Integer, DbAchievements> getAllRows(HyperConnection con) {
        if (con == null) {
            return null;
        }

        Map<Integer, DbAchievements> rowsById = new HashMap<>();
        try (Statement stmt = con.getCon().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ACHIEVEMENTS");
            ResultSet rs = stmt.executeQuery(sb.toString());
            while (rs.next()) {
                Integer id = rs.getInt(1);
                Achievement achievement = Achievement.getAchievementByCode(rs.getInt(2));
                LocalDate achievedDate = DateUtils.convertDateToLocalDate(rs.getDate(4));
                DbAchievements entry = new DbAchievements(id, achievement, achievedDate);

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

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public LocalDate getAchievedDate() {
        return achievedDate;
    }

    public void setAchievedDate(LocalDate achievedDate) {
        this.achievedDate = achievedDate;
    }

}
