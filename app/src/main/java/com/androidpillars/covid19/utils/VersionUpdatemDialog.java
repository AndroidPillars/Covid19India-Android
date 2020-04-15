package com.androidpillars.covid19.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.androidpillars.covid_19.R;


/**
 * Created by Gowtham on on 2020-04-05.
 */

public class VersionUpdatemDialog implements View.OnClickListener {

    private Dialog mDialog;
    Context mContext;
    private TextView mVersionTxt;
    private TextView mDateTxt;
    private TextView mDescriptionTxt;
    private TextView mUnderMaintenanceTxt;
    private Button mUploadBtn;

    private String mDate;
    private String mVersion;
    private String mSize;
    private String mDescription;

    public VersionUpdatemDialog(Context _context, String _date, String _version, String _size,
                                String _description) {

        mDialog = new Dialog(_context, android.R.style.Theme_Black_NoTitleBar);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.version_update);
        mContext = _context;
        mDate = _date;
        mVersion = _version;
        mSize = _size;
        mDescription = _description;

        uiInit();
        mDialog.setCancelable(false);
        mDialog.show();
        setValueToView();

    }

    void uiInit() {


        mVersionTxt = mDialog.findViewById(R.id.txt_version_size_version_layout);
        mDateTxt = mDialog.findViewById(R.id.txt_version_date_version_layout);
        mDescriptionTxt = mDialog.findViewById(R.id.txt_description_version_layout);
        mUploadBtn = mDialog.findViewById(R.id.txt_download_update_version_layout);
        mUnderMaintenanceTxt = mDialog.findViewById(R.id.txt_app_under_maintenance);
        mUploadBtn.setOnClickListener(this);

        if(!mDescription.equalsIgnoreCase("Under-Maintenance")){
            mUnderMaintenanceTxt.setVisibility(View.GONE);
            mVersionTxt.setVisibility(View.VISIBLE);
            mDescriptionTxt.setVisibility(View.VISIBLE);
            mDateTxt.setVisibility(View.VISIBLE);
            mUploadBtn.setVisibility(View.VISIBLE);
        }else {
            mUnderMaintenanceTxt.setVisibility(View.VISIBLE);
            mVersionTxt.setVisibility(View.GONE);
            mDescriptionTxt.setVisibility(View.GONE);
            mDateTxt.setVisibility(View.GONE);
            mUploadBtn.setVisibility(View.GONE);
        }

    }


    void setValueToView() {
        mDateTxt.setText(mDate);
        mVersionTxt.setText(Html.fromHtml("<b>" + "Version :" + " " + mVersion + ", " +
                mSize + "</b> "));

        mDescriptionTxt.setText(mDescription);
    }

    @Override
    public void onClick(View v) {

        final String appPackageName = mContext.getPackageName();

        try {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                    .parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                    .parse("https://play.google.com/store/apps/details?id="
                            + appPackageName)));
        }
    }
}

