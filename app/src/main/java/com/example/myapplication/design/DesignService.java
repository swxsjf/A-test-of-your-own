package com.example.myapplication.design;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 16:46
 * Describe: ${as}
 */
public interface DesignService {

    @Multipart
    @Headers({"User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0", "Connection: keep-alive"})
    @POST("upload")
    Call<RequestBody> upLoadPath(@Part("format") RequestBody smfile,@Part MultipartBody.Part file);

}
