package com.fm.volly;

import org.json.JSONException;
import org.json.JSONObject;

import com.fangming.myframwork.R;
import com.fm.qxtapp.MyApp;
import com.fm.volly.VollyUtill.VollyResponseListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestVollyUtillActivity extends Activity {
	private MyApp mApp;
	private Button btn_volly;
	private TestVollyUtillActivity _this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApp = MyApp.getInstance();
		_this = this;
		setContentView(R.layout.activity_testvolly);
		btn_volly = (Button) findViewById(R.id.btn_volly);
		btn_volly.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mApp.vollUtill.connetion(_this,
						"appLogin",
						"username=15271894533&password=25d55ad283aa400af464c76d713c07ad&reqToken=152718945334ac8aa21e16941ccb8a5e409e302b3661456447182973"
						,new VollyResponseListener() {

							@Override
							public void onResponse(String respons) {
								JSONObject jo;
								boolean status = false;
								try {
									jo = new JSONObject(respons);
									status = jo.getBoolean("status");
								} catch (JSONException e) {
									e.printStackTrace();
								}
								if(status){
									Log.d("Test", "登录成功");
								}
							}
						});
			}
		});

	}

}
