package com.example.fcode1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by wuzhuojun on 2016/7/29 0029.
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener, IUpdateActivity{
    SecondFragment secondFragment = new SecondFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        addFragmentToStack(secondFragment);

        Button btn = (Button)findViewById(R.id.btn_update_fragment);
        btn.setOnClickListener(this);
    }

    private void addFragmentToStack(Fragment fragment) {
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_fragment:
                secondFragment.UpdateText();
            break;
        }
    }

    @Override
    public void onUpdateActivityElement() {
        Button btn = (Button)findViewById(R.id.btn_update_fragment);
        btn.setText("Activity元素被更新2");
    }
}
