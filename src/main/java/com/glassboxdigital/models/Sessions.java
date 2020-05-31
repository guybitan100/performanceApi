package com.glassboxdigital.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sessions {

    @SerializedName("timeFrame")
    @Expose
    private TimeFrame timeFrame;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("uniqueCount")
    @Expose
    private UniqueCount uniqueCount;
    @SerializedName("steps")
    @Expose
    private List<Step> steps = null;
    @SerializedName("filters")
    @Expose
    private Filters filters;

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public UniqueCount getUniqueCount() {
        return uniqueCount;
    }

    public void setUniqueCount(UniqueCount uniqueCount) {
        this.uniqueCount = uniqueCount;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

}
