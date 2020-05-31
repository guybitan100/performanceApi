package com.glassboxdigital.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("messages")
    @Expose
    private List<Object> messages = null;
    @SerializedName("dataSources")
    @Expose
    private List<String> dataSources = null;
    @SerializedName("schema")
    @Expose
    private String schema;
    @SerializedName("restrictedAttributes")
    @Expose
    private List<Object> restrictedAttributes = null;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<Object> getMessages() {
        return messages;
    }

    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }

    public List<String> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<String> dataSources) {
        this.dataSources = dataSources;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public List<Object> getRestrictedAttributes() {
        return restrictedAttributes;
    }

    public void setRestrictedAttributes(List<Object> restrictedAttributes) {
        this.restrictedAttributes = restrictedAttributes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
