package com.androidpillars.covid19.Model;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

/**
 * Created by Gowtham.R on 2020-04-08.
 */
public class UpdateVersion implements Serializable {

    @Exclude
    private String mDate;
    private String mVersion;
    private String mSize;
    private String mDescription;

    public UpdateVersion(){

    }

    public UpdateVersion(String mDate, String mVersion, String mSize, String mDescription) {
        this.mDate = mDate;
        this.mVersion = mVersion;
        this.mSize = mSize;
        this.mDescription = mDescription;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmVersion() {
        return mVersion;
    }

    public void setmVersion(String mVersion) {
        this.mVersion = mVersion;
    }

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

}
