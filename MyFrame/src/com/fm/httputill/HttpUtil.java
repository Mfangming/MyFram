package com.fm.httputill;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class HttpUtil {
	public static final String adddress = "http://58.249.57.253:8011/smarthome/";// 开发
	URL url;
	HttpURLConnection httpConn;
	private int readTime = 15000;
	private String param;
	public String method;
	public ResponseListener mListener;

	private static HttpUtil instance;

	/* 1:懒汉式，静态工程方法，创建实例 */
	public static HttpUtil getInstance() {
		if (instance == null) {
			instance = new HttpUtil();
		}
		return instance;
	}

	public HttpUtil() {
	}

	/**
	 * 异步线程
	 */
	class PostTread implements Runnable {

		@Override
		public void run() {
			String response = "";
			response = doResopns(method, param);
			if (null == handler) {
				return;
			}
			Message msg = handler.obtainMessage();
			msg.what = 0;
			msg.obj = response;
			handler.sendMessage(msg);
		}
	}

	/**
	 * @describe:调用此方法来做异步请求
	 * @param method
	 *            方法名称
	 * @param params
	 *            参数集
	 * @param responseListener
	 *            回调监听
	 */
	public void threadPost(String method, Map<String, String> params,ResponseListener responseListener) {
		this.mListener = responseListener;
		this.method = method;
		Set<String> keySet = params.keySet();
		String param = "";
		for (String key : keySet) {
			param += key + "=" + params.get(key) + "&";
		}
		param = param.substring(0, param.length() - 1);
		this.param = param;
		new Thread(new PostTread()).start();
	}

	/**
	 * @describe:连接服务器请求数据
	 * @param path
	 *            方法名称
	 * @param param
	 * @return 请求结果
	 */
	public String doResopns(String method, String param) {
		Log.d("http post " + method, param);
		String response = "";
		try {
			url = new URL(adddress + method);
			httpConn = (HttpURLConnection) url.openConnection();
			// 设置连接超时为10s
			httpConn.setConnectTimeout(10000);
			// 读取数据超时也是10s
			httpConn.setReadTimeout(readTime);
			httpConn.setChunkedStreamingMode(0);
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.connect();
			OutputStream outStrm = httpConn.getOutputStream();
			outStrm.write(param.getBytes());
			outStrm.flush();
			outStrm.close();
			// 读数据
			InputStream inStrm = httpConn.getInputStream();
			ByteArrayOutputStream bIn = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inStrm.read(buffer)) != -1) {
				bIn.write(buffer, 0, len);
			}
			response = bIn.toString();
			bIn.close();
			inStrm.close();
			int state = httpConn.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpConn.disconnect();
		}
		if (response == null) {
			Log.d("http response " + method, "" + response);
			response = "{\"status\":false,\"code\":\"0000\",\"msg\":\"连接超时\"}";
		} else if (response.equals("")) {
			Log.d("http response " + method, "-" + response);
			response = "{\"status\":false,\"code\":\"0001\",\"msg\":\"未知错误\"}";
		}
		Log.d("http return " + method, "" + response);
		return response;
	}

	/** 回主线程刷新 */
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (mListener != null) {
				mListener.onResponse((String) msg.obj);
			}
		}
	};

	public interface ResponseListener {

		public void onResponse(String respons);

	}

}