package com.androidpillars.covid19;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpillars.covid19.Model.UpdateVersion;
import com.androidpillars.covid19.custom_adapter.DistrictScreenAdapter;
import com.androidpillars.covid19.custom_adapter.UpdateLogsScreenAdapter;
import com.androidpillars.covid19.pojo.GetDistrictDetailResponse;
import com.androidpillars.covid19.pojo.GetUpdateListResponse;
import com.androidpillars.covid19.retrofit.APIService;
import com.androidpillars.covid19.retrofit.ApiUtils;
import com.androidpillars.covid19.utils.CustomPopupDialog;
import com.androidpillars.covid19.utils.NetworkManager;
import com.androidpillars.covid_19.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gowtham.R on 2020-04-08.
 */
public class UpdateLogsActivity extends AppCompatActivity implements UpdateLogsScreenAdapter.ItemListener{

    private RecyclerView recyclerView;
    private APIService mAPIService;
    private RelativeLayout mBackBtnLayout;
    private TextView mTxtHeaderContent;
    private String mIntentValue;
    private List<GetUpdateListResponse> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmed_screen_activity);

        init();
    }

    private void init() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mIntentValue = bundle.getString("mIntentUpdateScreen");
        }

        mAPIService = ApiUtils.getAPIService();
        recyclerView = findViewById(R.id.recyclerView);
        mTxtHeaderContent = findViewById(R.id.toolbar_title);
        mTxtHeaderContent.setText(mIntentValue +" "+ "Updates");
        mBackBtnLayout = findViewById(R.id.linear_back_image);
        mBackBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (!NetworkManager.isInterNetConnection(UpdateLogsActivity.this)) {
            new CustomPopupDialog(UpdateLogsActivity.this);
        } else {
            fetchLiveData();
        }



    }

    public void fetchLiveData() {

        if (!NetworkManager.isInterNetConnection(UpdateLogsActivity.this)) {
            return;
        }

        Call<List<GetUpdateListResponse>> call = mAPIService.GetUpdateListResponseResult();
        call.enqueue(new Callback<List<GetUpdateListResponse>>() {
            @Override
            public void onResponse(Call<List<GetUpdateListResponse>> call, Response<List<GetUpdateListResponse>> response) {

                arrayList = new ArrayList();
                arrayList = response.body();
                Collections.reverse(arrayList);
                UpdateLogsScreenAdapter adapter = new UpdateLogsScreenAdapter(UpdateLogsActivity.this, arrayList, UpdateLogsActivity.this);
                recyclerView.setAdapter(adapter);

                GridLayoutManager manager = new GridLayoutManager(UpdateLogsActivity.this, 1, GridLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
            }

            @Override
            public void onFailure(Call<List<GetUpdateListResponse>> call, Throwable t) {

            }


        });

    }



    @Override
    public void onItemClick(GetUpdateListResponse item) {

    }
}
