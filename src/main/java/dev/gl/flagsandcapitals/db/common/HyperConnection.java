package dev.gl.flagsandcapitals.db.common;

import dev.gl.flagsandcapitals.enums.Achievement;
import dev.gl.flagsandcapitals.enums.GameMode;
import dev.gl.flagsandcapitals.enums.Region;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gl
 */
public class HyperConnection {

    private static HyperConnection instance;
    private static final Logger LOGGER = Logging.getLocalLogger(HyperConnection.class);
    private Connection con;

    private HyperConnection() {
    }

    public static HyperConnection getInstance() {
        if (instance == null) {
            instance = new HyperConnection();
            LOGGER.log(Level.CONFIG, "HyperConnection instance created");
        }
        return instance;

    }

    public void setConnection() {
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:file:db/db;shutdown=true", "SA", "");
            setInitialParameters();
            LOGGER.log(Level.CONFIG, "con to db established");
            createTablesAtFirstLaunch();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new RuntimeException("Connection to DB has failed");
        }
    }

    public void closeConnection() throws SQLException {
        if (con == null) {
            return;
        }

        if (!con.isClosed()) {
            con.rollback();
            con.close();
            LOGGER.log(Level.CONFIG, "con to db closed");
        }
    }

    private void setInitialParameters() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("SET DATABASE DEFAULT TABLE TYPE MEMORY;");
        stmt.close();
    }

    private void createTablesAtFirstLaunch() throws Exception {
        createGameModesTable();
        createRegionTable();
        createGeographyTable();
        createGamesTable();
        createAchievementsTable();
        createSettingsTable();
    }

    private void createGameModesTable() throws SQLException {
        Statement stmt = con.createStatement();
        String query = """
                           CREATE TABLE IF NOT EXISTS game_mode (
                            id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            code INT,
                            name VARCHAR(50)
                           );
                           """;
        stmt.executeUpdate(query);

        if (isTableEmpty("game_mode")) {
            Arrays.asList(GameMode.values()).stream()
                    .forEach(mode -> {
                        String sql = "INSERT INTO game_mode (code, name) VALUES (?, ?)";
                        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                            pstmt.setInt(1, mode.getCode());
                            pstmt.setString(2, mode.getName());
                            pstmt.executeUpdate();
                        } catch (SQLException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        }
                    });
            LOGGER.log(Level.CONFIG, "First rows inserted in \"game_mode\" table");
        }
    }

    private void createRegionTable() throws SQLException {
        Statement stmt = con.createStatement();
        String query = """
                           CREATE TABLE IF NOT EXISTS region (
                            id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            code INT,
                            name VARCHAR(50)
                           );
                           """;
        stmt.executeUpdate(query);

        if (isTableEmpty("region")) {
            Arrays.asList(Region.values()).stream()
                    .forEach(region -> {
                        String sql = "INSERT INTO region (code, name) VALUES (?, ?)";
                        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                            pstmt.setInt(1, region.getCode());
                            pstmt.setString(2, region.getName());
                            pstmt.executeUpdate();
                        } catch (SQLException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        }
                    });
            LOGGER.log(Level.CONFIG, "First rows inserted in \"region\" table");
        }
    }

    private void createGamesTable() throws SQLException {
        Statement stmt = con.createStatement();
        String query = """
                           CREATE TABLE IF NOT EXISTS games (
                            id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            game_mode_id INT NOT NULL,
                            region_id INT NOT NULL,
                            is_win BOOLEAN,
                            score INT,
                            mistakes INT,
                            keys_used INT,
                            date DATE,
                            guessed_flags INT,
                            guessed_capitals INT,
                       
                            FOREIGN KEY (game_mode_id) REFERENCES game_mode(id) ON DELETE RESTRICT,
                            FOREIGN KEY (region_id) REFERENCES region(id) ON DELETE RESTRICT
                           );
                           """;
        stmt.executeUpdate(query);
    }

    private void createAchievementsTable() throws SQLException {
        Statement stmt = con.createStatement();
        String query = """
                           CREATE TABLE IF NOT EXISTS achievements (
                            id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            code INT,
                            name VARCHAR(50),
                            achieved_date DATE
                           );
                           """;
        stmt.executeUpdate(query);

        if (isTableEmpty("achievements")) {
            int[] affectedRows = null;
            String sql = "INSERT INTO achievements (code, name, achieved_date) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                for (Achievement achievement : Achievement.values()) {
                    pstmt.setInt(1, achievement.getCode());
                    pstmt.setString(2, achievement.getName());
                    pstmt.setDate(3, null);
                    pstmt.addBatch();
                }

                affectedRows = pstmt.executeBatch();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            }

            LOGGER.config("Insert first " + affectedRows.length + " rows in the \"achievements\" table");
        }

    }

    private void createSettingsTable() throws SQLException {
        Statement stmt = con.createStatement();
        String query = """
                           CREATE TABLE IF NOT EXISTS settings (
                            id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            parameter VARCHAR(50) NOT NULL,
                            val_bool BOOLEAN,
                            val_int INT,
                            val_string VARCHAR(50)
                           );
                           """;
        stmt.executeUpdate(query);

        if (isTableEmpty("settings")) {
            int[] affectedRows = null;
            String sql = """ 
                         INSERT INTO settings 
                         (parameter, val_bool, val_int, val_string) 
                         VALUES (?, ?, ?, ?)
                         """;
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, "lang");
                pstmt.setBoolean(2, false);
                pstmt.setInt(3, 0);
                pstmt.setString(4, "en");
                pstmt.addBatch();

                pstmt.setString(1, "gameMode");
                pstmt.setBoolean(2, false);
                pstmt.setInt(3, 0);
                pstmt.setString(4, "flags");
                pstmt.addBatch();

                pstmt.setString(1, "difficulty");
                pstmt.setBoolean(2, false);
                pstmt.setInt(3, 0);
                pstmt.setString(4, "medium");
                pstmt.addBatch();

                affectedRows = pstmt.executeBatch();

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
            }
            LOGGER.config("Insert first " + affectedRows.length + " rows in the \"settings\" table");
        }
    }

    private boolean isTableEmpty(String tableName) throws SQLException {
        String query = new StringBuilder("SELECT * FROM ").append(tableName).toString();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return !rs.isBeforeFirst();
    }

    public Connection getCon() {
        return con;
    }

    private void createGeographyTable() throws Exception {
        Statement stmt = con.createStatement();
        String query = """
                           CREATE TABLE IF NOT EXISTS geography (
                            id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            iso_code VARCHAR(10),
                            country_en VARCHAR(100),
                            country_ru VARCHAR(100),
                            capital_en VARCHAR(100),
                            capital_ru VARCHAR(100),
                            region_id INT NOT NULL,
                       
                            FOREIGN KEY (region_id) REFERENCES region(id) ON DELETE RESTRICT
                           );
                           """;
        stmt.executeUpdate(query);

        if (isTableEmpty("geography")) {
            // preparing to read a file. Skip first line with headers
            File csv = new File(this.getClass().getClassLoader()
                    .getResource("csv/geography.csv").toURI());
            BufferedReader reader = new BufferedReader(new FileReader(csv));
            reader.readLine();
            String line;
            
            // preparing sql query
            String sql = """
                            INSERT INTO geography (iso_code, country_en, country_ru, capital_en, capital_ru, region_id)
                            VALUES (?, ?, ?, ?, ?, ?)
                         """;
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";", -1);

                pstmt.setString(1, columns[0]);
                pstmt.setString(2, columns[1]);
                pstmt.setString(3, columns[2]);
                pstmt.setString(4, columns[3]);
                pstmt.setString(5, columns[4]);
                pstmt.setInt(6, Integer.parseInt(columns[5]));
                pstmt.addBatch();
            }

            int[] affectedRows = pstmt.executeBatch();
            LOGGER.log(Level.CONFIG, "First " + affectedRows.length + "rows inserted in \"geography\" table");
        }
    }
}
