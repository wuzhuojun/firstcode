package com.example.fcode6;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface myServiceAIDL;
    private Intent binderIntent;
    private final static boolean create_flag=true;
    private final static boolean destory_flag=false;
    private final static String TAG="MainActivity";

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myServiceAIDL = IMyAidlInterface.Stub.asInterface(service);
            try {
                //通过AIDL远程调用
                Log.d(TAG,"++start download++");
                myServiceAIDL.DownLoad();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开启服务
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

        //连接远程Service和Activity
        binderIntent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("flag",create_flag);
        binderIntent.putExtras(bundle);
        bindService(binderIntent, sc, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"++MainActivity onDestroy++");

        boolean flag = false;
        //暂停服务
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);

        //断开与远程Service的连接
        unbindService(sc);
    }
}
