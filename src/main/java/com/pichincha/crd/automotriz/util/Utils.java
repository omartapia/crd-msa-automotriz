package com.pichincha.crd.automotriz.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }
}
