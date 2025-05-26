package dev.gl.flagsandcapitals.db.entities;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class DbSettings {

    private static final Logger LOGGER = Logging.getLocalLogger(DbSettings.class);

    private Integer id;
    private String parameter;
    private Boolean valBool;
    private Integer valInt;
    private String valString;

    public DbSettings(Integer id, String parameter, Boolean valBool, Integer valInt, String valString) {
        this.id = id;
        this.parameter = parameter;
        this.valBool = valBool;
        this.valInt = valInt;
        this.valString = valString;
    }

    public Integer getId() {
        return id;
    }

    public String getParameter() {
        return parameter;
    }

    public Boolean getValBool() {
        return valBool;
    }

    public Integer getValInt() {
        return valInt;
    }

    public String getValString() {
        return valString;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public void setValBool(Boolean valBool) {
        this.valBool = valBool;
    }

    public void setValInt(Integer valInt) {
        this.valInt = valInt;
    }

    public void setValString(String valString) {
        this.valString = valString;
    }

    public static Map<String, DbSettings> getAllSettings(HyperConnection con) {
        if (con == null) {
            return null;
        }

        Map<String, DbSettings> settings = new HashMap<>();
        try (Statement stmt = con.getCon().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM SETTINGS ");
            ResultSet rs = stmt.executeQuery(sb.toString());
            while (rs.next()) {
                DbSettings entry = new DbSettings(rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getInt(4),
                        rs.getString(5));

                settings.put(entry.getParameter(), entry);
            }

            LOGGER.info("read settings from db: " + settings.size());

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return settings;
    }

    public static DbSettings getSettingByParameter(HyperConnection con, String parameter) {

        if (con == null) {
            return null;
        }

        DbSettings entry = null;

        try (Statement stmt = con.getCon().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM SETTINGS ");
            sb.append("WHERE parameter = '").append(parameter).append("'");
            ResultSet rs = stmt.executeQuery(sb.toString());
            rs.next();
            entry = new DbSettings(rs.getInt(1),
                    rs.getString(2),
                    rs.getBoolean(3),
                    rs.getInt(4),
                    rs.getString(5));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return entry;
    }

    public static int updateRows(HyperConnection con, List<DbSettings> entries) {
        int affectedRows = entries.stream()
                .map(entry -> updateRow(con, entry))
                .mapToInt(Integer::intValue)
                .sum();
        LOGGER.log(Level.FINE, affectedRows + " rows updated");
        return affectedRows;
    }

    public static int updateRow(HyperConnection con, DbSettings entry) {
        int affectedRows = 0;
        try (Statement stmt = con.getCon().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE SETTINGS ");
            sb.append("SET VAL_BOOL = ").append(entry.valBool);
            sb.append(", VAL_INT = ").append(entry.valInt);
            sb.append(", VAL_STRING = '").append(entry.valString).append("' ");
            sb.append("WHERE ID = ").append(entry.id);
            affectedRows = stmt.executeUpdate(sb.toString());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        return affectedRows;
    }
}
