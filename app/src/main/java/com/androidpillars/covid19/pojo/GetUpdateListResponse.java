package com.androidpillars.covid19.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gowtham.R on 2020-04-08.
 */
public class GetUpdateListResponse {

    @SerializedName("update")
    @Expose
    private String update;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }


}
