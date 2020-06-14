package com.glassboxdigital.utils;

public class TimeOut {

    long startTimeMs = 0;
    int timeoutMin = 0;

    public TimeOut(int timeOutMin) {
        this.startTimeMs = System.currentTimeMillis();
        this.timeoutMin = timeOutMin * 60 * 1000;
    }

    public boolean isTimeOutArrived() {
        return (startTimeMs <= System.currentTimeMillis() + timeoutMin);
    }

}
