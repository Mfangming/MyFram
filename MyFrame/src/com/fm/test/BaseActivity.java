package com.fm.test;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public abstract class BaseActivity extends Activity{
	private String TAG_Base=BaseActivity.class.getSimpleName();
	private Handler mHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG_Base, "BaseActivity onCreate");
	}
	
//	public void setHandler(Handler mHandler){
//		this.mHandler=mHandler;
//	};
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG_Base, "BaseActivity onDestroy");
		if(null!=mHandler){
			mHandler.removeCallbacksAndMessages(null);
		}
	}

}
