package dev.gl.flagsandcapitals.db.entities;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
import dev.gl.flagsandcapitals.enums.Region;
import dev.gl.flagsandcapitals.utils.Configuration;
import dev.gl.flagsandcapitals.utils.logging.Logging;
import java.sql.PreparedStatement;
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
public class DbGeography {

    private static final Logger LOGGER = Logging.getLocalLogger(DbGeography.class);

    private Integer id;
    private String isoCode;
    private String countryEn;
    private String countryRu;
    private String capitalEn;
    private String capitalRu;
    private Region region;

    public DbGeography(Integer id, String isoCode, String countryEn,
            String countryRu, String capitalEn, String capitalRu, Region region) {
        this.id = id;
        this.isoCode = isoCode;
        this.countryEn = countryEn;
        this.countryRu = countryRu;
        this.capitalEn = capitalEn;
        this.capitalRu = capitalRu;
        this.region = region;
    }

    public static Map<Integer, DbGeography> getAllRows(HyperConnection con) {

        Map<Integer, DbGeography> rowsById = new HashMap<>();

        String sql = """
                     SELECT g.*
                        , r.code AS region_code
                     FROM GEOGRAPHY AS g
                     INNER JOIN REGION AS r ON g.region_id = r.id
                    """;

        try (Statement stmt = con.getCon().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String isoCode = rs.getString(2);
                String countryEn = rs.getString(3);
                String countryRu = rs.getString(4);
                String capitalEn = rs.getString(5);
                String capitalRu = rs.getString(6);
                Integer regionCode = rs.getInt(8);
                DbGeography entry = new DbGeography(id,
                        isoCode,
                        countryEn,
                        countryRu,
                        capitalEn,
                        capitalRu,
                        Region.getRegionByCode(regionCode));

                rowsById.put(id, entry);
            }

            LOGGER.info("read data from db: " + rowsById.size());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return rowsById;
    }

    public static Map<Integer, DbGeography> getRowsByRegionFilter(HyperConnection con, Region regionFilter) {

        Map<Integer, DbGeography> rowsById = new HashMap<>();

        String sql = """
                         SELECT g.*
                            , r.code AS region_code
                         FROM GEOGRAPHY AS g
                         INNER JOIN REGION AS r ON g.region_id = r.id
                            AND r.code = ?
                         """;
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            if (regionFilter != null) {
                pstmt.setInt(1, regionFilter.getCode());
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String isoCode = rs.getString(2);
                String countryEn = rs.getString(3);
                String countryRu = rs.getString(4);
                String capitalEn = rs.getString(5);
                String capitalRu = rs.getString(6);
                Integer regionCode = rs.getInt(8);
                DbGeography entry = new DbGeography(id,
                        isoCode,
                        countryEn,
                        countryRu,
                        capitalEn,
                        capitalRu,
                        Region.getRegionByCode(regionCode));

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

    public String getIsoCode() {
        return isoCode;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public String getCountryRu() {
        return countryRu;
    }

    public String getCapitalEn() {
        return capitalEn;
    }

    public String getCapitalRu() {
        return capitalRu;
    }

    public Region getRegion() {
        return region;
    }

    public String getCountryLocalized() {
        String country = null;
        switch (Configuration.getLang()) {
            case EN:
                country = countryEn;
                break;
            case RU:
                country = countryRu;
                break;
        }
        return country;
    }

}
