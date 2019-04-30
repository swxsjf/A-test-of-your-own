package com.example.myapplication.main;

import com.example.myapplication.main.MainBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 14:09
 * Describe: ${as}
 */
public interface MainService {

    @GET
    Call<MainBean> getTangPoetry(@Url() String url, @Query("page") String page, @Query("count") String count);

}
