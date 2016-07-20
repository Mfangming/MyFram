package com.fm.service;
//package com.fangming.service;
//
//import java.io.BufferedInputStream;
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.InetSocketAddress;
//import java.net.Socket;
//import java.net.SocketAddress;
//import java.net.SocketException;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.JSONStringer;
//
//import com.fangming.utill.WifiAdmin;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
//import android.annotation.SuppressLint;
//import android.app.ActivityManager;
//import android.app.Service;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.net.NetworkInfo;
//import android.net.wifi.WifiInfo;
//import android.net.wifi.WifiManager;
//import android.os.Binder;
//import android.os.IBinder;
//import android.util.Log;
//
//public class AliveConnService extends Service {
//	private static final String TAG = "AliveConnService";
//	private String ipAdd = "192.168.1.1";
//	private int port = 10001;
//	String respone;
//	private Socket mSocket;
//	private OutputStream outStream;
//	private InputStream inStream;
//	private Boolean isAccepte = true;
//	private final static int PACKATHEADER_SIGN_LENGTH = 2;// AJB包头标识长度
//	private final static int PACKATHEADER_TYPE_LENGTH = 4;// 包类型长度4byte
//	private final static int PACKATHEADER_DATALEN_LENGTH = 4;// 包长数据长度标识4byte
//	private final static int PACKATHEADER_LENGTH = PACKATHEADER_SIGN_LENGTH
//			+ PACKATHEADER_TYPE_LENGTH + PACKATHEADER_DATALEN_LENGTH;// 包头
//	private ReadThread readThread;
//	private InitSocketThread initSocketThread;
//	public WifiAdmin wifiM;
//	Context context;
//
//	@Override
//	public void onCreate() {
//		super.onCreate();
//		Log.e(TAG, "---->" + "creatservice");
//		IntentFilter myFilter = new IntentFilter();
//		myFilter.addAction(Constants.SERVICE_RECEIVE);
//		this.registerReceiver(ResponBR, myFilter);
//		context = getApplication();
//		wifiM = new WifiAdmin(context);
//		initSocketThread = new InitSocketThread();
//		initSocketThread.start();
//	}
//
//	@Override
//	public void onStart(Intent intent, int startId) {
//		super.onStart(intent, startId);
//	}
//
//	@Override
//	public int onStartCommand(Intent intent, int flags, int startId) {
//		Log.e(TAG, "---->" + "startservice");
//		return super.onStartCommand(intent, flags, startId);
//	}
//
//	/**
//	 * 初始化socket线程
//	 */
//	class InitSocketThread extends Thread {
//
//		@Override
//		public void run() {
//			super.run();
//			while (!wifiM.isConn(context)) {
//				if (!wifiM.isConn(context)) {
//					wifiM.connect(Constants.ssid, Constants.pwd);
//				}
//			}
//			initSocket();
//		}
//	}
//
//	/**
//	 * 初始化socket
//	 */
//	private void initSocket() {
//		Log.e(TAG, "---->创建socket成功");
//		try {
//			mSocket = new Socket(ipAdd, port);
//			mSocket.setSoTimeout(10 * 1000);// 10秒超时
//			if (mSocket.isConnected()) {
//				Log.e(TAG, "---->连接成功");
//				outStream = mSocket.getOutputStream();
//				inStream = mSocket.getInputStream();
//				serviceSend(Constants.SERVICE_SEND, Constants.WIFI_SUCCESS);
//				readThread = new ReadThread();
//				readThread.start();
//			}
//		} catch (SocketException e) {
//			serviceSend(Constants.SERVICE_SEND, Constants.WIFI_FAIL);
//			e.printStackTrace();
//		} catch (IOException e) {
//			serviceSend(Constants.SERVICE_SEND, Constants.WIFI_FAIL);
//			e.printStackTrace();
//		}
//	}
//
//	private void serviceSend(String action, int type) {
//		Intent intent = new Intent();
//		intent.setAction(action);
//		intent.putExtra("type", type);
//		sendBroadcast(intent);
//	}
//
//	/**
//	 * 断开连接
//	 */
//	public void disConnected() {
//		try {
//			isAccepte = false;
//			if (null != readThread && !readThread.isInterrupted()) {
//				readThread.interrupt();
//			}
//			if (null != mSocket) {
//				Log.e(TAG, "---->断开连接");
//				if (mSocket.isOutputShutdown()) {
//					mSocket.shutdownOutput();
//				}
//				if (mSocket.isInputShutdown()) {
//					mSocket.shutdownInput();
//				}
//				mSocket.close();
//				mSocket = null;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * @param jsonObject
//	 * @param id
//	 * @return 以byte[]返回上传的数据
//	 * @throws JSONException
//	 */
//	private byte[] getCMD(byte[] data, int id, byte[] len) throws JSONException {
//		// 3000
//		int length = data.length;
//		byte[] buffer = new byte[PACKATHEADER_LENGTH + length];// 创建一个定长byte数组
//		System.arraycopy(Constants.APPREQUEST_HEAD, 0, buffer, 0, 2);// 写入3000
//		switch (id) {
//		case Constants.APPREQUEST_TYPE_LOGWG: // 写入登录AP命令
//			byte[] type = { 0, 0, 0x21, (byte) 0x84 };
//			System.arraycopy(type, 0, buffer, 2, 4);
//			break;
//		case Constants.APPREQUEST_TYPE_ETWIFI: // 修改wifi命令
//			byte[] type2 = { 0, 0, 0x21, (byte) 0x8A };
//			System.arraycopy(type2, 0, buffer, 2, 4);
//			break;
//		case Constants.APPREQUEST_TYPE_ETTDSTART: // 修改无线通道
//			byte[] type3 = { 0, 0, (byte) 0x21, (byte) 0x8B };
//			System.arraycopy(type3, 0, buffer, 2, 4);
//			break;
//		case Constants.APPREQUEST_TYPE_EXIT: // 写入AP命令
//			byte[] type1 = { 0, 0, 0x21, (byte) 0x8C };
//			System.arraycopy(type1, 0, buffer, 2, 4);
//			break;
//		case Constants.APPREQUEST_TYPE_SCAEQP: // 扫描设备命令
//			byte[] type4 = { 0, 0, 0x21, (byte) 0x89 };
//			System.arraycopy(type4, 0, buffer, 2, 4);
//			break;
//		case Constants.APPREQUEST_TYPE_DELEQP: // 删除设备
//			byte[] type5 = { 0, 0, 0x21, (byte) 0x88 };
//			System.arraycopy(type5, 0, buffer, 2, 4);
//			break;
//		case Constants.APPREQUEST_TYPE_SCECON: // 手机app发�?情景控制命令
//			byte[] type6 = { 0, 0, 0x21, (byte) 0x87 };
//			System.arraycopy(type6, 0, buffer, 2, 4);
//			break;
//		case Constants.APPREQUEST_TYPE_SIGNCON: // 手机app发�?单控命令
//			byte[] type7 = { 0, 0, 0x21, (byte) 0x86 };
//			System.arraycopy(type7, 0, buffer, 2, 4);
//			break;
//		case Constants.APPREQUEST_TYPE_REGEQP: // 手机app发送注册新设备命�??
//			byte[] type8 = { 0, 0, 0x21, (byte) 0x85 };
//			System.arraycopy(type8, 0, buffer, 2, 4);
//			break;
//		case Constants.APPREQUEST_TYPE_EDITWSNS: // 手机app发送修改WS/NS地址及端口号命令
//			byte[] type9 = { 0, 0, 0x21, (byte) 0x8d };
//			System.arraycopy(type9, 0, buffer, 2, 4);
//			break;
//		default:
//			break;
//		}
//
//		System.arraycopy(len, 0, buffer, 6, 4);
//		System.arraycopy(data, 0, buffer, 10, length);
//		return buffer;
//	}
//
//	/**
//	 * 发送命令！！
//	 * 
//	 * @param cmd
//	 */
//	private synchronized void sendCMD(byte[] cmd) {
//		try {
//			if (mSocket.isConnected()) {
//				outStream.write(cmd);
//				outStream.flush();
//				Log.e(TAG, "start sendCMD SUCCESS>>>>>>>");
//			} else {
//				Log.e(TAG, "start sendCMD FAILe>>>>>>>");
//			}
//
//		} catch (Exception e) {
//			Log.e(TAG, "ping命令异常-->重连");
//			e.printStackTrace();
//		}
//	}
//
//	private boolean startPing(String ip) {
//		Log.e(TAG, "startPing...");
//		boolean success = false;
//		Process p = null;
//
//		try {//
//			p = Runtime.getRuntime().exec("ping -c 1 -i 0.2 -W 1 " + ip);
//			int status = p.waitFor();
//			if (status == 0) {
//				success = true;
//			} else {
//				success = false;
//			}
//		} catch (IOException e) {
//			success = false;
//		} catch (InterruptedException e) {
//			success = false;
//		} finally {
//			p.destroy();
//		}
//
//		return success;
//	}
//
//	/**
//	 * 接收解析协议包
//	 * 
//	 * @param dis
//	 * @throws IOException
//	 */
//	@SuppressLint("NewApi")
//	public void recv(DataInputStream dis) throws IOException {
//		byte[] head = new byte[PACKATHEADER_LENGTH];// 10个字�??
//		Log.e(TAG, "---->>接收数据：" + dis.available());
//		try {
//			dis.readFully(head, 0, PACKATHEADER_LENGTH);
//		} catch (IOException e) {
//			return;
//		}
//
//		// 读取前两个字节AJB包标识head[]
//		byte[] sign = new byte[2];
//		sign = Arrays.copyOfRange(head, 0, 2);
//		if (ByteConvertUtils.bytesToInt2(sign) != Constants.APP_CTL_HEAD) {
//			return;
//		}
//		// 读取4byte包类型
//		byte[] type = new byte[4];
//		type = Arrays.copyOfRange(head, 2, 6);
//		// 读取4byte内容长度
//		byte[] datalen = new byte[4];
//		datalen = Arrays.copyOfRange(head, 6, 10);
//
//		int nType = ByteConvertUtils.bytesToInt(type);
//		int nDatalen = ByteConvertUtils.bytesToInt(datalen);
//		String strData = "";
//		byte[] data = null;
//		if (nDatalen > 0) {
//			// 接收指定长度的内容
//			data = new byte[nDatalen];
//			dis.readFully(data, 0, nDatalen);
//			strData = new String(data);
//			Log.e(TAG, "---->返回信息�?? + strData);
//		}
//		Log.e(TAG, "nType:" + nType + "  nDatalen:" + nDatalen);
//		Intent intent;
//		switch (nType) {// App登陆/查询网关
//		case Constants.APPRETURN_TYPE_LOGWG:
//			Log.e(TAG, "---->家电网关向APP返回登陆信息：" + strData);
//			// 刷新ui
//			sendBroad(data, Constants.APPRETURN_TYPE_LOGWG);
//			break;
//		case Constants.APPRETURN_TYPE_ETWIFI:// 更改网关WIFI连接设置
//			Log.e(TAG, "---->更改网关WIFI连接设置:" + strData);
//			// 刷新ui
//			sendBroad(data, Constants.APPRETURN_TYPE_ETWIFI);
//			break;
//		case Constants.APPRETURN_TYPE_ETTDEND:// 更改无线通道
//			Log.e(TAG, "---->更改无线通道:" + strData);
//			// 刷新ui
//			sendBroad(data, Constants.APPRETURN_TYPE_ETTDEND);
//			break;
//		case Constants.APPRETURN_TYPE_NOREGEQP:// 网关扫描设备功能
//			Log.e(TAG, "---->家电网关返回未注册设备列表：" + strData);
//			// 展示列表
//			sendBroad(data, Constants.APPRETURN_TYPE_NOREGEQP);
//			break;
//		case Constants.APPRETURN_TYPE_END:// 家电网关扫描未注册设备执行结束消�??
//			Log.e(TAG, "---->家电网关扫描未注册设备执行结束消息：" + strData);
//			// 展示列表
//			sendBroad(data, Constants.APPRETURN_TYPE_END);
//			break;
//		case Constants.APPRETURN_TYPE_EXIAP:// 网关返回AP模式
//			Log.e(TAG, "---->家电网关返回AP模式的应答：" + strData);
//			isAccepte = false;
//			sendBroad(data, Constants.APPRETURN_TYPE_EXIAP);
//			break;
//		case Constants.APPRETURN_TYPE_REGEQP:// 注册活动执行结束
//			Log.e(TAG, "---->家电网关上报注册活动执行结束消息�?? + strData);
//			sendBroad(data, Constants.APPRETURN_TYPE_REGEQP);
//			break;
//		case Constants.APPRETURN_TYPE_UPEQPINF:// 注册成功
//			Log.e(TAG, "---->家电网关上报家电设备的信息：" + strData);
//			sendBroad(data, Constants.APPRETURN_TYPE_UPEQPINF);
//			break;
//		case Constants.APPRETURN_TYPE_SCECON:// 家电网关上报情景控制执行结束消息
//			Log.e(TAG, "---->家电网关上报情景控制执行结束消息:" + strData);
//			sendBroad(data, Constants.APPRETURN_TYPE_SCECON);
//			break;
//		case Constants.APPRETURN_TYPE_TO:// 家电网关设备控制
//			Log.e(TAG, "---->家电网关设备控制:" + strData);
//			sendBroad(data, Constants.APPRETURN_TYPE_TO);
//			break;
//		case Constants.APPRETURN_TYPE_EDITWSNS:// 家电网关上报修改WS/NS地址及端口号执行结束消息
//			Log.e(TAG, "---->家电网关上报修改WS/NS地址及端口号执行结束消息:" + strData);
//			sendBroad(data, Constants.APPRETURN_TYPE_EDITWSNS);
//			break;
//		default:
//			Log.e(TAG, "---->不识别的命令�?? + strData);
//			break;
//		}
//	};
//
//	private void sendBroad(byte[] data, int type) {
//		Intent intent = new Intent();
//		intent.setAction(Constants.SERVICE_SEND);
//		intent.putExtra("data", data);
//		intent.putExtra("type", type);
//		sendBroadcast(intent);
//	}
//
//	@Override
//	public void onDestroy() {
//		disConnected();
//		Log.e(TAG, "---->service停止");
//		unregisterReceiver(ResponBR);
//		super.onDestroy();
//	}
//
//	private BroadcastReceiver ResponBR = new BroadcastReceiver() {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			JSONStringer jo = new JSONStringer();
//			if (Constants.SERVICE_RECEIVE.equals(intent.getAction())) {
//				int id = intent.getExtras().getInt("MYtype");
//				Log.e(TAG, "---->service接收到命令广�??id=" + id);
//				switch (id) {
//				case Constants.APPREQUEST_TYPE_LOGWG:// 登录ap
//					// str="{\"user\""+":\""+user+"\",\"password\""+":\""+passwrod+"\"}";
//					try {
//						jo.object();
//						jo.key("user");
//						jo.value(intent.getExtras().getString("user"));
//						jo.key("password");
//						jo.value(intent.getExtras().getString("passwrod"));
//						jo.endObject();
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					sendData(jo, Constants.APPREQUEST_TYPE_LOGWG);
//					break;
//				case Constants.APPREQUEST_TYPE_ETWIFI:// 修改wifi
//					try {
//						jo.object();
//						jo.key("terminal_code");
//						jo.value(intent.getExtras().getString("terminal_code")
//								+ "");
//						jo.key("type");
//						jo.value(intent.getExtras().getInt("type"));
//						jo.key("SSID_NAME");
//						jo.value(intent.getExtras().getString("SSID_NAME") + "");
//						jo.key("SECURITY_TYPE");
//						jo.value(intent.getExtras().getInt("SECURITY_TYPE"));
//						jo.key("SECURITY_KEY");
//						jo.value(intent.getExtras().getString("SECURITY_KEY"));
//						jo.endObject();
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					sendData(jo, Constants.APPREQUEST_TYPE_ETWIFI);
//					break;
//				case Constants.APPREQUEST_TYPE_ETTDSTART:// 修改网关
//					try {
//						jo.object();
//						jo.key("terminal_code");
//						jo.value(intent.getExtras().getString("terminal_code")
//								+ "");
//						jo.key("type");
//						jo.value(intent.getExtras().getInt("type"));
//						jo.key("rf_channel");
//						jo.value(intent.getExtras().getInt("rf_channel"));
//						jo.endObject();
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					sendData(jo, Constants.APPREQUEST_TYPE_ETTDSTART);
//					break;
//				case Constants.APPREQUEST_TYPE_EXIT:// �??��AP
//					try {
//						jo.object();
//						jo.key("terminal_code");
//						jo.value(intent.getExtras().getString("terminal_code")
//								+ "");
//						jo.key("type");
//						jo.value(intent.getExtras().getInt("type"));
//						jo.endObject();
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					sendData(jo, Constants.APPREQUEST_TYPE_EXIT);
//					break;
//				case Constants.APPREQUEST_TYPE_SCAEQP:// 扫描设备
//					try {
//						jo.object();
//						jo.key("terminal_code");
//						jo.value(intent.getExtras().getString("terminal_code")
//								+ "");
//						jo.key("type");
//						jo.value(intent.getExtras().getInt("type"));
//						jo.endObject();
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					sendData(jo, Constants.APPREQUEST_TYPE_SCAEQP);
//					break;
//				case Constants.APPREQUEST_TYPE_DELEQP:// 删除设备
//					try {
//						jo.object();
//						jo.key("terminal_code");
//						jo.value(intent.getExtras().getString("terminal_code")
//								+ "");
//						jo.endObject();
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					sendData(jo, Constants.APPREQUEST_TYPE_DELEQP);
//					break;
//				case Constants.APPREQUEST_TYPE_REGEQP:// 手机app发�?注册新设备命�??
//					WgDevice list = (WgDevice) intent
//							.getSerializableExtra("equipment_list");
//					StringBuilder sb = new StringBuilder()
//							.append("{\"terminal_code\""
//									+ ":\""
//									+ intent.getExtras().getString(
//											"terminal_code")
//									+ "\",\"type"
//									+ "\":"
//									+ intent.getExtras().getInt("type")
//									+ ",\"equipment_list\":{\"EQUIPMENTLIST\":[");
//					sb.append("{\"equipment_code\":\""
//							+ list.getEquipment_code() + "\",\"number\":\""
//							+ list.getType() + "\"}");
//					sb.append("]}}");
//					// �??��处理数据，然后发送数�??
//					try {
//						byte[] data = sb.toString().getBytes();
//						int length = data.length;
//						byte[] len = ByteConvertUtils.intToBytes(length);
//						byte[] buff = getCMD(data,
//								Constants.APPREQUEST_TYPE_REGEQP, len);
//						if (null != buff) {
//							sendCMD(buff);
//						}
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					break;
//				case Constants.WIFI_FAIL:// 重连
//					initSocketThread.interrupt();
//					mSocket = null;
//					outStream = null;
//					inStream = null;
//					new InitSocketThread().start();
//					break;
//				case Constants.APPREQUEST_TYPE_SIGNCON:// 单控模式
//					try {
//						jo.object();
//						jo.key("terminal_code");
//						jo.value(intent.getExtras().getString("terminal_code")
//								+ "");
//						jo.key("type");
//						jo.value(intent.getExtras().getInt("type") + "");
//						jo.key("equipment_code");
//						jo.value(intent.getExtras().getString("equipment_code")
//								+ "");
//						jo.key("number");
//						jo.value(intent.getExtras().getInt("number") + "");
//						jo.key("state");
//						jo.value(intent.getExtras().getInt("state"));
//						jo.endObject();
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					sendData(jo, Constants.APPREQUEST_TYPE_SIGNCON);
//					break;
//				case Constants.APPREQUEST_TYPE_EDITWSNS:// 修改ws/ns
//					try {
//						jo.object();
//						jo.key("terminal_code");
//						jo.value(intent.getExtras().getString("terminal_code")
//								+ "");
//						jo.key("type");
//						jo.value(intent.getExtras().getInt("type"));
//						jo.key("WS_address");
//						jo.value(intent.getExtras().getString("Wsaddress") + "");
//						jo.key("WS_port");
//						jo.value(intent.getExtras().getInt("WSport"));
//						jo.key("NS_address");
//						jo.value(intent.getExtras().getString("NSaddress"));
//						jo.key("NS_port");
//						jo.value(intent.getExtras().getInt("NSport"));
//						jo.endObject();
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					sendData(jo, Constants.APPREQUEST_TYPE_EDITWSNS);
//					break;
//				default:
//					break;
//				}
//
//			}
//		}
//	};
//
//	private void sendData(JSONStringer jo, int cmd) {
//		// �??��处理数据，然后发送数�??
//		try {
//			byte[] data = jo.toString().getBytes();
//			int length = data.length;
//			byte[] len = ByteConvertUtils.intToBytes(length);
//			byte[] buff = getCMD(data, cmd, len);
//			if (null != buff) {
//				sendCMD(buff);
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//	}
//
//	class ReadThread extends Thread {
//		@Override
//		public void run() {
//			super.run();
//			while (isAccepte) {
//				if (mSocket != null && mSocket.isConnected()) {
//					devide(new DataInputStream(
//							new BufferedInputStream(inStream)));
//				}
//			}
//		}
//	}
//
//	public synchronized void devide(DataInputStream ds) {
//		try {
//			if (ds.available() > 81) {
//				Log.e(TAG, ds.available() + "");
//			}
//			while (ds.available() != 0) {
//				recv(ds);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public class MyBinder extends Binder {
//
//		public AliveConnService getService() {
//			return AliveConnService.this;
//		}
//	}
//
//	private MyBinder myBinder = new MyBinder();
//
//	@Override
//	public IBinder onBind(Intent intent) {
//		return myBinder;
//	}
//
//	@Override
//	public boolean onUnbind(Intent intent) {
//		return super.onUnbind(intent);
//	}
//
//	@Override
//	public void onRebind(Intent intent) {
//		super.onRebind(intent);
//	}
//
//}
