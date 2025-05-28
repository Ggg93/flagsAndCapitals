package dev.gl.flagsandcapitals.enums;

import dev.gl.flagsandcapitals.utils.Configuration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gl
 */
public enum Region {
    ALL(0, "ALL", "regionAll"),
    ASIA(1, "ASIA", "regionAsia"),
    AFRICA(2, "AFRICA", "regionAfrica"),
    NORTH_AMERICA(3, "NORTH_AMERICA", "regionNorthAmerica"),
    SOUTH_AMERICA(4, "SOUTH_AMERICA", "regionSouthAmerica"),
    EUROPE(5, "EUROPE", "regionEurope"),
    AUSTRALIA(6, "AUSTRALIA", "regionAustralia");

    private Integer code;
    private String name;
    private String localizedName;
    private static Map<Integer, Region> regionByCode;

    private Region(Integer code, String name, String localizedName) {
        this.code = code;
        this.name = name;
        this.localizedName = localizedName;
    }

    static {
        regionByCode = new HashMap<>();
        Arrays.asList(Region.values()).stream()
                .forEach(region -> {
                    regionByCode.put(region.code, region);
                });
    }


    public static Region getRegionByCode(Integer code) {
        return regionByCode.get(code);
    }

    public Integer getCode() {
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
