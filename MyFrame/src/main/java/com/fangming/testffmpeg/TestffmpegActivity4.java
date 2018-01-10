package main.java.com.fangming.testffmpeg;

import com.fangming.ffmtest.fftest;
import com.fangming.myframwork.R;
import com.fm.qxtapp.MyApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class TestffmpegActivity4 extends Activity{
	private TestffmpegActivity4 _this;
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
				et_input.setText(fftest.exchange());
			}
		});
	}
}
