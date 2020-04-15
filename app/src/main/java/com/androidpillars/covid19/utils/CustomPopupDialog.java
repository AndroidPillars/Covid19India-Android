package com.androidpillars.covid19.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidpillars.covid_19.R;

/**
 * Created by Gowtham.R on 2020-04-05.
 */


public class CustomPopupDialog implements View.OnClickListener {

    private Dialog mDialog;
    private Context mContext;
    private TextView mTxtContent;
    private Button mBtnOk;


    private String mtoastContent;

    public CustomPopupDialog(Context _context) {
        // TODO Auto-generated method stub
        mContext = _context;
        mDialog = new Dialog(_context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.custom_toast_popup_dialog);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.mDialog.setCanceledOnTouchOutside(false);
        uiInit();
        mDialog.show();
    }

    public void uiInit() {

        mTxtContent = mDialog.findViewById(R.id.message_content);
        mBtnOk = mDialog.findViewById(R.id.btn_ok);

        mBtnOk.setOnClickListener(this);
        mTxtContent.setText("The Internet Connection Appears \nto be Offline");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                mDialog.dismiss();
                break;
        }
    }

}
