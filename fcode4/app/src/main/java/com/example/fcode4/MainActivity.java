package com.example.fcode4;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @BindView(R.id.text_show_content)
    TextView textShow;

    /**
     * 启动服务 & 绑定服务
     */
    @OnClick(R.id.btn_start_service) void startService()
    {
        Intent startIntent = new Intent(this, MyService.class);
        startService(startIntent);
        bindService(startIntent, connection, BIND_AUTO_CREATE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
    }

    /**
     * 管理 Activity 与 Service 之间的链接。
     * 等待被 AMS 回调，获取到 Service 的引用，从而可以调用 Service的功能。
     */
    public ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.DownloadBinder binder = (MyService.DownloadBinder)service;
            MyService myservice = binder.getService();
            myservice.startDownload();

            //注册回调接口来接收下载进度的变化
            myservice.setOnUpdateUIListener(new OnUpdateUIListener() {

                /**
                 * 该回调方法由子线程调用，属于跨线程访问UI元素，因此需要通过 发消息的方法来更新UI
                 * @param result
                 */
                @Override
                public void OnUpdateUIListener(String result) {
                    if(null != result) {
                        Message msg = new Message();
                        Bundle b = new Bundle();// 存放数据
                        b.putString("value", result);
                        msg.setData(b);

                        myHandler.sendMessage(msg);
                    }
                }
            });
        }
    };

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
            textShow.setText(val);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unbindService(connection);
    }
}
