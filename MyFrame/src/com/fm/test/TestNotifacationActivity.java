package com.fm.test;

import com.fangming.myframwork.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TestNotifacationActivity extends Activity {
	private int mm = 1;
	private TextView mytv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testbaseactivity);
		mytv=(TextView)findViewById(R.id.mytv);
		mytv.setText("我是页面一");
		findViewById(R.id.btn).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				notificationMethod(mm);
				mm++;
			}
		});

	}

	private void notificationMethod(int i) {
		// 在Android进行通知处理，首先需要重系统哪里获得通知管理器NotificationManager，它是一个系统Service。
		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Intent miIntent=new Intent(this, TestNotifacationActivity2.class);
		miIntent.putExtra("data", mm+"");
		PendingIntent pendingIntent2 = PendingIntent.getActivity(this, mm,
				miIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		// 通过Notification.Builder来创建通知，注意API Level
		Notification notify2 = new Notification.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setTicker("TickerText:" + "---" + i)// 设置在status
				.setContentTitle("Notification Title"+ i)// 设置在下拉status
				.setAutoCancel(true)
				.setContentText("This is the notification message")// TextView中显示的详细内容
				.setContentIntent(pendingIntent2) // 关联PendingIntent
				.setNumber(mm) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
				.build();
		manager.notify(mm, notify2);
	}

}
