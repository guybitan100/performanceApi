package com.glassboxdigital.utils;

public class TimeOut {

    long currentTime = 0;
    int timeoutMin = 0;

    public TimeOut(int timeOutMin) {
        this.currentTime = System.currentTimeMillis();
        this.timeoutMin = timeOutMin * 60 * 1000;
    }

    public boolean isTimeOutArrived() {
        return (currentTime <= System.currentTimeMillis() + timeoutMin);
    }

}
