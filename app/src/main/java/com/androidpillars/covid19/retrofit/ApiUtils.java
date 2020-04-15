package com.androidpillars.covid19.retrofit;


/**
 * Created by Gowtham on 05-04-2020.
 */


public class ApiUtils {


    public static final String BASE_URL = "https://api.covid19india.org";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
