package com.example.fcode13;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wuzhuojun on 2016/8/3 0003.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "接收到广播信息", Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
