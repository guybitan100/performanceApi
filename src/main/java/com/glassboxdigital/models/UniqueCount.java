package com.glassboxdigital.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UniqueCount {

    @SerializedName("field")
    @Expose
    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
