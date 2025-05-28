package dev.gl.flagsandcapitals.db.entities;

import dev.gl.flagsandcapitals.db.common.HyperConnection;
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
public class DbRegion {
    private static final Logger LOGGER = Logging.getLocalLogger(DbRegion.class);
    
    private int id;
    private Region region;

    public DbRegion(int id, Region region) {
        this.id = id;
        this.region = region;
    }
    
    public static Map<Integer, DbRegion> getAllRows(HyperConnection con) {
        if (con == null) {
            return null;
        }

        Map<Integer, DbRegion> rowsById = new HashMap<>();
        try (Statement stmt = con.getCon().createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM REGION");
            ResultSet rs = stmt.executeQuery(sb.toString());
            while (rs.next()) {
                Integer id = rs.getInt(1);
                Integer code = rs.getInt(2);
                DbRegion entry = new DbRegion(id,
                        Region.getRegionByCode(code));

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
    
    
    
}
