package main.java.com.fm.test;

import com.fangming.myframwork.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TestBaseActivity2 extends BaseActivity {
	private String TAG = TestBaseActivity2.class.getName();
	private TextView mytv;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "TestBaseActivity onCreate--");
		setContentView(R.layout.activity_testbaseactivity);
		mytv=(TextView)  findViewById(R.id.mytv);
		btn=(Button)  findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TestBaseActivity2.this,TestBaseActivity.class);
				startActivity(intent);
			}
		});
		
	}

}
