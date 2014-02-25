package com.example.notification_traning;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {
	NotificationCompat.Builder mBuilder;
	PopupWindow pWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBuilder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("My notification")
				.setContentText("Hello World!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void getNotification(View v) {
		PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
				null, PendingIntent.FLAG_ONE_SHOT);
		int mNotificationId = 001;
		NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mBuilder.setContentIntent(resultPendingIntent);
		Notification notify = mBuilder.setAutoCancel(true).build();
		mNotifyMgr.notify(mNotificationId, notify);
	}

	public void showPopup(View v) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.popup,
				(ViewGroup) findViewById(R.id.popup));
		TextView textview = (TextView)layout.findViewById(R.id.text);
		textview.setText("My notification");
		pWindow = new PopupWindow(layout, 350, 350, true);
		pWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
		OnClickListener close_button_listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				pWindow.dismiss();
			}
		};

		Button close_btn = (Button) layout.findViewById(R.id.btn_close_popup);
		close_btn.setOnClickListener(close_button_listener);
	}
}
