package main.java.com.fm.test;

import com.fangming.myframwork.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class TestBaseActivity extends BaseActivity {
	private String TAG = TestBaseActivity.class.getName();
	private TextView mytv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "TestBaseActivity onCreate--");
		setContentView(R.layout.activity_testbaseactivity);
		mytv=(TextView)  findViewById(R.id.mytv);
		new Thread(new MyThread()).start();
		 
	}
	
	class MyThread implements Runnable{

		@Override
		public void run() {
			Message msg = handler.obtainMessage();
			msg.what = 1;
			msg.obj = "hello";
//			handler.sendMessage(msg);
			handler.sendMessageDelayed(msg,10*1000);
		}
		
	}
	
	Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			mytv.setText(msg.obj+"");
			Log.e(TAG, msg.obj+"");
		};
	};

	@Override
	protected void onDestroy() {
		handler.removeCallbacksAndMessages(null);
		super.onDestroy();
		Log.d(TAG, "TestBaseActivity onDestroy");
		
	}

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
//			finish();
//		}
//		return super.onKeyDown(keyCode, event);
//	}
}
