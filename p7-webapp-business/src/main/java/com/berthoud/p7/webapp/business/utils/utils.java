package com.berthoud.p7.webapp.business.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class utils {


    /**
     * Formats a LocalDate in a string formatted as follows: dd/MM/yy
     * @param localDate
     * @return
     */
    public static String localDateFormater (LocalDate localDate){
        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));

    }

}
