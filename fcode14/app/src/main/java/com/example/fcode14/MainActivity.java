package com.example.fcode14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.ObjectGraph;

public class MainActivity extends AppCompatActivity {

    @Inject UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObjectGraph.create(AppModule.class).inject(this);

        userModel.setName("张三");

        TextView textView = (TextView) findViewById(R.id.tx_show_name);
        textView.setText(userModel.getName());
    }
}
