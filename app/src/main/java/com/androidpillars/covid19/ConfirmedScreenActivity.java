package com.androidpillars.covid19;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpillars.covid19.custom_adapter.ConfirmedScreenAdapter;
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
public class ConfirmedScreenActivity extends AppCompatActivity implements ConfirmedScreenAdapter.ItemListener {

    private RecyclerView recyclerView;
    private APIService mAPIService;
    private RelativeLayout mBackBtnLayout;
    private TextView mTxtHeaderContent;
    private String mIntentValue;
    private List<GetParentDataDetailResponse.Statewise> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmed_screen_activity);

        init();
    }

    private void init() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mIntentValue = bundle.getString("mIntentDashBoardScreen");
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

        if (!NetworkManager.isInterNetConnection(ConfirmedScreenActivity.this)) {
            new CustomPopupDialog(ConfirmedScreenActivity.this);
        } else {
            fetchLiveData();
        }


    }

    public void fetchLiveData() {

        if (!NetworkManager.isInterNetConnection(ConfirmedScreenActivity.this)) {
            return;
        }

        Call<GetParentDataDetailResponse> call = mAPIService.GetParentDataDetailResponseResult();
        call.enqueue(new Callback<GetParentDataDetailResponse>() {
            @Override
            public void onResponse(Call<GetParentDataDetailResponse> call, Response<GetParentDataDetailResponse> response) {


                arrayList = new ArrayList();
                arrayList = response.body().getStatewise();
                arrayList.remove(0);


                ConfirmedScreenAdapter adapter = new ConfirmedScreenAdapter(ConfirmedScreenActivity.this, arrayList, ConfirmedScreenActivity.this);
                recyclerView.setAdapter(adapter);

                GridLayoutManager manager = new GridLayoutManager(ConfirmedScreenActivity.this, 1, GridLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
            }

            @Override
            public void onFailure(Call<GetParentDataDetailResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    public void onItemClick(GetParentDataDetailResponse.Statewise item) {
        if (!NetworkManager.isInterNetConnection(ConfirmedScreenActivity.this)) {
            new CustomPopupDialog(ConfirmedScreenActivity.this);
        } else {
            Intent mIntent = new Intent(ConfirmedScreenActivity.this, DistrictScreenActivity.class);
            mIntent.putExtra("mIntentConfirmedScreen", item.getState());
            startActivity(mIntent);
        }
    }
}
