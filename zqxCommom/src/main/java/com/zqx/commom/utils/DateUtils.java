package com.zqx.commom.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date tranferTimeBy(String time, String formatDate){
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        Date date = null;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
