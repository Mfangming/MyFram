package com.fm.camero;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fangming.ffmtest.Jnitest;
import com.fangming.myframwork.R;
import com.fm.file.FileUtil;
import com.fm.qxtapp.MyApp;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class TestcamoraActivety extends Activity implements Callback,
		PreviewCallback {

	private static final String FRAME_DUMP_FOLDER_PATH = Environment
			.getExternalStorageDirectory() + File.separator + "ffmpegTest";
	private TestcamoraActivety _this;
	private String TAG = "TestcamoraActivety";
	private Button btn_start;
	private SurfaceView sf_surfaceView;
	private SurfaceHolder suHolder;
	private int width;// 分辨率宽度
	private int height;// 分辨率高度
	private Camera mCamera;
	private boolean isStreaming = false;
	private File myFile;
	private MyApp myApp;
	private String path;
	private Button btn_puse;
	private ImageView iv_show;
	private Boolean isShow = true;
	int mwidth, mheight;
	private Button btn_switch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_testcamora);
		_this = this;
		initView();
		myApp = MyApp.getInstance();
		path = myApp.filepath + File.separator + System.currentTimeMillis()
				+ ".yuv";
		try {
			myFile = FileUtil.createFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initSurfaceholder() {
		suHolder = sf_surfaceView.getHolder();
		suHolder.addCallback(this);
		suHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		// width = 352;
		// height = 288;
		width = 320;
		height = 480;
		mwidth = width;
		mheight = height;
		System.out.println("*****相机初始化完成*****");
	}

	private void initView() {
		btn_start = (Button) findViewById(R.id.btn_start);
		btn_puse = (Button) findViewById(R.id.btn_puse);
		iv_show = (ImageView) findViewById(R.id.iv_show);
		sf_surfaceView = (SurfaceView) findViewById(R.id.sf_surfaceView);
		initSurfaceholder();
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mCamera.startPreview();
//				new DumpFrameTask().execute();
			}
		});
		btn_puse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// try {
				// byte[] current = "123".getBytes();
				// System.out.println(Arrays.toString(current));
				// FileUtil.writeFileSdcardFile(path, current, true);
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
				Jnitest.stoplay();
			}
		});
		btn_switch=(Button)findViewById(R.id.btn_switch);
		btn_switch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

	/* (non-Javadoc)
	 * @see android.hardware.Camera.PreviewCallback#onPreviewFrame(byte[], android.hardware.Camera)
	 */
	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		isStreaming = true;
		System.out.println("*****相机采集到的数组长度" + data.length + "*****");
		if (isShow) {
			mwidth = camera.getParameters().getPreviewSize().width;
			mheight = camera.getParameters().getPreviewSize().height;
			isShow = false;
		}

		// yuv420
		// byte[] current=new byte[data.length];
		// System.arraycopy(data, 0, current, 0, data.length);
		int[] current = Jnitest.decodeYUV420SP(data, mwidth, mheight);

		Bitmap bmp = Bitmap.createBitmap(current, mwidth, mheight,
				Bitmap.Config.ARGB_8888);
		iv_show.setRotation(90);
		iv_show.setImageBitmap(bmp);
		// iv_show.setImageBitmap(RemoteUtil.rawByteArray2RGBABitmap2(data,
		// mwidth, mheight));
		// 封包传输上传
//		int m = Jnitest.decodeH264(data, mwidth, height);
//		Log.e(TAG, m + "");
//		 try {
//		 FileUtil.writeFileSdcardFile(path, current, true);
//		 } catch (IOException e) {
//		 e.printStackTrace();
//		 }
		// System.out.println(Arrays.toString(current));
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mCamera = Camera.open();
		try {
			mCamera.setPreviewDisplay(suHolder);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mCamera.setPreviewCallback(this);
		List<Size> previewSizes = mCamera.getParameters()
				.getSupportedPreviewSizes();
		width = previewSizes.get(0).width;
		height = previewSizes.get(0).height;
		Camera.Parameters parameters = mCamera.getParameters();
		parameters.setPreviewSize(width, height);
		// 横竖屏镜头自动调整
		if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
			parameters.set("orientation", "portrait"); //
			parameters.set("rotation", 90); // 镜头角度转90度（默认摄像头是横拍）
			mCamera.setDisplayOrientation(90); // 在2.2以上可以使用
		} else// 如果是横屏
		{
			parameters.set("orientation", "landscape"); //
			mCamera.setDisplayOrientation(0); // 在2.2以上可以使用
		}
		System.out.println("*****surfaceCreated*****");
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
							   int height) {
		System.out.println("*****系统执行surfaceChanged*****");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		System.out.println("*****surfaceDestroyed*****");
		if (mCamera != null) {
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	}

	class DumpFrameTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
//			Jnitest.testHandler(_this, FRAME_DUMP_FOLDER_PATH + File.separator
//					+ "hobbit.flv", 20);
			Jnitest.netFramPlay(_this,"rtmp://live.hkstv.hk.lxdns.com/live/hks");
//			Jnitest.netFramPlay(_this,"rtp://192.168.26.87:5004");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}
	}

	private void getBitmap(Bitmap bitmap) {
		Message msg = new Message();
		msg.what=0;
		msg.obj = bitmap;
		mHandler.sendMessage(msg);
	}

	public Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 0:
					Log.d(TAG, "mHandler");
					Bitmap bitmap = (Bitmap) msg.obj;
					if (bitmap != null) {
						iv_show.setRotation(90);
						iv_show.setImageBitmap(bitmap);
					}
					break;

				default:
					break;
			}
		};
	};
}
