package com.example.fcode15;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wuzhuojun on 2016/8/4 0004.
 */
public class ChildFragment extends Fragment {

    @OnClick(R.id.btn_send_event_child) void sendMessageEvent()
    {
        EventBus.getDefault().post(new MessageEvent());
    }

    @OnClick(R.id.btn_send_event_child_hello) void sendHelloEvent()
    {
        EventBus.getDefault().post(new HelloEvent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child, container);
        ButterKnife.bind(this, view);  //Fragment绑定注解的方式

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
