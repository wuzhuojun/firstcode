package com.example.fcode1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BtnOpen = (Button)findViewById(R.id.btn_open_new);
        BtnOpen.setOnClickListener(this);

        Button BtnSecond = (Button)findViewById(R.id.btn_open_second);
        BtnSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_new:
                Intent intent = new Intent(this, NewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_open_second:
                Intent intent2 = new Intent(this, SecondActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
