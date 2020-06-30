package com.glassboxdigital.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {

    public static String getCurrentTimeDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static int getHour() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}