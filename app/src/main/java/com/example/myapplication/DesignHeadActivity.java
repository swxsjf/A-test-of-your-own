package com.example.myapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.myapplication.design.DesignService;
import com.example.myapplication.design.MyHeadImageView;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DesignHeadActivity extends AppCompatActivity implements View.OnClickListener {

    private MyHeadImageView myHeadImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

        initView();

    }

    private void initView() {
        myHeadImageView = (MyHeadImageView) findViewById(R.id.myHeadImageView);
        myHeadImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            Uri uri = data.getData();
            ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            cursor.moveToFirst();
            String str = cursor.getString(cursor.getColumnIndex("_data"));
            File file = new File(str);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getName());
            myHeadImageView.setImageBitmap(bitmap);

            //上传图片
            upImageData(str);
            cursor.close();
        }
    }

    public void upImageData(String str) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sm.ms/doc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DesignService service = retrofit.create(DesignService.class);

        MediaType mediaType = MediaType.parse("multipart/form-data");
        RequestBody requestBody = RequestBody.create(mediaType, "json");

        MediaType formType = MediaType.parse("application/otcet-stream");
        File file = new File(str);
        Log.e("aaa","file.getName():"+file.getName());
        RequestBody fileBody = RequestBody.create(formType,str);
        String name = null;

        try {
            name = URLEncoder.encode(file.getName(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("smfile",name,fileBody);
        Call<RequestBody> call = service.upLoadPath(requestBody,filePart);
        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                Log.e("aaa", response.message());
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {

            }
        });

    }

}
