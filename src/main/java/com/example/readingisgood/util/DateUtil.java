package com.example.readingisgood.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DateUtil {

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Date convert(String dateStr) {
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convert(Date date) {
        return formatter.format(date);
    }

    public static HashMap<String, Date> getDatesFromYear(Integer year) {
        HashMap<String, Date> dates = new HashMap<>();

        Calendar calStart = Calendar.getInstance();
        calStart.set(Calendar.YEAR, year);
        calStart.set(Calendar.DAY_OF_YEAR, 1);
        dates.put("start", calStart.getTime());

        Calendar calEnd = Calendar.getInstance();
        calEnd.set(Calendar.YEAR, year);
        calEnd.set(Calendar.MONTH, 11);
        calEnd.set(Calendar.DAY_OF_MONTH, 31);
        dates.put("end", calEnd.getTime());
        return dates;
    }


}
