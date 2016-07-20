package com.fm.httputill;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;

public class GsonHttpComExchange {
	private Context context;
	private static GsonHttpComExchange gsonTemplate;

	public static GsonHttpComExchange getInstance(Context context) {
		if (gsonTemplate == null) {
			gsonTemplate = new GsonHttpComExchange(context);
		}
		return gsonTemplate;
	}

	public GsonHttpComExchange(Context context) {
		super();
		this.context = context;
	}

	public <T> Respones<T> sendRequstObject(String obj, Class<T> clazz) {
		Respones<T> data = formGson(obj,clazz);
		return data;
	}

	public <T> Respones<T> formGson(String json, Class<T> clazz) {
		String reponseBody = null;
		Respones<T> resp = new Respones<T>();
		T t;
		Gson gson = new Gson();
		if (json == null || json.equals("")) {
			return null;
		} else {
			try {
				JSONObject jsonObject = new JSONObject(json);
				String msg = jsonObject.getString("msg");
				resp.setMsg(msg);
				String code = jsonObject.getString("code");
				resp.setCode(code);
				Boolean status = jsonObject.getBoolean("status");
				resp.setStatus(status);
				String dataStr = null;
				if (status.booleanValue()) {
					dataStr = jsonObject.getString("data");
					reponseBody = dataStr;
				} else {
					return resp;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (String.class == clazz) {
				t = (T) reponseBody;
				resp.setData(t);
			} else {
				t = gson.fromJson(reponseBody, clazz);
				resp.setData(t);
			}
		}
		return resp;
	}

}
