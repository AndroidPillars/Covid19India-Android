package com.androidpillars.covid19.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gowtham.R on 2020-04-06.
 */
public class GetDistrictDetailResponse {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("districtData")
    @Expose
    private List<DistrictDatum> districtData = null;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DistrictDatum> getDistrictData() {
        return districtData;
    }

    public void setDistrictData(List<DistrictDatum> districtData) {
        this.districtData = districtData;
    }

    public class DistrictDatum {

        @SerializedName("district")
        @Expose
        private String district;
        @SerializedName("confirmed")
        @Expose
        private Integer confirmed;
        @SerializedName("lastupdatedtime")
        @Expose
        private String lastupdatedtime;
        @SerializedName("delta")
        @Expose
        private Delta delta;

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public Integer getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(Integer confirmed) {
            this.confirmed = confirmed;
        }

        public String getLastupdatedtime() {
            return lastupdatedtime;
        }

        public void setLastupdatedtime(String lastupdatedtime) {
            this.lastupdatedtime = lastupdatedtime;
        }

        public Delta getDelta() {
            return delta;
        }

        public void setDelta(Delta delta) {
            this.delta = delta;
        }

    }

    public class Delta {

        @SerializedName("confirmed")
        @Expose
        private Integer confirmed;

        public Integer getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(Integer confirmed) {
            this.confirmed = confirmed;
        }

    }



}

