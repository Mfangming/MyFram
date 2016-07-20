package com.fm.testanima;

import com.fangming.myframwork.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class Testopendoor extends Activity {
	private Button btn_opendoor;
	private TextView door_left;
	TranslateAnimation tAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testanima);
		initView();
		
	}

	private void initView() {
		door_left =(TextView)findViewById(R.id.door_left);
		tAnimation=new TranslateAnimation(0, -80, 0, 0);
		tAnimation.setDuration(5000);
		tAnimation.setFillAfter(true);
		btn_opendoor =(Button)findViewById(R.id.btn_opendoor);
		btn_opendoor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				door_left.startAnimation(tAnimation);
			}
		});
		
	}

}
