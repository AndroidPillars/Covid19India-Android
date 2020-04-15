package com.androidpillars.covid19.custom_fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.androidpillars.covid19.Model.DashBoardScreenModel;
import com.androidpillars.covid19.custom_adapter.DashBoardScreenAdapter;
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

public class UpdateTimeFragment extends BaseFragment {

    private static UpdateTimeFragment mUpdateTimeFragment;

    private ArrayList arrayList;
    private Bitmap bitmap;

    private TextView mTxtActive;
    private TextView mTxtRecovered;
    private TextView mTxtDeath;
    private TextView mTxtTime;

    private APIService mAPIService;

    public static UpdateTimeFragment newInstance(){
        if (mUpdateTimeFragment == null){
            mUpdateTimeFragment = new UpdateTimeFragment();
        }
        return mUpdateTimeFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_update_time;
    }

    @Override
    public void initView(View rootView) {

        mAPIService = ApiUtils.getAPIService();

        mTxtActive = rootView.findViewById(R.id.txtActive);
        mTxtRecovered = rootView.findViewById(R.id.txtRecovered);
        mTxtDeath = rootView.findViewById(R.id.txtDeath);
        mTxtTime = rootView.findViewById(R.id.txtUpdateTime);

        if (!NetworkManager.isInterNetConnection(getActivity())) {
            new CustomPopupDialog(getActivity());
        } else {
            fetchLiveData();
        }
    }

    @Override
    public void initData() {

    }

    public void fetchLiveData() {

        if (!NetworkManager.isInterNetConnection(getActivity())) {
            return;
        }

        Call<GetParentDataDetailResponse> call = mAPIService.GetParentDataDetailResponseResult();
        call.enqueue(new Callback<GetParentDataDetailResponse>() {
            @Override
            public void onResponse(Call<GetParentDataDetailResponse> call, Response<GetParentDataDetailResponse> response) {

                arrayList = new ArrayList();

                mTxtActive.setText(response.body().getStatewise().get(0).getActive());
                mTxtRecovered.setText(response.body().getStatewise().get(0).getRecovered() + " " + "[+" + response.body().getStatewise().get(0).getDeltarecovered() + "]" );
                mTxtDeath.setText(response.body().getStatewise().get(0).getDeaths() + " " + "[+" + response.body().getStatewise().get(0).getDeltadeaths() + "]");
                mTxtTime.setText(response.body().getStatewise().get(0).getLastupdatedtime());


            }

            @Override
            public void onFailure(Call<GetParentDataDetailResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}
