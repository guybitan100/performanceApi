package com.glassboxdigital.xls;

public class ClickHouse {

    private String date;
    private String hour;
    private String sessionUuid;
    private String count;

    public ClickHouse(String date, String hour, String sessionUuid, String count) {
        this.date = date;
        this.hour = hour;
        this.sessionUuid = sessionUuid;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSessionUuid() {
        return sessionUuid;
    }

    public void setSessionUuid(String sessionUuid) {
        this.sessionUuid = sessionUuid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}

