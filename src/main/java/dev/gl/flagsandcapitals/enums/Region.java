package dev.gl.flagsandcapitals.enums;

import dev.gl.flagsandcapitals.utils.Configuration;

/**
 *
 * @author gl
 */
public enum Region {
    ALL(0, "regionAll"),
    ASIA(1, "regionAsia"),
    AFRICA(2, "regionAfrica"),
    NORTH_AMERICA(3, "regionNorthAmerica"),
    SOUTH_AMERICA(4, "regionSouthAmerica"),
    EUROPE(5, "regionEurope"),
    AUSTRALIA(6, "regionAustralia");
    
    private Integer code;
    private String name;

    private Region(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return Configuration.getResourceBundle().getString(name);
    }
    
}
