package com.example.fcode13;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSendBroadcast = (Button) findViewById(R.id.btn_send_broadcast);
        btnSendBroadcast.setOnClickListener(this);

        Button btnGetContent = (Button) findViewById(R.id.btn_get_content);
        btnGetContent.setOnClickListener(this);

        Logger.d("hello");
        Logger.d("hello %s %d", "world", 5);   // String.format
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_broadcast:
                Intent intent = new Intent("com.example.fcode13.MY_BROADCAST");
                sendBroadcast(intent);
                break;
            case R.id.btn_get_content:
                Cursor cu = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                String name = "";
                while (cu.moveToNext()) {
                    name = cu.getString(cu.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) + name;
                }
                TextView tv = (TextView) findViewById(R.id.show_text);
                tv.setText(name);
                break;
        }
    }
}
