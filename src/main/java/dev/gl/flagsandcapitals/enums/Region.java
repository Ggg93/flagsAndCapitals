package dev.gl.flagsandcapitals.enums;

import dev.gl.flagsandcapitals.utils.Configuration;

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

    private Region(Integer code, String name, String localizedName) {
        this.code = code;
        this.name = name;
        this.localizedName = localizedName;
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
