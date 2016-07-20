package com.fm.qxtapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fm.dbmanager.SqliteHelper;
import com.fm.entity.User;
import com.fm.file.FileUtil;
import com.fm.httputill.GsonHttpComExchange;
import com.fm.httputill.HttpUtil;
import com.fm.volly.VollyUtill;

import android.app.Application;
import android.os.Environment;

public class MyApp extends Application {
	private static MyApp mInstance = null;
	public SqliteHelper dbHelper;
	public List<Class<?>> tableClassNameList;
	// 数据库名称
	public final static String DBNAME = "qxt.db";
	// 当前版本的数据库，如果数据库需要删除，DataBaseVersion 累加
	public final static int DataBaseVersion = 1;
	public static String filepath = Environment.getExternalStorageDirectory()
			+ File.separator + "TestCamora";

	public VollyUtill vollUtill;

	public HttpUtil mHttpUtill;

	public GsonHttpComExchange gsonHttpComExchange;

	@Override
	public void onCreate() {
		super.onCreate();
		this.mInstance = this;
		tableClassNameList = new ArrayList<Class<?>>();
		tableClassNameList.add(User.class);
		dbHelper = new SqliteHelper(this, DBNAME, DataBaseVersion,
				tableClassNameList);
		dbHelper.open();
		vollUtill=VollyUtill.getInstance();
		mHttpUtill=HttpUtil.getInstance();
		gsonHttpComExchange=GsonHttpComExchange.getInstance(this);
//		JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
//		JPushInterface.init(this); // 初始化 JPush

		// 参数定义
		// alias
		// "" （空字符串）表示取消之前的设置。
		// 每次调用设置有效的别名，覆盖之前的设置。
		// 有效的别名组成：字母（区分大小写）、数字、下划线、汉字。
		// 限制：alias 命名长度限制为 40 字节。（判断长度需采用UTF-8编码）
		// callback
		// 在TagAliasCallback 的 gotResult 方法，返回对应的参数 alias,
		// tags。并返回对应的状态码：0为成功，其他返回码请参考错误码定义。
//		JPushInterface.setAlias(this, "mingfang", new TagAliasCallback() {
//
//			@Override
//			public void gotResult(int responseCode, String alias,
//					Set<String> tags) {
//				Log.e("tags", responseCode + "");
//				if (responseCode == 0) {
//					Log.e("tags", "成功");
//				}
//
//			}
//		});

		try {
			FileUtil.createFiles(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		// 日志系统
//		CrashHandler crashHandler = CrashHandler.getInstance();
//		crashHandler.init(this);

	}

	public static MyApp getInstance() {
		return mInstance;
	}

}
