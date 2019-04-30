package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.myapplication.main.database.TangPoetryDao;
import com.example.myapplication.main.MainBean;
import com.example.myapplication.main.MainRecyclerAdapter;
import com.example.myapplication.main.MainService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private long currentTime = 0;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainRecyclerAdapter adapter;

    private String url = "https://api.apiopen.top/getTangPoetry";
    private int page = 1;
    private int count = 5;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what != 100){
                Response<MainBean> response = (Response<MainBean>) msg.obj;
                List<MainBean.ResultBean> result = response.body().getResult();
                adapter.refresh(result);

                //存入数据库
                ContentValues values = new ContentValues();
               for (int i = 0; i < result.size(); i++){
                   values.put("title"+i,result.get(i).getTitle());
                   values.put("content"+i,result.get(i).getContent());
                   values.put("authors"+i,result.get(i).getAuthors());
               }
                TangPoetryDao.insert(values);

            }
            else {
                List<MainBean.ResultBean> list = new ArrayList<>();
                Cursor cursor = TangPoetryDao.query();
                int i = 0;
                while (cursor.moveToNext()){
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String content = cursor.getString(cursor.getColumnIndex("content"));
                    String authors = cursor.getString(cursor.getColumnIndex("authors"));
                    list.get(i).setTitle(title);
                    list.get(i).setTitle(content);
                    list.get(i).setTitle(authors);
                    i++;
                }
                adapter.refresh(list);
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData(url,page+"",count+"");
        //下拉刷新数据
        initSwipeRefresh();

    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            int i = 1;
            int j = 5;
            @Override
            public void onRefresh() {
                page = i += 1;
                count = j += 5;
                initData(url,page+"",count+"");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initData(final String url, final String page, final String count) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.apiopen.top/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    MainService service = retrofit.create(MainService.class);
                    Call<MainBean> call = service.getTangPoetry(url,page,count);

                    Response<MainBean> response = call.execute();
                    Message message = new Message();
                    message.obj = response;

                    if (response.isSuccessful()){
                        handler.sendMessage(message);
                    }
                    else{
                        handler.sendEmptyMessage(100);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        long lastTime = System.currentTimeMillis();
        if (lastTime - currentTime > 3000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            currentTime = lastTime;
            return;
        } else {
            super.onBackPressed();
        }

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        adapter = new MainRecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }
}
