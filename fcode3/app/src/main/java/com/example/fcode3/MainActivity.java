package com.example.fcode3;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @BindView(R.id.text_show_content)
    TextView mTvContent;

    @OnClick(R.id.btn_get_content) void submit() {
        mTvContent.setText("按钮点击了！");

        /**
         * 由于请求网络数据不能在主线程中执行（属于耗时操作，会报错），因此启动子线程执行
         */
        new Thread(networkTask).start();
    }

    @OnClick(R.id.btn_get_content2) void submit2() {
        MyAsyncTask mTask = new MyAsyncTask();
        mTask.execute("http://www.sina.com");
    }




    /**
     * 实现线程的第一种方式：
     * Thread属于T，Runnable属于I，Task属于E
     * 执行网络请求
     */
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            String result = GetWebData("http://www.baidu.com");
            handleResult(result);

        }
    };

    private String GetWebData(String strUrl)
    {
        HttpURLConnection urlConnection = null;
        String result = "";
        try {
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection)url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result  = readInStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return result;
    }

    private String readInStream(InputStream in) {
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    /**
     * 由于子线程不能直接更新UI元素，因此需要通过 Handle 向主线程发消息。
     * 主线程从消息队列中取得消息，更新UI元素
     * Google这样设计的好处在于不需要处理线程安全问题，提高APP的性能
     * @param result
     */
    private void handleResult(String result) {
        Message msg = new Message();
        Bundle b = new Bundle();// 存放数据
        b.putString("value", result);
        msg.setData(b);

        MainActivity.this.myHandler.sendMessage(msg); // 向Handler发送消息，更新UI
    }

    /**
     * 接收到子线程投递过来的消息，进行处理
     */
    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            // TODO
            // UI界面的更新等相关操作
            mTvContent.setText(val);
        }
    };


    /**
     * 实现线程的第二种方法
     */
    private class MyAsyncTask extends AsyncTask<String, Integer, String> {

        //doInBackground方法内部执行后台任务,不可在此方法内修改UI
        @Override
        protected String doInBackground(String... params) {
            return GetWebData(params[0]);
        }

        //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
        @Override
        protected void onPostExecute(String result) {
            mTvContent.setText(result);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
