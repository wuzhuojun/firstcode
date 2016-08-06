package com.example.fcode4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
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
 * 注意四大组件都是由Android底层调用的，它通过 manifest.xml 获取需要被调用的组件类。
 * 通过IoC的原理调用子类的具体实现，大量 on***() 的方法就是系统调用子类的具体实现。
 *
 * 有人提问，既然主线程可以启动多线程能解决后台执行任务的功能为什么还要用Service呢？
 * 原因在于Service比Activity的优先级更高，当系统资源不足时，GC时Activity下的多线程有可能会被回收，
 * 而Service不会被轻易回收，能继续保持运行。
 *
 */
public class MyService extends Service {

    /**
     * Service 与 Activity之间的通信属于跨进程的通信，若两者在同一个 application 是属于 近进程通信，Service的生命周期服从进程的生命周期。
     * RemoteService 与 Activity之间的通信属于远进程之间的通信，它独立于 application 的运行。也就是属于IPC，可以通过AIDL来进行处理。
     *
     * 而Activity是通过 binder来访问 Service
     *
     */
    private DownloadBinder mBinder = new DownloadBinder();


    /**
     * 当主线程启动Service时，系统AMS（ActivityManagerService）会调用 onBind，将 binder的具体实现传递给 Activity
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class DownloadBinder extends Binder {

        /**
         * Activity 通过 binder 获取到 Service的引用
         * @return
         */
        public MyService getService(){
            return MyService.this;
        }
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

                    onUpdateUIListener.OnUpdateUIListener(result);
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

    /**
     * 更新UI
     */
    OnUpdateUIListener onUpdateUIListener;
    public void setOnUpdateUIListener(OnUpdateUIListener onUpdateUIListener)
    {
        this.onUpdateUIListener = onUpdateUIListener;
    }

}
