package com.androidpillars.covid19.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gowtham.R on 2020-04-05.
 */

public class GetParentDataDetailResponse {

    @SerializedName("cases_time_series")
    @Expose
    private List<CasesTimeSeries> casesTimeSeries = null;
    @SerializedName("statewise")
    @Expose
    private List<Statewise> statewise = null;
    @SerializedName("tested")
    @Expose
    private List<Tested> tested = null;

    public List<CasesTimeSeries> getCasesTimeSeries() {
        return casesTimeSeries;
    }

    public void setCasesTimeSeries(List<CasesTimeSeries> casesTimeSeries) {
        this.casesTimeSeries = casesTimeSeries;
    }

    public List<Statewise> getStatewise() {
        return statewise;
    }

    public void setStatewise(List<Statewise> statewise) {
        this.statewise = statewise;
    }

    public List<Tested> getTested() {
        return tested;
    }

    public void setTested(List<Tested> tested) {
        this.tested = tested;
    }


    public class CasesTimeSeries {

        @SerializedName("dailyconfirmed")
        @Expose
        private String dailyconfirmed;
        @SerializedName("dailydeceased")
        @Expose
        private String dailydeceased;
        @SerializedName("dailyrecovered")
        @Expose
        private String dailyrecovered;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("totalconfirmed")
        @Expose
        private String totalconfirmed;
        @SerializedName("totaldeceased")
        @Expose
        private String totaldeceased;
        @SerializedName("totalrecovered")
        @Expose
        private String totalrecovered;

        public String getDailyconfirmed() {
            return dailyconfirmed;
        }

        public void setDailyconfirmed(String dailyconfirmed) {
            this.dailyconfirmed = dailyconfirmed;
        }

        public String getDailydeceased() {
            return dailydeceased;
        }

        public void setDailydeceased(String dailydeceased) {
            this.dailydeceased = dailydeceased;
        }

        public String getDailyrecovered() {
            return dailyrecovered;
        }

        public void setDailyrecovered(String dailyrecovered) {
            this.dailyrecovered = dailyrecovered;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTotalconfirmed() {
            return totalconfirmed;
        }

        public void setTotalconfirmed(String totalconfirmed) {
            this.totalconfirmed = totalconfirmed;
        }

        public String getTotaldeceased() {
            return totaldeceased;
        }

        public void setTotaldeceased(String totaldeceased) {
            this.totaldeceased = totaldeceased;
        }

        public String getTotalrecovered() {
            return totalrecovered;
        }

        public void setTotalrecovered(String totalrecovered) {
            this.totalrecovered = totalrecovered;
        }

    }

    public class Statewise {

        @SerializedName("active")
        @Expose
        private String active;
        @SerializedName("confirmed")
        @Expose
        private String confirmed;
        @SerializedName("deaths")
        @Expose
        private String deaths;
        @SerializedName("deltaconfirmed")
        @Expose
        private String deltaconfirmed;
        @SerializedName("deltadeaths")
        @Expose
        private String deltadeaths;
        @SerializedName("deltarecovered")
        @Expose
        private String deltarecovered;
        @SerializedName("lastupdatedtime")
        @Expose
        private String lastupdatedtime;
        @SerializedName("recovered")
        @Expose
        private String recovered;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("statecode")
        @Expose
        private String statecode;

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(String confirmed) {
            this.confirmed = confirmed;
        }

        public String getDeaths() {
            return deaths;
        }

        public void setDeaths(String deaths) {
            this.deaths = deaths;
        }

        public String getDeltaconfirmed() {
            return deltaconfirmed;
        }

        public void setDeltaconfirmed(String deltaconfirmed) {
            this.deltaconfirmed = deltaconfirmed;
        }

        public String getDeltadeaths() {
            return deltadeaths;
        }

        public void setDeltadeaths(String deltadeaths) {
            this.deltadeaths = deltadeaths;
        }

        public String getDeltarecovered() {
            return deltarecovered;
        }

        public void setDeltarecovered(String deltarecovered) {
            this.deltarecovered = deltarecovered;
        }

        public String getLastupdatedtime() {
            return lastupdatedtime;
        }

        public void setLastupdatedtime(String lastupdatedtime) {
            this.lastupdatedtime = lastupdatedtime;
        }

        public String getRecovered() {
            return recovered;
        }

        public void setRecovered(String recovered) {
            this.recovered = recovered;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStatecode() {
            return statecode;
        }

        public void setStatecode(String statecode) {
            this.statecode = statecode;
        }

    }

    public class Tested {

        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("testsconductedbyprivatelabs")
        @Expose
        private String testsconductedbyprivatelabs;
        @SerializedName("totalindividualstested")
        @Expose
        private String totalindividualstested;
        @SerializedName("totalpositivecases")
        @Expose
        private String totalpositivecases;
        @SerializedName("totalsamplestested")
        @Expose
        private String totalsamplestested;
        @SerializedName("updatetimestamp")
        @Expose
        private String updatetimestamp;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTestsconductedbyprivatelabs() {
            return testsconductedbyprivatelabs;
        }

        public void setTestsconductedbyprivatelabs(String testsconductedbyprivatelabs) {
            this.testsconductedbyprivatelabs = testsconductedbyprivatelabs;
        }

        public String getTotalindividualstested() {
            return totalindividualstested;
        }

        public void setTotalindividualstested(String totalindividualstested) {
            this.totalindividualstested = totalindividualstested;
        }

        public String getTotalpositivecases() {
            return totalpositivecases;
        }

        public void setTotalpositivecases(String totalpositivecases) {
            this.totalpositivecases = totalpositivecases;
        }

        public String getTotalsamplestested() {
            return totalsamplestested;
        }

        public void setTotalsamplestested(String totalsamplestested) {
            this.totalsamplestested = totalsamplestested;
        }

        public String getUpdatetimestamp() {
            return updatetimestamp;
        }

        public void setUpdatetimestamp(String updatetimestamp) {
            this.updatetimestamp = updatetimestamp;
        }

    }

}
