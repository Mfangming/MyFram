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

public class TestpopupActivity extends Activity implements OnClickListener {

	private String content = "sdhkjhskf";
	private TestpopupActivity _this;
	private Button btn_shar;
	private PopupWindow myPop;
	private View addview;
	private RelativeLayout layout;
	private GridView gridview;
	ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testpopup);
		_this = this;
		btn_shar = (Button) findViewById(R.id.btn_shar);
		initView();
		// 初始化ShareSDK
		// ShareSDK.initSDK(this);
		btn_shar.setOnClickListener(this);
	}

	private void initView() {
		layout = (RelativeLayout) findViewById(R.id.layout);
		LayoutInflater inflater = LayoutInflater.from(_this);
		addview = inflater.inflate(R.layout.pop_bottom, null);
		gridview = (GridView) addview.findViewById(R.id.gridview);
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("ItemImage", R.drawable.ssdk_oks_logo_qq);
		map1.put("ItemText", "QQ");
		lstImageItem.add(map1);

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("ItemImage", R.drawable.ssdk_oks_logo_wechat);
		map2.put("ItemText", "微信");
		lstImageItem.add(map2);

		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("ItemImage", R.drawable.ssdk_oks_logo_wechat);
		map3.put("ItemText", "短信");
		lstImageItem.add(map3);

		// 生成适配器的ImageItem <====> 动�?�数组的元素，两者一�?对应
		SimpleAdapter saImageItems = new SimpleAdapter(_this, lstImageItem,
				R.layout.griad_item, new String[] { "ItemImage", "ItemText" },
				new int[] { R.id.ItemImage, R.id.ItemText });
		// 添加并且显示
		gridview.setAdapter(saImageItems);
		// 添加消息处理
		gridview.setOnItemClickListener(new ItemClickListener());
	}

	// 当AdapterView被单�?(触摸屏或者键�?)，则返回的Item单击事件
	class ItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {
			HashMap<String, Object> item = (HashMap<String, Object>) parent
					.getItemAtPosition(position);
			if (position == 0) {
				getShareApps(_this, "com.tencent.mobileqq", content, position);
			} else if (position == 1) {
				getShareApps(_this, "com.tencent.mm", content, position);
			} else {
				getShareApps(_this, "com.android.mms", content, position);
			}
			Log.e("test", (String) item.get("ItemText"));
		}
	}

	/**
	 * 演示调用ShareSDK执行分享
	 *
	 * @param context
	 * @param platformToShare
	 *            指定直接分享平台名称（一旦设置了平台名称，则九宫格将不会显示�?
	 * @param showContentEdit
	 *            是否显示编辑�?
	 */
	// public static void showShare(Context context, String platformToShare,
	// boolean showContentEdit) {
	// OnekeyShare oks = new OnekeyShare();
	// oks.setSilent(showContentEdit);
	// if (platformToShare != null) {
	// oks.setPlatform(platformToShare);
	// }
	// // ShareSDK快捷分享提供两个界面第一个是九宫�? CLASSIC 第二个是SKYBLUE
	// oks.setTheme(OnekeyShareTheme.CLASSIC);
	// // 令编辑页面显示为Dialog模式
	// oks.setDialogMode();
	// // 在自动授权时可以禁用SSO方式
	// oks.disableSSOWhenAuthorize();
	// // oks.setAddress("12345678901"); //分享短信的号码和邮件的地�?
	// oks.setTitle("ShareSDK--Title");
	// // oks.setTitleUrl("http://mob.com");
	// oks.setText("访客姓名：刘强\n访客密码�?123456\n发证日期�?2015�?12�?13日\n发证时间�?14�?25�?25 \n�?1、此通行证在12小时内有�?   \n2、在大门口机或业主单元门口机各一次有效\n 3、在输入密码前加�?*”号和�??#”号�?");
	// // oks.setImagePath(url); // 分享sdcard目录下的图片
	// // oks.setImageUrl(randomPic()[0]);
	// oks.setUrl("http://www.mob.com"); // 微信不绕过审核分享链�?
	// // oks.setFilePath("/sdcard/test-pic.jpg");
	// // //filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提�?
	// oks.setComment("分享"); // 我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
	// oks.setSite("ShareSDK"); // QZone分享完之后返回应用时提示框上显示的名�?
	// oks.setSiteUrl("http://mob.com");// QZone分享参数
	// oks.setVenueName("ShareSDK");
	// oks.setVenueDescription("This is a beautiful place!");
	// oks.setShareFromQQAuthSupport(false);
	// // 将快捷分享的操作结果将�?�过OneKeyShareCallback回调
	// // oks.setCallback(new OneKeyShareCallback());
	// // 去自定义不同平台的字段内�?
	// // oks.setShareContentCustomizeCallback(new
	// // ShareContentCustomizeDemo());
	// // 在九宫格设置自定义的图标
	// // Bitmap enableLogo = BitmapFactory.decodeResource(
	// // context.getResources(), R.drawable.ic_launcher);
	// // Bitmap disableLogo = BitmapFactory.decodeResource(
	// // context.getResources(), R.drawable.ic_launcher);
	// // String label = "ShareSDK";
	// // OnClickListener listener = new OnClickListener() {
	// // public void onClick(View v) {
	// //
	// // }
	// // };
	// // oks.setCustomerLogo(enableLogo, disableLogo, label, listener);
	//
	// // 为EditPage设置�?个背景的View
	// // oks.setEditPageBackground(getPage());
	// // 隐藏九宫格中的新浪微�?
	// // oks.addHiddenPlatform(SinaWeibo.NAME);
	//
	// // String[] AVATARS = {
	// // "http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg",
	// // "http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339485237265.jpg",
	// //
	// "http://diy.qqjay.com/u/files/2012/0523/f466c38e1c6c99ee2d6cd7746207a97a.jpg",
	// //
	// "http://diy.qqjay.com/u2/2013/0422/fadc08459b1ef5fc1ea6b5b8d22e44b4.jpg",
	// // "http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339510584349.jpg",
	// //
	// "http://diy.qqjay.com/u2/2013/0401/4355c29b30d295b26da6f242a65bcaad.jpg"
	// // };
	// // oks.setImageArray(AVATARS); //腾讯微博和twitter用此方法分享多张图片，其他平台不可以
	//
	// // 启动分享
	// oks.show(context);
	// }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_shar:
				// showShare(this, null, false);

				// Intent intent=new Intent(Intent.ACTION_SEND);
				// intent.setType("text/plain");
				// intent.putExtra(Intent.EXTRA_SUBJECT,"分享");
				// intent.putExtra(Intent.EXTRA_TEXT,
				// "我正在浏览这�?,觉得真不�?,推荐给你哦~ 地址:"+"http://www.google.com.hk/");
				// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// startActivity(Intent.createChooser(intent,
				// "share"));,"com.android.mms"

				// getShareApps(this, "com.tencent.mobileqq");
				// getpop(layout);
				// Intent i = getIntent("com.tencent.mobileqq");
				// Intent i = getIntent("com.tencent.mm");
				Intent i = getIntent("com.android.mms");
				boolean b = judge(_this, i);
				if (b == false) {
					startActivity(i);
				}
				break;
			default:
				break;
		}

	}

	public static Intent getIntent(String param) {
		Uri localUri = Uri.parse("market://details?id=" + param);
		return new Intent("android.intent.action.VIEW", localUri);
	}

	// 直接跳转不判断是否存在市场应�?
	public static void start(Context paramContext, String paramString) {
		Uri localUri = Uri.parse(paramString);
		Intent localIntent = new Intent("android.intent.action.VIEW", localUri);
		localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		paramContext.startActivity(localIntent);
	}

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

	private void getpop(View v) {
		if (myPop == null) {
			myPop = new PopupWindow(addview, LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT, true);
		}
		myPop.setTouchable(true);
		myPop.setFocusable(true);
		// �?要设置一下此参数，点击外边可消失
		myPop.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击窗口外边窗口消失
		myPop.setOutsideTouchable(true);
		myPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
	}

	public void getShareApps(Context context, String type, String content,
							 int position) {
		boolean found = false;
		List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();
		Intent intent = new Intent(Intent.ACTION_SEND, null);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setType("text/plain");
		PackageManager pManager = context.getPackageManager();
		mApps = pManager.queryIntentActivities(intent,
				PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
		if (null != mApps && mApps.size() > 0) {
			for (ResolveInfo info : mApps) {
				if (info.activityInfo.packageName.toLowerCase().contains(type)
						|| info.activityInfo.processName.toLowerCase()
						.contains(type)) {
					intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
					intent.putExtra(Intent.EXTRA_TEXT, content);
					// intent.setPackage(info.activityInfo.packageName);
					System.out.println(info.activityInfo.packageName);
					System.out.println(info.activityInfo.name);
					intent.setComponent(new ComponentName(
							info.activityInfo.packageName,
							info.activityInfo.name));
					found = true;
					break;
				}
			}
		}
		if (!found) {
			if (position == 0) {
				Toast.makeText(_this, "您暂时没有安装腾讯QQ，请先安装再分享", Toast.LENGTH_SHORT)
						.show();
				Intent i = IntentSharUtill.getIntent("com.tencent.mobileqq");
				boolean b = IntentSharUtill.judge(_this, i);
				if (b == false) {
					startActivity(i);
				}
			} else if (position == 1) {
				Toast.makeText(_this, "您暂时没有安装微信，请先安装再分�?", Toast.LENGTH_SHORT)
						.show();
				Intent i = IntentSharUtill.getIntent("com.tencent.mm");
				boolean b = IntentSharUtill.judge(_this, i);
				if (b == false) {
					startActivity(i);
				}
			} else {
				Toast.makeText(_this, "您暂时没有安装短信，请先安装再分�?", Toast.LENGTH_SHORT)
						.show();
				// Intent i = IntentSharUtill.getIntent("com.android.mms");
				// boolean b = IntentSharUtill.judge(_this, i);
				// if (b == false) {
				// startActivity(i);
				// }
			}
			return;
		}
		startActivity(Intent.createChooser(intent, "Select"));
	}

}
