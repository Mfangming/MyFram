package com.fm.myframwork;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import com.fangming.myframwork.R;
import com.fm.qxtapp.MyApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestSocket extends Activity {
	//	private String ipAdd = "192.168.1.1";
//	private String ipAdd = "192.168.26.87";
	private String ipAdd = "192.168.253.1";
	private int port = 10001;
	private Socket mSocket;
	private OutputStream outStream;
	private InputStream inStream;
	private Boolean isAccepte = true;
	private MyApp qxtApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		qxtApp=(MyApp) getApplication();
		new InitSocketThread().start();
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sendMsg();
			}
		});
	}

	private void sendMsg() {
		JSONObject jo = new JSONObject();
		try {
			jo.put("user", "anjubao");
			jo.put("passwrod", "ajbadmin");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		byte[] data;
		try {
			data = jo.toString().getBytes("GBK");
			int length = data.length;
			byte[] buffer = new byte[10 + length];// 创建一个定长byte数组
			byte[] APPREQUEST_HEAD = { 0x0b,(byte) 0xB8 };
			System.arraycopy(APPREQUEST_HEAD, 0, buffer, 0, 2);// 写入头3000
			byte[] type = { 0, 0 , 0x21, (byte) 0x84};
			System.arraycopy(type, 0, buffer, 2, 4);
			byte[] len = intToBytes(length);
			System.arraycopy(len, 0, buffer, 6, 4);
			System.arraycopy(data, 0, buffer, 10, length);
			if (mSocket.isConnected()) {
				try {
					outStream.write(buffer);
					outStream.flush();
					Log.e("test", "发送成功");
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("test", "发送失败");
				}
			} else {
				Log.e("test", "断开连接");
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	public byte[] intToBytes(int n) {
		byte[] b = new byte[4];
		b[3] = (byte) (n & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[0] = (byte) (n >> 24 & 0xff);
		return b;
	}

	private void initSocket() {
		try {
			mSocket = new Socket(ipAdd, port);
			if (mSocket.isConnected()) {
				Log.e("test", "连接上了socket");
				outStream = mSocket.getOutputStream();
				inStream = mSocket.getInputStream();
				String str="{\"user\""+":\""+"anjubao"+"\",\"password\""+":\""+"admin"+"\"}";
//				JSONObject jo = new JSONObject();
//				Gson g=new Gson();
//				User u=new User("anjubao", "ajbadmin");
//				String str=g.toJson(u);
//				try {
//					jo.put("user", "anjubao");
//					jo.put("password", "ajbadmin");
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				byte[] data = jo.toString().getBytes("GB2312");
				byte[] data = str.getBytes("UTF-8");
				if (mSocket.isConnected()) {
					try {
						outStream.write(data);
						outStream.flush();
						Log.e("test", "发送成功");
					} catch (IOException e) {
						e.printStackTrace();
						Log.e("test", "发送失败");
					}
				} else {
					Log.e("test", "断开连接");
				}
//				sendMsg();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class InitSocketThread extends Thread {

		@Override
		public void run() {
			super.run();
			initSocket();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		disConnected();
	}

	class ReadThread extends Thread {
		@Override
		public void run() {
			super.run();
			while (isAccepte) {
				if (mSocket != null && mSocket.isConnected()) {
					try {
						recv(new DataInputStream(new BufferedInputStream(
								inStream)));
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(5 * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	@SuppressLint("NewApi")
	public void recv(DataInputStream dis) throws IOException {
		Log.e("test", "---->>接收数据包");
		byte[] xx = new byte[8 * 1024];
		dis.readFully(xx, 0, 10);
		String mm = new String(xx);
		Log.e("test", "---->>" + xx);
		isAccepte = false;
		return;
	};

	public void disConnected() {
		try {
			if (null != mSocket) {
				Log.e("test", "---->断开连接");
				if (mSocket.isOutputShutdown()) {
					mSocket.shutdownOutput();
				}
				if (mSocket.isInputShutdown()) {
					mSocket.shutdownInput();
				}
				mSocket.close();
				mSocket = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
