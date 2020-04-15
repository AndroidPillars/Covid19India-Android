package com.androidpillars.covid19.custom_fragment;

import android.content.Intent;
import android.graphics.Bitmap;

import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpillars.covid19.ConfirmedScreenActivity;
import com.androidpillars.covid19.Model.DashBoardScreenModel;
import com.androidpillars.covid19.UpdateLogsActivity;
import com.androidpillars.covid19.custom_adapter.DashBoardScreenAdapter;
import com.androidpillars.covid19.custom_adapter.DashBoradScreenPagerAdapter;
import com.androidpillars.covid19.pojo.GetParentDataDetailResponse;
import com.androidpillars.covid19.retrofit.APIService;
import com.androidpillars.covid19.retrofit.ApiUtils;
import com.androidpillars.covid19.utils.CustomPopupDialog;
import com.androidpillars.covid19.utils.NetworkManager;
import com.androidpillars.covid_19.R;
import com.asksira.loopingviewpager.LoopingViewPager;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gowtham.R on 2020-04-07.
 */
public class HomeSelectionFragment extends BaseFragment implements DashBoardScreenAdapter.ItemListener {

    private static HomeSelectionFragment mHomeSelectionFragment;

    private RecyclerView recyclerView;
    private DashBoradScreenPagerAdapter mAdapter;
    private LoopingViewPager viewPager;
    private PageIndicatorView indicatorView;
    private ArrayList arrayList;
    private Bitmap bitmap;

    private APIService mAPIService;

    private ArrayList<String> createDummyItems() {
        ArrayList<String> items = new ArrayList<>();
        items.add(0, "Avoid going out during the lockdown. Help break the chain of spread.");
        items.add(1, "Our brothers from the North-East are just as Indian as you! Help everyone during this crisis ❤️");
        items.add(2, "Stand against FAKE news and illegit WhatsApp forwards! Do NOT ❌ forward a message until you verify the content it contains.");
        items.add(3, "If you have any medical queries, reach out to your state helpline, district administration or trusted doctors!");
        items.add(4, "There is no evidence that hot weather will stop the virus! You can! Stay home, stay safe.");
        items.add(5, "Plan ahead! Take a minute and check how much supplies you have at home. Planning lets you buy exactly what you need.");
        items.add(6, "Be considerate. While buying essentials remember that you need to share with 130 crore fellow citizens!");
        return items;
    }


    public static HomeSelectionFragment newInstance() {

        if (mHomeSelectionFragment == null) {
            mHomeSelectionFragment = new HomeSelectionFragment();
        }
        return mHomeSelectionFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_selection;
    }



    @Override
    public void initData() {

    }


    @Override
    public void initView(View rootView) {
        mAPIService = ApiUtils.getAPIService();
        recyclerView = rootView.findViewById(R.id.recyclerView);
        mAdapter = new DashBoradScreenPagerAdapter(getActivity(), createDummyItems(), true);
        viewPager = rootView.findViewById(R.id.viewpager);
        indicatorView = rootView.findViewById(R.id.indicator);
        viewPager.setAdapter(mAdapter);
        indicatorView.setCount(viewPager.getIndicatorCount());
        viewPager.setIndicatorPageChangeListener(new LoopingViewPager.IndicatorPageChangeListener() {
            @Override
            public void onIndicatorProgress(int selectingPosition, float progress) {
                indicatorView.setProgress(selectingPosition, progress);
            }

            @Override
            public void onIndicatorPageChange(int newIndicatorPosition) {

            }
        });

        if (!NetworkManager.isInterNetConnection(getActivity())) {
            new CustomPopupDialog(getActivity());
        } else {
            fetchLiveData();
        }
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
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
                arrayList.add(new DashBoardScreenModel(response.body().getStatewise().get(0).getConfirmed() + " " + "[+" + response.body().getStatewise().get(0).getDeltaconfirmed() + "]", "Confirmed", "#721b65"));
                arrayList.add(new DashBoardScreenModel("Updates", "Recent", "#10375c"));
                arrayList.add(new DashBoardScreenModel(response.body().getStatewise().get(0).getActive(), "Active", "#b80d57"));
                arrayList.add(new DashBoardScreenModel(response.body().getStatewise().get(0).getRecovered() + " " + "[+" + response.body().getStatewise().get(0).getDeltarecovered() + "]", "Recovered", "#f8615a"));
                arrayList.add(new DashBoardScreenModel(response.body().getStatewise().get(0).getDeaths() + " " + "[+" + response.body().getStatewise().get(0).getDeltadeaths() + "]", "Death", "#eb4559"));


                DashBoardScreenAdapter adapter = new DashBoardScreenAdapter(getActivity(), arrayList, HomeSelectionFragment.this);
                recyclerView.setAdapter(adapter);

                GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
            }

            @Override
            public void onFailure(Call<GetParentDataDetailResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }


    @Override
    public void onItemClick(DashBoardScreenModel item) {

        if (!NetworkManager.isInterNetConnection(getActivity())) {
            new CustomPopupDialog(getActivity());
        } else {
            if(item.text.equalsIgnoreCase("Updates")){
                Intent mIntent = new Intent(getActivity(), UpdateLogsActivity.class);
                mIntent.putExtra("mIntentUpdateScreen", item.desc);
                startActivity(mIntent);
            }else {
                Intent mIntent = new Intent(getActivity(), ConfirmedScreenActivity.class);
                mIntent.putExtra("mIntentDashBoardScreen", item.desc);
                startActivity(mIntent);
            }
        }

    }
}