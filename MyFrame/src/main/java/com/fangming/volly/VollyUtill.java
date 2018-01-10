package main.java.com.fangming.volly;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;

public class VollyUtill {
	public static final String adddress = "http://58.249.57.253:8011/smarthome/";

	public static VollyResponseListener mListener;

	private static VollyUtill instance;

	/* 1:懒汉式，静态工程方法，创建实例 */
	public static VollyUtill getInstance() {
		if (instance == null) {
			instance = new VollyUtill();
		}
		return instance;
	}

	public void connetion(Context context, String methord, String patam,VollyResponseListener responseListener) {
		this.mListener=responseListener;
		String ip = adddress + methord + "?" + patam;
		doRequest(context, ip);
	}

	public static void doRequest(Context context, String url) {
		RequestQueue mQueue = Volley.newRequestQueue(context);
		StringRequest stringRequest = new StringRequest(
				url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						mListener.onResponse(response);
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				String str="{\"status\":false,\"code\":\"0000\",\"msg\":\""+error.getMessage()+"\"}";
				mListener.onResponse(str);
			}
		});
		mQueue.add(stringRequest);
	}

	public interface VollyResponseListener {

		public void onResponse(String respons);

	}

}
