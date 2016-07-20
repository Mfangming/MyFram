package com.fm.activity;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;

import com.fangming.myframwork.R;
import com.fm.utill.ByteConvertUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServerSocketActivity extends Activity {
	ServerSocket serverSocket;
	InitSocketThread mySocketThread;
	private Button btn_clic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serversocket);
		try {
			serverSocket = new ServerSocket(1821);
		} catch (IOException e) {
			System.out.println("ServerSocket启动失败!");
			e.printStackTrace();
		}
		System.out.println("ip:"+serverSocket.getLocalSocketAddress()+"----port: "+serverSocket.getLocalPort());
		System.out.println("服务器ip: "+serverSocket.getInetAddress());
		System.out.println("服务器搭建成�?************");

		btn_clic = (Button) findViewById(R.id.btn_clic);
		btn_clic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mySocketThread = new InitSocketThread(serverSocket);
				mySocketThread.start();
			}
		});
	}

	/**
	 * 初始化socket线程
	 */
	class InitSocketThread extends Thread {

		ServerSocket server;
		Socket socket;

		public InitSocketThread(ServerSocket server) {
			super();
			this.server = server;
			try {
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		@Override
		public void run() {
			super.run();
			while (true) {
				try {
					devide(new DataInputStream(new BufferedInputStream(
							socket.getInputStream())));
					Thread.sleep(3 * 1000);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public synchronized void devide(DataInputStream ds) {
		try {
			while (ds.available() != 0) {
				recv(ds);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接收解析协议�?
	 *
	 * @param dis
	 * @throws IOException
	 */
	public void recv(DataInputStream dis) throws IOException {
		byte[] head = new byte[10];//
		Log.e("test", "---->>接收数据长度 " + dis.available());
		try {
			dis.readFully(head, 0, 10);
		} catch (IOException e) {
			return;
		}

		// 读取前两个字head
		byte[] sign = new byte[2];
		sign = Arrays.copyOfRange(head, 0, 2);
		if (ByteConvertUtils.bytesToInt2(sign) != 3000) {
			return;
		}
		// 读取4byte包类�?
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
			// 接收指定长度的内字符
			data = new byte[nDatalen];
			dis.readFully(data, 0, nDatalen);
			strData = new String(data);
			Log.e("test", "---->返回信息" + strData);
		}
		Log.e("test", "nType:" + nType + "  nDatalen:" + nDatalen);
		// switch (nType) {// App登陆/查询网关
		// case Constants.APPRETURN_TYPE_LOGWG:
		// Log.e(TAG, "---->家电网关向APP返回登陆信息" + strData);
		// // 刷新ui
		// sendBroad(data, Constants.APPRETURN_TYPE_LOGWG);
		// break;
		// default:
		// break;
	}
}
