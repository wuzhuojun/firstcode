package com.example.fcode6;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by wuzhuojun on 2016/8/2 0002.
 */
public class MyService extends Service {

    boolean flag;
    private final static String TAG = "MyService";
    private static final int NOTIFY_ID = 0;
    private NotificationManager mNotificationManager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "++MainService onCreate++");

/*        Notification no = new Notification(R.mipmap.ic_launcher, "有通知到来", System.currentTimeMillis());
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
//        no.setLatestEventInfo(this, "AIDLDemo", "running", pi);
//        startForeground(1, no);

        no.contentIntent = pi;
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFY_ID, no);*/


        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new Notification.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("title")
                .setContentText("describe")
                .setContentIntent(pi)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .build();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Bundle bundle = intent.getExtras();
        flag = bundle.getBoolean("flag");
        System.out.println(flag);
        return ms;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "++MainService onDestroy++");
        flag = false;
    }

    IMyAidlInterface.Stub ms = new IMyAidlInterface.Stub() {
        @Override
        public void DownLoad() throws RemoteException {

            new Thread(new Runnable() {
                int i = 0;

                @Override
                public void run() {
                    //未达到线程条件，会一直在后台运行，就算服务已经关闭
                    while (flag) {

                        try {
                            i++;
                            System.out.println("i的值是" + i);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("退出服务");
                }
            }).start();
        }
    };
}
