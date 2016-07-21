package com.fangming.myapp;

import android.app.Application;
import android.os.Environment;

import com.fangming.jsoup.GsonHttpComExchange;
import com.fangming.jsoup.JsoupUtil;
import com.fangming.volly.ImageManager;
import com.fm.dbmanager.SqliteHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by fangming on 2016-4-26.
 */
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

//    public BmobHttp mBmobHttp;
    public JsoupUtil jsoupUtil;
    public GsonHttpComExchange gsonHttpComExchange;

    public ImageManager imageManager;


    @Override
    public void onCreate() {
        super.onCreate();
        this.mInstance = this;
        tableClassNameList = new ArrayList<Class<?>>();
        dbHelper = new SqliteHelper(this, DBNAME, DataBaseVersion,
                tableClassNameList);
        dbHelper.open();
//        Bmob.initialize(this, "ef3986ed931316b869816e34ecbb0d4e");
//        mBmobHttp=BmobHttp.getInstance(this);
        jsoupUtil=JsoupUtil.getInstance();
        gsonHttpComExchange=GsonHttpComExchange.getInstance(this);
        imageManager=ImageManager.getInstance(this);
//		// 日志系统
//		CrashHandler crashHandler = CrashHandler.getInstance();
//		crashHandler.init(this);

    }

    public static MyApp getInstance() {
        return mInstance;
    }




}
