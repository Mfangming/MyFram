package com.fm.service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

import com.fm.constants.Constants;
import com.fm.utill.ByteConvertUtils;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ConnService extends Service {
	private final static int PACKATHEADER_SIGN_LENGTH = 2;// AJB包标识
	private final static int PACKATHEADER_TYPE_LENGTH = 4;// 包类型 4byte
	private final static int PACKATHEADER_DATALEN_LENGTH = 4;// 包长度 4byte
	private final static int PACKATHEADER_LENGTH = PACKATHEADER_SIGN_LENGTH
			+ PACKATHEADER_TYPE_LENGTH + PACKATHEADER_DATALEN_LENGTH;// 包头
	private String ipAdd = "10.1.3.1";
	private int port = 10001;
	private String TAG = "ConnService";
	private Socket mSocket;
	private OutputStream outStream;
	private InputStream inStream;
	private InitSocketThread initSocketThread;
	private ReadThread readThread;
	private Boolean isAccepte = true;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e(TAG, "---->" + "onCreateservice");
		IntentFilter myFilter = new IntentFilter();
		myFilter.addAction(Constants.SERVICE_REC);
		this.registerReceiver(ResponBR, myFilter);
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "---->service停止");
		unregisterReceiver(ResponBR);
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.e(TAG, "---->" + "onStart");
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e(TAG, "---->" + "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 初始化socket线程
	 */
	class InitSocketThread extends Thread {

		@Override
		public void run() {
			super.run();
			initSocket();
		}
	}

	/**
	 * 初始化socket
	 */
	private void initSocket() {
		Log.e(TAG, "---->创建socket成功");
		try {
			mSocket = new Socket(ipAdd, port);
			mSocket.setSoTimeout(10 * 1000);// 10秒超时
			if (mSocket.isConnected()) {
				Log.e(TAG, "---->连接成功");
				outStream = mSocket.getOutputStream();
				inStream = mSocket.getInputStream();
				Log.d(TAG, "socket conn success");
				// serviceSend(Constants.SERVICE_SEND, Constants.CONN_SUCCESS);
				readThread = new ReadThread();
				readThread.start();
			}
		} catch (SocketException e) {
			Log.d(TAG, "socket conn fail");
			// serviceSend(Constants.SERVICE_SEND, Constants.CONN_FAIL);
			e.printStackTrace();
		} catch (IOException e) {
			Log.d(TAG, "socket conn fail");
			// serviceSend(Constants.SERVICE_SEND, Constants.CONN_FAIL);
			e.printStackTrace();
		}
	}

	class ReadThread extends Thread {
		@Override
		public void run() {
			super.run();
			while (isAccepte) {
				if (mSocket != null && mSocket.isConnected()) {
					devide(new DataInputStream(
							new BufferedInputStream(inStream)));
				}
			}
		}
	}

	public synchronized void devide(DataInputStream ds) {
		try {
			if (ds.available() > 81) {
				Log.e(TAG, ds.available() + "");
			}
			while (ds.available() != 0) {
				recv(ds);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接收解析协议包
	 *
	 * @param dis
	 * @throws IOException
	 */
	@SuppressLint("NewApi")
	public void recv(DataInputStream dis) throws IOException {
		byte[] head = new byte[PACKATHEADER_LENGTH];// 10个字节
		Log.e(TAG, "---->>接收数据包" + dis.available());
		try {
			dis.readFully(head, 0, PACKATHEADER_LENGTH);
		} catch (IOException e) {
			return;
		}

		// 读取前两个字节 AJB包标识 head[]
		byte[] sign = new byte[2];
		sign = Arrays.copyOfRange(head, 0, 2);
		if (ByteConvertUtils.bytesToInt2(sign) != Constants.APP_CTL_HEAD) {
			return;
		}
		// 读取4byte包类型
		byte[] type = new byte[4];
		type = Arrays.copyOfRange(head, 2, 6);
		// 读取4byte内容长度
		byte[] datalen = new byte[4];
		datalen = Arrays.copyOfRange(head, 6, 10);

		int nType = ByteConvertUtils.bytesToInt(type);
		int nDatalen = ByteConvertUtils.bytesToInt(datalen);
		String strData = "";
		byte[] data = null;
		if (nDatalen > 0) {
			// 接收指定长度的内容
			data = new byte[nDatalen];
			dis.readFully(data, 0, nDatalen);
			strData = new String(data);
			Log.e(TAG, "---->返回信息：" + strData);
		}
		Log.e(TAG, "nType:" + nType + "  nDatalen:" + nDatalen);
		Intent intent;
		switch (nType) {// App登陆/查询网关
			case Constants.SERVER_SEND_PLAY:
				Log.e(TAG, "---->家电网关向APP返回登陆信息：" + strData);
				// 刷新ui
				// sendBroad(data, Constants.APPRETURN_TYPE_LOGWG);
				break;
			default:
				Log.e(TAG, "---->不识别的命令：" + strData);
				break;
		}
	};

	private void serviceSend(String action, int type) {
		Intent intent = new Intent();
		intent.setAction(action);
		intent.putExtra("type", type);
		sendBroadcast(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private BroadcastReceiver ResponBR = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (Constants.SERVICE_REC.equals(intent.getAction())) {
				int type=intent.getExtras().getInt("type");
				switch (type) {
					case Constants.BINGIN_CONN:
						Log.d(TAG, "Socket bingin conn");
						initSocketThread = new InitSocketThread();
						initSocketThread.start();

						break;

					default:
						break;
				}

			}
		}
	};
}
