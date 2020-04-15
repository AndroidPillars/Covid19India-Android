package com.androidpillars.covid19.custom_fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

import com.androidpillars.covid19.pojo.GetParentDataDetailResponse;
import com.androidpillars.covid19.retrofit.APIService;
import com.androidpillars.covid19.retrofit.ApiUtils;
import com.androidpillars.covid19.utils.CustomPopupDialog;
import com.androidpillars.covid19.utils.NetworkManager;
import com.androidpillars.covid_19.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Gowtham.R on 2020-04-07.
 */

public class FAQSelectionFragment extends BaseFragment {

    private static FAQSelectionFragment mUpdateTimeFragment;

    private Bitmap bitmap;


    public static FAQSelectionFragment newInstance(){
        if (mUpdateTimeFragment == null){
            mUpdateTimeFragment = new FAQSelectionFragment();
        }
        return mUpdateTimeFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_faq_selection;
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initData() {

    }


    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}
