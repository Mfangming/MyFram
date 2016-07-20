package com.fm.popupwindow;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

public class IntentSharUtill {

	/**
	 * 获取应用市场intent
	 * 
	 * @param param
	 * @return
	 */
	public static Intent getIntent(String param) {
		Uri localUri = Uri.parse("market://details?id=" + param);
		return new Intent("android.intent.action.VIEW", localUri);
	}

	// 直接跳转不判断是否存在市场应用
	public static void start(Context paramContext, String paramString) {
		Uri localUri = Uri.parse(paramString);
		Intent localIntent = new Intent("android.intent.action.VIEW", localUri);
		localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		paramContext.startActivity(localIntent);
	}

	/**
	 * 跳转到应用市场
	 * 
	 * @param paramContext
	 * @param paramIntent
	 * @return
	 */
	public static boolean judge(Context paramContext, Intent paramIntent) {
		List<ResolveInfo> localList = paramContext.getPackageManager()
				.queryIntentActivities(paramIntent,
						PackageManager.GET_INTENT_FILTERS);
		if ((localList != null) && (localList.size() > 0)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 短信分享
	 * 
	 * @param smsBody
	 *            分享内容
	 */
	private void sendSMS(Context paramContext, String smsBody) {

		Uri smsToUri = Uri.parse("smsto:");

		Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);

		intent.putExtra("sms_body", smsBody);

		paramContext.startActivity(intent);

	}
}
