package com.fm.opengl;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class TestOpengl extends Activity {

	private OpenGLView mOpenGLView;
	private OpenGLRenderer mRenderer;
	private ISimplePlayer mSPlayer;
	private DisplayMetrics mDM;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mOpenGLView = new OpenGLView(this);
		mOpenGLView.setEGLContextClientVersion(2); 
		mOpenGLView.requestFocus();
		mOpenGLView.setFocusableInTouchMode(true);
		mOpenGLView.setZOrderOnTop(true); 
		mDM = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDM);
		mRenderer=new OpenGLRenderer(mSPlayer, mOpenGLView, mDM);
		mOpenGLView.setRenderer(mRenderer);
		setContentView(mOpenGLView);
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		mOpenGLView.onPause();
	}

	@Override
	protected void onResume() {
		if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    }
		super.onResume();
		mOpenGLView.onResume();
	}

}
