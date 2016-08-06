package com.example.fcode8;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wuzhuojun on 2016/8/3 0003.
 */
public class FragmentA extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //返回在本Fragment实现类所 画好的 view
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        return view;
    }
}
