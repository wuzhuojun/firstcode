package com.example.fcode15;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_show)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        ButterKnife.bind(this);

        addFragmentToStack(new ChildFragment());
    }

    private void addFragmentToStack(Fragment fragment) {
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event)
    {
        textView.setText("message");
    }

    @Subscribe
    public void onHelloEvent(HelloEvent event)
    {
        textView.setText("hello");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);  //注意要解除注册，否则会引起内存泄漏
    }
}
