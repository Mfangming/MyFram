package com.fm.jsoup;

import com.fangming.myframwork.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
			jUtil.getdDocument(urlString);
		}

	}

	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 1:
					// tv_test.setText((String) msg.obj);

					break;

				default:
					break;
			}
		};

	};

}
