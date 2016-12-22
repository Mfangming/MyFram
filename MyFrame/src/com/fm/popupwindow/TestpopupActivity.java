package com.fm.popupwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fangming.myframwork.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

//public class TestpopupActivity extends Activity implements OnClickListener {

//	private String content = "sdhkjhskf";
//	private TestpopupActivity _this;
//	private Button btn_shar;
//	private PopupWindow myPop;
//	private View addview;
//	private RelativeLayout layout;
//	private GridView gridview;
//	ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_testpopup);
//		_this = this;
//		btn_shar = (Button) findViewById(R.id.btn_shar);
//		initView();
//		// 初始化ShareSDK
//		// ShareSDK.initSDK(this);
//		btn_shar.setOnClickListener(this);
//	}
//
//	private void initView() {
//		layout = (RelativeLayout) findViewById(R.id.layout);
//		LayoutInflater inflater = LayoutInflater.from(_this);
//		addview = inflater.inflate(R.layout.pop_bottom, null);
//		gridview = (GridView) addview.findViewById(R.id.gridview);
//		HashMap<String, Object> map1 = new HashMap<String, Object>();
//		map1.put("ItemImage", R.drawable.ssdk_oks_logo_qq);
//		map1.put("ItemText", "QQ");
//		lstImageItem.add(map1);
//
//		HashMap<String, Object> map2 = new HashMap<String, Object>();
//		map2.put("ItemImage", R.drawable.ssdk_oks_logo_wechat);
//		map2.put("ItemText", "微信");
//		lstImageItem.add(map2);
//
//		HashMap<String, Object> map3 = new HashMap<String, Object>();
//		map3.put("ItemImage", R.drawable.ssdk_oks_logo_wechat);
//		map3.put("ItemText", "短信");
//		lstImageItem.add(map3);
//
//		// 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
//		SimpleAdapter saImageItems = new SimpleAdapter(_this, lstImageItem,
//				R.layout.griad_item, new String[] { "ItemImage", "ItemText" },
//				new int[] { R.id.ItemImage, R.id.ItemText });
//		// 添加并且显示
//		gridview.setAdapter(saImageItems);
//		// 添加消息处理
//		gridview.setOnItemClickListener(new ItemClickListener());
//	}
//
//
//	class ItemClickListener implements OnItemClickListener {
//
//		@Override
//		public void onItemClick(AdapterView<?> parent, View view, int position,
//								long id) {
//			HashMap<String, Object> item = (HashMap<String, Object>) parent
//					.getItemAtPosition(position);
//			if (position == 0) {
//				getShareApps(_this, "com.tencent.mobileqq", content, position);
//			} else if (position == 1) {
//				getShareApps(_this, "com.tencent.mm", content, position);
//			} else {
//				getShareApps(_this, "com.android.mms", content, position);
//			}
//			Log.e("test", (String) item.get("ItemText"));
//		}
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//			case R.id.btn_shar:
//				// showShare(this, null, false);
//
//				// Intent intent=new Intent(Intent.ACTION_SEND);
//				// intent.setType("text/plain");
//				// intent.putExtra(Intent.EXTRA_SUBJECT,"分享");
//				// intent.putExtra(Intent.EXTRA_TEXT,
//				// "我正在浏览推荐给你哦~ 地址:"+"http://www.google.com.hk/");
//				// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				// startActivity(Intent.createChooser(intent,
//				// "share"));,"com.android.mms"
//
//				// getShareApps(this, "com.tencent.mobileqq");
//				// getpop(layout);
//				// Intent i = getIntent("com.tencent.mobileqq");
//				// Intent i = getIntent("com.tencent.mm");
//				Intent i = getIntent("com.android.mms");
//				boolean b = judge(_this, i);
//				if (b == false) {
//					startActivity(i);
//				}
//				break;
//			default:
//				break;
//		}
//
//	}
//
//	public static Intent getIntent(String param) {
//		Uri localUri = Uri.parse("market://details?id=" + param);
//		return new Intent("android.intent.action.VIEW", localUri);
//	}
//
//	// 直接跳转不判断是否存在市场应用
//	public static void start(Context paramContext, String paramString) {
//		Uri localUri = Uri.parse(paramString);
//		Intent localIntent = new Intent("android.intent.action.VIEW", localUri);
//		localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		paramContext.startActivity(localIntent);
//	}
//
//	public static boolean judge(Context paramContext, Intent paramIntent) {
////		List<ResolveInfo> localList = paramContext.getPackageManager()
////				.queryIntentActivities(paramIntent,PackageManager.GET_INTENT_FILTERS);
////		if ((localList != null) && (localList.size() > 0)) {
////			return false;
////		} else {
//			return true;
////		}
//	}
//
//	private void getpop(View v) {
//		if (myPop == null) {
//			myPop = new PopupWindow(addview, LayoutParams.MATCH_PARENT,
//					LayoutParams.WRAP_CONTENT, true);
//		}
//		myPop.setTouchable(true);
//		myPop.setFocusable(true);
//		// 需要设置一下此参数，点击外边可消失
//		myPop.setBackgroundDrawable(new BitmapDrawable());
//		// 设置点击窗口外边窗口消失
//		myPop.setOutsideTouchable(true);
//		myPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
//	}
//
//	public void getShareApps(Context context, String type, String content,
//							 int position) {
//		boolean found = false;
//		List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();
//		Intent intent = new Intent(Intent.ACTION_SEND, null);
//		intent.addCategory(Intent.CATEGORY_DEFAULT);
//		intent.setType("text/plain");
//		PackageManager pManager = context.getPackageManager();
//		mApps = pManager.queryIntentActivities(intent,
//				PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
//		if (null != mApps && mApps.size() > 0) {
//			for (ResolveInfo info : mApps) {
//				if (info.activityInfo.packageName.toLowerCase().contains(type)
//						|| info.activityInfo.processName.toLowerCase()
//						.contains(type)) {
//					intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//					intent.putExtra(Intent.EXTRA_TEXT, content);
//					// intent.setPackage(info.activityInfo.packageName);
//					System.out.println(info.activityInfo.packageName);
//					System.out.println(info.activityInfo.name);
//					intent.setComponent(new ComponentName(
//							info.activityInfo.packageName,
//							info.activityInfo.name));
//					found = true;
//					break;
//				}
//			}
//		}
//		if (!found) {
//			if (position == 0) {
//				Toast.makeText(_this, "您暂时没有安装腾讯QQ，请先安装再分享", Toast.LENGTH_SHORT)
//						.show();
//				Intent i = IntentSharUtill.getIntent("com.tencent.mobileqq");
//				boolean b = IntentSharUtill.judge(_this, i);
//				if (b == false) {
//					startActivity(i);
//				}
//			} else if (position == 1) {
//				Toast.makeText(_this, "您暂时没有安装微信，请先安装再分享", Toast.LENGTH_SHORT)
//						.show();
//				Intent i = IntentSharUtill.getIntent("com.tencent.mm");
//				boolean b = IntentSharUtill.judge(_this, i);
//				if (b == false) {
//					startActivity(i);
//				}
//			} else {
//				Toast.makeText(_this, "您暂时没有安装短信，请先安装再分享", Toast.LENGTH_SHORT)
//						.show();
//				// Intent i = IntentSharUtill.getIntent("com.android.mms");
//				// boolean b = IntentSharUtill.judge(_this, i);
//				// if (b == false) {
//				// startActivity(i);
//				// }
//			}
//			return;
//		}
//		startActivity(Intent.createChooser(intent, "Select"));
//	}

//}
