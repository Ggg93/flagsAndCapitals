package dev.gl.flagsandcapitals.utils;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author gl
 */
public class DateUtils {

    public static LocalDate convertDateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }

        return date.toLocalDate();
    }
    
    public static Date converLocalDateToDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        
        return Date.valueOf(localDate);
    }
}
