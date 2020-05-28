
package com.glassboxdigital.http.models;

import java.util.List;

public class Sessions {

    private TimeFrame timeFrame;
    private Integer limit;
    private UniqueCount uniqueCount;
    private List<Step> steps = null;
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
