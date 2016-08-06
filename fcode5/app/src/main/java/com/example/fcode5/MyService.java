package com.example.fcode5;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by wuzhuojun on 2016/8/2 0002.
 */
public class MyService extends Service {

    private Intent intent = new Intent("com.example.fcode5.RECEIVER");

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 启动后台线程 访问网络数据
     */
    public void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 处理 网络请求
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    urlConnection = (HttpURLConnection)url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    Scanner scanner = new Scanner(in).useDelimiter("\\A");
                    String result = scanner.hasNext() ? scanner.next() : "";

                    //发送Action为com.example.communication.RECEIVER的广播
                    intent.putExtra("value", result);
                    sendBroadcast(intent);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }

            }
        }).start();
        Log.d("MyService", "startDownload executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startDownload();
        return super.onStartCommand(intent, flags, startId);
    }

}
