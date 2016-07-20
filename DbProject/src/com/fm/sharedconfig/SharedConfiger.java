package com.fm.sharedconfig;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences 类
 * 
 */
public class SharedConfiger {

	public static final String SSOToken = "SSOToken";
	public static final String NativeLogin = "NativeLogin"; //
	public static final String UserRollNames = "UserRollNames";
	public static final String BIANHAO = "BIANHAO";
	public static final String COOKIE = "COOKIE";
	public static final String COOKIE1 = "COOKIE1";

	public static final String USERICON = "USERICON";// 用户头像

	public static final String CURAPNID = "CURAPNID";// 记录下apn修改之前的APN ID
														// 为了退出oa的时候 能够帮用户复原

	public static final String NEEDSHOW = "NEEDSHOW"; // 是否需要展示同步对话框 false 为 需要

	public static final String NEEDSHOW_ORG = "NEEDSHOW_ORG"; // 是否需要展示同步对话框
																// false 为 需要
	// oa登录名—手机号码
	public static final String LOGINNANEOA = "loginNaneOa";
	// oa登录名—手机号码
	public static final String LOGINNAMEOA = "loginNameOa";
	// oa用于保存上一次账户名信息
	public static final String LASTLOGINNANEOA = "lastloginNameOa";
	// oa是否记住密码
	public static final String REMPASSWORDOA = "falseOa";
	// oa 密码
	public static final String PASSWORDOA = "passWordOa";
	// UserID
	public static final String USERIDOA = "userIdOa";

	public static final String IsRec = "IsRec";// 是否可以看见 收文列表

	// 文件名
	public static final String SHAREDNAME = "preferencesSelf";

	// 登录名—手机号码
	public static final String LOGINNANE = "loginNane";
	// 登录名—手机号码
	public static final String LOGINNAME = "loginName";
	// 用于保存上一次账户名信息
	public static final String LASTLOGINNANE = "lastloginName";

	// 密码
	public static final String PASSWORD = "passWord";
	// 密码
	public static final String PASSWORDREMEBER = "passWordhide";
	// 是否记住密码
	public static final String REMPASSWORD = "false";
	// UserID
	public static final String USERID = "userId";
	public static final String USERName = "username";

	public static final String DeptID = "DeptID";
	public static final String deptName = "deptName";

	// 后台Ip地址
	public static final String WEBSERVICEIP = "ip";
	// OA 后台Ip地址
	public static final String WEBSERVICEIPForOA = "ipforoa";

	// 当前版本号
	public static final String CURRENTVERSION = "currentVersion";

	// 提交ip
	public static final String NATIVEOPERATORID = "NativeOperatorId";

	public static final String SERVERCURTIME = "serviceCurrentTime";

	public static final String ISLOGIN = "";
	public static final String ISLOGINOA = "Oa";

	public static final String LASTLOGINTIME = "";
	public static final String LbsLocationCache = "LbsLocationCache";

	public static ArrayList<String> sharedList = new ArrayList<String>();

	static {
		sharedList.add(SHAREDNAME);
		sharedList.add(LOGINNANE);
		sharedList.add(PASSWORD);
		sharedList.add(USERID);
		sharedList.add(WEBSERVICEIP);
		sharedList.add(CURRENTVERSION);
		sharedList.add(NATIVEOPERATORID);
		sharedList.add(SERVERCURTIME);
		sharedList.add(ISLOGIN);
		sharedList.add(LASTLOGINTIME);

	}

	/**
	 * 删除值
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 */
	public static void removeKey(Context context, String key) throws Exception {
		SharedPreferences settings = getSharedSetting(context);
		if (settings.contains(key)) {
			settings.edit().remove(key).commit();
		} else {
			throw new Exception("文件里不存在该key" + key);
		}
	}

	/**
	 * 获取配置文件
	 * 
	 * @param _ctx
	 * @return
	 */
	private static SharedPreferences getSharedSetting(Context _ctx) {
		return _ctx.getSharedPreferences(SHAREDNAME,
				Context.MODE_PRIVATE | Context.MODE_WORLD_WRITEABLE | Context.MODE_WORLD_READABLE);
	}

	public static void saveString(Context context, String key, String strValue) {
		SharedPreferences settings = getSharedSetting(context);
		settings.edit().putString(key, strValue).commit();
	}

	public static String getString(Context context, String key) {
		SharedPreferences settings = getSharedSetting(context);
		return settings.getString(key, "");
	}

	public static void saveBoolean(Context context, String key, boolean blnValue) {
		SharedPreferences settings = getSharedSetting(context);
		settings.edit().putBoolean(key, blnValue).commit();
	}

	public static boolean getBoolean(Context context, String key) {
		SharedPreferences settings = getSharedSetting(context);
		return settings.getBoolean(key, false);
	}

	public static int getIntValue(Context context, String key, int defaultInt) {
		SharedPreferences settings = getSharedSetting(context);
		return settings.getInt(key, defaultInt);

	}

	public static void saveIntValue(Context context, String key, int intValue) {
		SharedPreferences settings = getSharedSetting(context);
		settings.edit().putInt(key, intValue).commit();
	}

	public static long getLongValue(Context context, String key, long defaultLong) {
		SharedPreferences settings = getSharedSetting(context);
		return settings.getLong(key, defaultLong);

	}

	public static void saveLongValue(Context context, String key, Long saveLong) {
		SharedPreferences settings = getSharedSetting(context);
		settings.edit().putLong(key, saveLong).commit();
	}

	public static float getFloatValue(Context context, String key, float floatValue) {
		SharedPreferences settings = getSharedSetting(context);
		return settings.getFloat(key, floatValue);

	}

	public static void saveFloatValue(Context context, String key, float floatValue) {
		SharedPreferences settings = getSharedSetting(context);
		settings.edit().putFloat(key, floatValue).commit();
	}
}
