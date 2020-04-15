package com.androidpillars.covid19.custom_fragment;

import android.graphics.Bitmap;
import android.view.View;

import com.androidpillars.covid_19.R;


/**
 * Created by Gowtham.R on 2020-04-07.
 */

public class HelpLinksFragment extends BaseFragment {

    private static HelpLinksFragment mHelpLinksFragment;
    private Bitmap bitmap;

    public static HelpLinksFragment newInstance(){
        if (mHelpLinksFragment == null){
            mHelpLinksFragment = new HelpLinksFragment();
        }
        return mHelpLinksFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_helpful_links;
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
