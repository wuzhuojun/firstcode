package com.example.fcode1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wuzhuojun on 2016/7/29 0029.
 */
public class SecondFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflater.inflate(R.layout.fragment_second, container);
        Button btn = (Button)getActivity().findViewById(R.id.btn_update_activity);
        btn.setOnClickListener(this);

        Button btn2 = (Button)getActivity().findViewById(R.id.btn_update_activity2);
        btn2.setOnClickListener(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * Activity 访问 Fragment的元素
     */
    public void UpdateText()
    {
        TextView textView = (TextView)getActivity().findViewById(R.id.tx_show);
        textView.setText("update text");
    }

    /**
     * Fragment 访问 Activity元素的第一种方法
     */
    public void UpdateActivityElement()
    {
        Button btn = (Button)getActivity().findViewById(R.id.btn_update_fragment);
        btn.setText("Activity元素被更新");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * Fragment 访问 Activity元素的第一种方法
             * 面向具体编程，耦合性较高
             * 它要把Activity元素放到Framgent中， 如：btn_update_fragment
             */
            case R.id.btn_update_activity:
                UpdateActivityElement();
                break;
            /**
             * Fragment 访问 Activity元素的第二种方法
             * 面向接口编程，耦合性低
             * 元素的操作是在自身的Activity中操作
             */
            case R.id.btn_update_activity2:
                    IUpdateActivity updateActivity = (SecondActivity)getActivity();
                    updateActivity.onUpdateActivityElement();
                break;
        }
    }
}


interface IUpdateActivity
{
    void onUpdateActivityElement();
}