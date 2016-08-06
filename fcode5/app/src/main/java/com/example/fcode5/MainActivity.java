package com.example.fcode5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private MsgReceiver msgReceiver;
    private Intent serviceIntent;
    private Intent serviceIntent2;

    @BindView(R.id.text_show_content)
    TextView textShow;

    /**
     * 启动服务
     */
    @OnClick(R.id.btn_start_service) void startService()
    {
        serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
    }

    /**
     * 启动服务二
     */
    @OnClick(R.id.btn_start_service2) void startService2()
    {
        serviceIntent2 = new Intent(this, MyIntentService.class);
        startService(serviceIntent2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        //动态注册广播接收器
        msgReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.fcode5.RECEIVER");
        registerReceiver(msgReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        //停止服务
        stopService(serviceIntent);
        stopService(serviceIntent2);
        //注销广播
        unregisterReceiver(msgReceiver);
    }

    /**
     * 广播接收器
     *
     */
    public class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //拿到网络数据，更新UI
            String values = intent.getStringExtra("value");
            textShow.setText(values.toString());
        }

    }
}
