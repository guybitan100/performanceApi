package com.glassboxdigital.utils;

public class TimeOut {

    long startTimeMs = 0;
    long timeoutMin = 0;

    public TimeOut(int timeOutMin) {
        this.startTimeMs = System.currentTimeMillis();
        this.timeoutMin = timeOutMin * 60 * 1000;
    }

    public boolean isContinueRun() {
        return (System.currentTimeMillis() <= startTimeMs + timeoutMin);
    }

}
