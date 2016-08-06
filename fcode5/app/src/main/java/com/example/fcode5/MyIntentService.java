package com.example.fcode5;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * 每次用Service都需要另外启动线程来处理耗时操作。而IntentService对此做了封装，不需要每次另外启动线程。
 */
public class MyIntentService extends IntentService {

	private Intent receiverIntent = new Intent("com.example.fcode5.RECEIVER");

	public MyIntentService() {
		super("MyIntentService");
	}

	/**
	 * 多线程操作
	 * @param intent
     */
	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("MyIntentService", "Thread id is " + Thread.currentThread().getId());

		// 处理 网络请求
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL("http://www.sina.com");
			urlConnection = (HttpURLConnection)url.openConnection();
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			Scanner scanner = new Scanner(in).useDelimiter("\\A");
			String result = scanner.hasNext() ? scanner.next() : "";

			//发送Action为com.example.fcode5.RECEIVER的广播
			receiverIntent.putExtra("value", result);
			sendBroadcast(receiverIntent);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			urlConnection.disconnect();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("MyIntentService", "onDestroy executed");
	}

}
