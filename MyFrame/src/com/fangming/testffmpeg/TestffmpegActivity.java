package com.fangming.testffmpeg;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fangming.ffmtest.Jnitest;
import com.fangming.myframwork.R;
import com.fm.file.FileUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TestffmpegActivity extends Activity {
	private TestffmpegActivity _this;
	private static final String FRAME_DUMP_FOLDER_PATH = Environment
			.getExternalStorageDirectory() + File.separator + "ffmpegTest";
	private EditText et_framnum;
	private Button btn_start;
	private Button btn_stop;
	static AssetManager assetManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testffmpeg);
		_this = this;
		assetManager = getAssets();
		initView();
		initFile();
	}

	/**
	 * 初始化view
	 */
	private void initView() {
//		et_framnum = (EditText) findViewById(R.id.et_framnum);
		btn_start = (Button) findViewById(R.id.btn_start);
		btn_stop=(Button) findViewById(R.id.btn_stop);
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// String numText = et_framnum.getText().toString();
				// int numOfFrames;
				// try {
				// numOfFrames = Integer.valueOf(numText);
				// } catch (Exception e) {
				// e.printStackTrace();
				// numOfFrames = 5;
				// }
				// //limit number of frames to grab to 20
				// if (numOfFrames > 20) {
				// numOfFrames = 20;
				// }
				// start processing using asynctask
				new DumpFrameTask().execute();


			}
		});

		btn_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				Jnitest.VideoPlayerStop();
			}
		});
	}

	private void saveFrameToPath(Bitmap bitmap, String pPath) {
		int BUFFER_SIZE = 1024 * 8;
		try {
			File file = new File(pPath);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			final BufferedOutputStream bos = new BufferedOutputStream(fos,
					BUFFER_SIZE);
			bitmap.compress(CompressFormat.JPEG, 100, bos);
			bos.flush();
			bos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化file
	 */
	private void initFile() {
		File dumpFolder = new File(FRAME_DUMP_FOLDER_PATH);
		if (!dumpFolder.exists()) {
			dumpFolder.mkdirs();
		}
		// copy input video file from assets folder to directory
		FileUtil.copyAssets(this, "1.mp4", FRAME_DUMP_FOLDER_PATH);
	}

	class DumpFrameTask extends AsyncTask<Void, Void, Integer> {
		ProgressDialog mlDialog;

		@Override
		protected void onPreExecute() {
			// mlDialog = ProgressDialog.show(_this,
			// "Dump Frames","Processing..Wait.." , false);
		}

		@Override
		protected Integer doInBackground(Void... params) {
			int ret = 0;
//			ret = Jnitest.getVoice(FRAME_DUMP_FOLDER_PATH
//					+ File.separator + "3.mp3");
//			if (ret == -1) {
//				Log.e("test", "打开文件失败！");
//			} else {
//				Log.e("test", "ok!");
//			}
//			ret=Jnitest.PushVadio(FRAME_DUMP_FOLDER_PATH+ File.separator + "hobbit.flv", "rtmp://localhost/publishlive/livestream");
			return ret;
		}

		@Override
		protected void onPostExecute(Integer param) {
//			if (null != mlDialog && mlDialog.isShowing()) {
//				mlDialog.dismiss();
//			}
			// _this.displayDumpedFrames();
		}
	}

	/**
	 * 解码数据
	 */
	// class DumpFrameTask extends AsyncTask<Void, Void, Integer> {
	// int mlNumOfFrames;
	// ProgressDialog mlDialog;
	// DumpFrameTask(int pNumOfFrames) {
	// this.mlNumOfFrames = pNumOfFrames;
	// }
	// @Override
	// protected void onPreExecute() {
	// mlDialog = ProgressDialog.show(_this, "Dump Frames","Processing..Wait.."
	// , false);
	// }
	// @Override
	// protected Integer doInBackground(Void... params) {
	// int ret=0;
	// ret=Nativeffmpeg.decode(FRAME_DUMP_FOLDER_PATH + File.separator +
	// "1.mp4",mlNumOfFrames,_this.getClass());
	// if(ret==-1){
	// Log.e("test", "打开文件失败！");
	// }else if(ret==-2){
	// Log.e("test", "Couldn't find stream information!");
	// }else if(ret==-3){
	// Log.e("test", "Didn't find a video stream!");
	// }else if(ret==-4){
	// Log.e("test", "Codec not found!");
	// }else if(ret==-5){
	// Log.e("test", "Could not open codec!");
	// }else if(ret==-6){
	// Log.e("test", "not Allocate an AVFrame structure!");
	// }else if(ret==-7){
	// Log.e("test", "锁定像素失败!");
	// }else{
	// Log.e("test", "ok!");
	// }
	// return ret;
	// }
	// @Override
	// protected void onPostExecute(Integer param) {
	// if (null != mlDialog && mlDialog.isShowing()) {
	// mlDialog.dismiss();
	// }
	// // _this.displayDumpedFrames();
	// }
	// }

}
