package main.java.com.fm.jpush;

import android.content.Context;
import cn.jpush.android.api.JPushInterface;

public class JpushUntil {

	/**
	 * 停止推送
	 * @param context
	 */
	public static void stopPush(Context context){
		JPushInterface.stopPush(context);
	}

	/**
	 * 恢复推送
	 * @param context
	 */
	public static void resumePush(Context context){
		JPushInterface.resumePush(context);
	}




}
