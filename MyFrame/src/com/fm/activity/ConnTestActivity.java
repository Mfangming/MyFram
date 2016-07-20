package com.fm.activity;

import com.fangming.myframwork.R;
import com.fm.constants.Constants;
import com.fm.service.ConnService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ConnTestActivity extends Activity {
	private String TAG = "ConnTestActivity";
	private ConnTestActivity _this;
	private Button btn_conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conntest);
		_this = this;
		Intent intent = new Intent(_this, ConnService.class);
		startService(intent);
		initView();
	}

	private void initView() {
		btn_conn = (Button) findViewById(R.id.btn_conn);
		btn_conn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				serviceSend(Constants.SERVICE_REC, Constants.BINGIN_CONN);
			}
		});

	}

	private void serviceSend(String action, int type) {
		Intent intent = new Intent();
		intent.setAction(action);
		intent.putExtra("type", type);
		sendBroadcast(intent);
	}



	@Override
	protected void onDestroy() {
		Intent intent = new Intent(_this, ConnService.class);
		stopService(intent);
		super.onDestroy();
	}
}
