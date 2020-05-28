
package com.glassboxdigital.http.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeFrame {
    public TimeFrame() {
    }

    public TimeFrame(Long from, Long till) {
        this.from = from;
        this.till = till;
    }

    @SerializedName("from")
    @Expose
    private Long from;
    @SerializedName("till")
    @Expose
    private Long till;

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTill() {
        return till;
    }

    public void setTill(Long till) {
        this.till = till;
    }

}
