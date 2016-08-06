package com.example.fcode10;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mTopicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        new MyAsyncTask().execute();
    }

    /**
     * 启动线程访问网络
     */
    private class MyAsyncTask extends AsyncTask<String, Integer, String> {

        //doInBackground方法内部执行后台任务,不可在此方法内修改UI
        @Override
        protected String doInBackground(String... params) {
            String strContent = null;
            try {
                String param = "sortby=new&boardId=2&pageSize=20&page=1&accessToken=c29b8a10fd19ce6c0b8f15a1acd28&accessSecret=26ff9822a3f0b9fa33f53a8d0f8ce";
                strContent = executePost("http://beta.www.coolxap.com/openapi/app/web/index.php?r=forum/topiclist", param);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return strContent;
        }

        //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
        @Override
        protected void onPostExecute(String result) {
            if(null != result)
            {
                Gson gson = new Gson();
                TopicJson topicJson = gson.fromJson(result, TopicJson.class);

                //设置recycler的内容
                RecyclerViewAdapter listViewAdapter = new RecyclerViewAdapter(MainActivity.this);
                listViewAdapter.addData(topicJson.getList());
                mTopicList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                mTopicList.setAdapter(listViewAdapter);
            }
        }
    }

    OkHttpClient client = new OkHttpClient();
    /**
     * Post 网络请求
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    String executePost(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
