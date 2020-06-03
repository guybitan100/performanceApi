package com.glassboxdigital.xls;

public class SessionPipelineMetrics {

    private String date;
    private String key;
    private String count;
    private String exceptionCount;
    private String totalTimeNanos;
    private String squaresOfTime;
    private String averageMS;
    private String std;
    private String maxMS;
    private String timeLength;

    public SessionPipelineMetrics(String date, String key, String count, String exceptionCount, String totalTimeNanos, String squaresOfTime, String averageMS, String std, String maxMS, String timeLength) {
        this.date = date;
        this.key = key;
        this.count = count;
        this.exceptionCount = exceptionCount;
        this.totalTimeNanos = totalTimeNanos;
        this.squaresOfTime = squaresOfTime;
        this.averageMS = averageMS;
        this.std = std;
        this.maxMS = maxMS;
        this.timeLength = timeLength;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getExceptionCount() {
        return exceptionCount;
    }

    public void setExceptionCount(String exceptionCount) {
        this.exceptionCount = exceptionCount;
    }

    public String getTotalTimeNanos() {
        return totalTimeNanos;
    }

    public void setTotalTimeNanos(String totalTimeNanos) {
        this.totalTimeNanos = totalTimeNanos;
    }

    public String getSquaresOfTime() {
        return squaresOfTime;
    }

    public void setSquaresOfTime(String squaresOfTime) {
        this.squaresOfTime = squaresOfTime;
    }

    public String getAverageMS() {
        return averageMS;
    }

    public void setAverageMS(String averageMS) {
        this.averageMS = averageMS;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getMaxMS() {
        return maxMS;
    }

    public void setMaxMS(String maxMS) {
        this.maxMS = maxMS;
    }

    public String getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength;
    }
}

