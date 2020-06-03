package com.glassboxdigital.xls;

public class TrafficGenerator {

    private String date;
    private String concurrentSessions;
    private String sleepInterval;
    private String actualSessions;

    public TrafficGenerator(String date, String concurrentSessions, String sleepInterval, String actualSessions) {
        this.date = date;
        this.concurrentSessions = concurrentSessions;
        this.sleepInterval = sleepInterval;
        this.actualSessions = actualSessions;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConcurrentSessions() {
        return concurrentSessions;
    }

    public void setConcurrentSessions(String concurrentSessions) {
        this.concurrentSessions = concurrentSessions;
    }

    public String getSleepInterval() {
        return sleepInterval;
    }

    public void setSleepInterval(String sleepInterval) {
        this.sleepInterval = sleepInterval;
    }

    public String getActualSessions() {
        return actualSessions;
    }

    public void setActualSessions(String actualSessions) {
        this.actualSessions = actualSessions;
    }
}
