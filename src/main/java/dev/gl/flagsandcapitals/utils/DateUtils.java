package dev.gl.flagsandcapitals.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author gl
 */
public class DateUtils {

    public static LocalDate convertDateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
