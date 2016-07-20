package com.fm.test;

import com.fangming.myframwork.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TestNotifacationActivity2 extends Activity{
	private Intent intent;
	private TextView mytv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testbaseactivity);
		intent=getIntent();
		mytv=(TextView)findViewById(R.id.mytv);
		mytv.setText(intent.getStringExtra("data"));
	}
	
}
