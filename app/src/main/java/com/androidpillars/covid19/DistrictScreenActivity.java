package com.androidpillars.covid19;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpillars.covid19.custom_adapter.ConfirmedScreenAdapter;
import com.androidpillars.covid19.custom_adapter.DistrictScreenAdapter;
import com.androidpillars.covid19.pojo.GetDistrictDetailResponse;
import com.androidpillars.covid19.pojo.GetParentDataDetailResponse;
import com.androidpillars.covid19.retrofit.APIService;
import com.androidpillars.covid19.retrofit.ApiUtils;
import com.androidpillars.covid19.utils.CustomPopupDialog;
import com.androidpillars.covid19.utils.NetworkManager;
import com.androidpillars.covid_19.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gowtham.R on 2020-04-06.
 */
public class DistrictScreenActivity extends AppCompatActivity implements DistrictScreenAdapter.ItemListener {

    private RecyclerView recyclerView;
    private APIService mAPIService;
    private RelativeLayout mBackBtnLayout;
    private TextView mTxtHeaderContent;
    private String mIntentValue;
    private List<GetDistrictDetailResponse> arrayList;
    private List<GetDistrictDetailResponse.DistrictDatum> mDistrictData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmed_screen_activity);

        init();
    }

    private void init() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mIntentValue = bundle.getString("mIntentConfirmedScreen");
        }

        mAPIService = ApiUtils.getAPIService();
        recyclerView = findViewById(R.id.recyclerView);
        mTxtHeaderContent = findViewById(R.id.toolbar_title);
        mTxtHeaderContent.setText(mIntentValue);
        mBackBtnLayout = findViewById(R.id.linear_back_image);
        mBackBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (!NetworkManager.isInterNetConnection(DistrictScreenActivity.this)) {
            new CustomPopupDialog(DistrictScreenActivity.this);
        } else {
            fetchLiveData();
        }



    }

    public void fetchLiveData() {

        if (!NetworkManager.isInterNetConnection(DistrictScreenActivity.this)) {
            return;
        }

            Call<List<GetDistrictDetailResponse>> call = mAPIService.GetDistrictDetailResponseResult();
            call.enqueue(new Callback<List<GetDistrictDetailResponse>>() {
                @Override
                public void onResponse(Call<List<GetDistrictDetailResponse>> call, Response<List<GetDistrictDetailResponse>> response) {

                    arrayList = new ArrayList();
                    mDistrictData = new ArrayList<>();
                    arrayList = response.body();

                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i).getState().equalsIgnoreCase(mIntentValue)) {
                            mDistrictData = arrayList.get(i).getDistrictData();
                        }
                    }


                    DistrictScreenAdapter adapter = new DistrictScreenAdapter(DistrictScreenActivity.this, mDistrictData, DistrictScreenActivity.this);
                    recyclerView.setAdapter(adapter);

                    GridLayoutManager manager = new GridLayoutManager(DistrictScreenActivity.this, 1, GridLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);
                }

                @Override
                public void onFailure(Call<List<GetDistrictDetailResponse>> call, Throwable t) {

                }


            });

    }


    @Override
    public void onItemClick(GetDistrictDetailResponse.DistrictDatum item) {

    }
}
