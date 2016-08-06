
package com.example.fcode7;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_show_data)
    TextView mTextView;

    @OnClick(R.id.btn_okhttp_get) void getHttpData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String strContent = executeGet("http://www.baidu.com");

                    Message msg = new Message();
                    Bundle b = new Bundle();
                    b.putString("value", strContent);
                    msg.setData(b);
                    myHandler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @OnClick(R.id.btn_okhttp_post) void postHttpData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String param = "sortby=new&boardId=2&pageSize=20&page=1&accessToken=c29b8a10fd19ce6c0b8f15a1acd28&accessSecret=26ff9822a3f0b9fa33f53a8d0f8ce";
                    String strContent = executePost("http://beta.www.coolxap.com/openapi/app/web/index.php?r=forum/topiclist", param);

                    Message msg = new Message();
                    Bundle b = new Bundle();
                    b.putString("value", strContent);
                    msg.setData(b);
                    myHandlerPost.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    Handler myHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            // TODO
            // UI界面的更新等相关操作
            mTextView.setText(val);
        }
    };

    Handler myHandlerPost = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            // TODO
            // UI界面的更新等相关操作
            Gson gson = new Gson();
            TopicJson topicJson = gson.fromJson(val, TopicJson.class);

            mTextView.setText(topicJson.getHead().getErrInfo());
        }
    };

    OkHttpClient client = new OkHttpClient();

    /**
     * Get 网络请求
     */
    String executeGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


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
