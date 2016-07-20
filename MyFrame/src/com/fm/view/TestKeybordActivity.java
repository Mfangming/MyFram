package com.fm.view;

import com.fangming.myframwork.R;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class TestKeybordActivity extends Activity{
	private Context ctx;  
    private Activity act;
	private EditText et_key;
	private KeyboardView keyboard_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testkeybord);
		ctx=this;
		act=this;
		et_key=(EditText)findViewById(R.id.et_key);
		et_key.setInputType(InputType.TYPE_NULL);
		keyboard_view=(KeyboardView)findViewById(R.id.keyboard_view);
		et_key.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				new KeyboardUtil(act, ctx, et_key).showKeyboard(); 
				return false;
			}
		});
	}

}
