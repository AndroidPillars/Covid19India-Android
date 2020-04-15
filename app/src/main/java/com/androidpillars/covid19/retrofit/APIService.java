package com.androidpillars.covid19.retrofit;


import com.androidpillars.covid19.pojo.GetDistrictDetailResponse;
import com.androidpillars.covid19.pojo.GetParentDataDetailResponse;
import com.androidpillars.covid19.pojo.GetUpdateListResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by Gowtham on 05-04-2020.
 */

public interface APIService {

    @GET("/data.json")
    Call<GetParentDataDetailResponse> GetParentDataDetailResponseResult();

    @GET("/v2/state_district_wise.json")
    Call<List<GetDistrictDetailResponse>> GetDistrictDetailResponseResult();

    @GET("/updatelog/log.json")
    Call<List<GetUpdateListResponse>> GetUpdateListResponseResult();


}
