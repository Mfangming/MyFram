package com.fangming.testffmpeg;

import com.fangming.ffmtest.fftest;
import com.fangming.myframwork.R;
import com.fm.qxtapp.MyApp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestffmpegActivity3 extends Activity{
	private TestffmpegActivity3 _this;
	private String _TAG="--TestffmpegActivity3--";
	private MyApp myApp;
	private EditText et_input;
	private EditText et_output;
	private Button btn_start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_testffmpeg3);
		_this = this;
		initView();
		myApp = MyApp.getInstance();
	}


	private void initView() {
		et_input = (EditText) findViewById(R.id.et_input);
		et_output = (EditText) findViewById(R.id.et_output);
		btn_start = (Button) findViewById(R.id.btn_start);
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String urltext_input=et_input.getText().toString();
				String inputurl=myApp.filepath+"/"+urltext_input;
				String urltext_output=et_output.getText().toString();
				String outputurl=myApp.filepath+"/"+urltext_output;
				new DumpFrameTask(inputurl, outputurl).execute();
			}
		});
	}

	class DumpFrameTask extends AsyncTask<Void, Void, Integer> {
		String inputurl;
		String outputurl;

		public DumpFrameTask(String inputurl, String outputurl) {
			super();
			this.inputurl = inputurl;
			this.outputurl = outputurl;
		}

		@Override
		protected Integer doInBackground(Void... params) {
			fftest ff=new fftest();
			int mm= fftest.decode(ff,inputurl,outputurl);
			return mm;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			if(null!=result){
				Toast.makeText(_this, result+"", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
