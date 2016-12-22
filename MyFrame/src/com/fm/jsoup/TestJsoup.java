package com.fm.jsoup;

import com.fangming.myframwork.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TestJsoup extends Activity {
	private JsoupUtil jUtil;
	private TextView tv_test;
	private Button btn_gethtml;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_jsoup);
		jUtil = JsoupUtil.getInstance();
		initView();
	}

	private void initView() {
		tv_test = (TextView) findViewById(R.id.tv_test);
		btn_gethtml = (Button) findViewById(R.id.btn_gethtml);
		btn_gethtml.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new myThread("http://www.cnbeta.com").start();
			}
		});

	}

	class myThread extends Thread {
		String urlString;

		public myThread(String url) {
			this.urlString = url;
		}

		@Override
		public void run() {
			super.run();
//			jUtil.getdDocument(urlString);

			Bundle bundle=new Bundle();
			byte[] txt=new byte[]{123};
			bundle.putByteArray("msd",txt);
			Message msg= handler.obtainMessage();
			msg.setData(bundle);
			msg.what=1;
			handler.sendMessage(msg);
		}

	}

	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 1:
					// tv_test.setText((String) msg.obj);
					Bundle bundle=msg.getData();
					byte[] a= bundle.getByteArray("msd");
					Log.e("test",String.valueOf(a));

					break;

				default:
					break;
			}
		};

	};

}
