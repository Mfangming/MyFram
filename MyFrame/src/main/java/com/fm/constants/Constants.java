package main.java.com.fm.constants;

import java.io.File;

import android.os.Environment;

public class Constants {
	public static String CrashLogDir = Environment.getExternalStorageDirectory()
			+ File.separator + "CrashLog";
	public static String Username = "username";

	public static final String SERVICE_SEND = "com.fangming.servicesend";// service发送广播
	public static final String SERVICE_REC = "com.fangming.servicereceiver";// service发送广播
	public static final int BINGIN_CONN = 20;// 开始连接socket
	public static final int CONN_SUCCESS = 11;// 连接Socket成功
	public static final int CONN_FAIL = 12;// 连接Socket失败
	public static final int APP_CTL_HEAD = 3000;// 包头标识
	public static final int SERVER_SEND_PLAY = 2001;//服务端将要发送播放视频命令
}
