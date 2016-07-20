//package com.fangming.bmob;
//
//import android.content.Context;
//import android.util.Log;
//
//import org.json.JSONObject;
//
//import cn.bmob.v3.AsyncCustomEndpoints;
//import cn.bmob.v3.listener.CloudCodeListener;
//
///**
// * Created by fangming on 2016-4-26.
// */
//public class BmobHttp {
//
//    private String Tag="BmobHttp";
//
//    private Context context;
//    private static BmobHttp bmobHttp;
//
//    public static BmobHttp getInstance(Context context) {
//        if (bmobHttp == null) {
//            bmobHttp = new BmobHttp(context);
//        }
//        return bmobHttp;
//    }
//
//    public BmobHttp(Context context) {
//        super();
//        this.context = context;
//    }
//
//
//    public void mAsyTask(String cloudCodeName, JSONObject params){
//        AsyncCustomEndpoints ace = new AsyncCustomEndpoints();
////第一个参数是上下文对象，第二个参数是云端逻辑的方法名称，第三个参数是上传到云端逻辑的参数列表（JSONObject cloudCodeParams），第四个参数是回调类
//        ace.callEndpoint(context, cloudCodeName, params, new CloudCodeListener() {
//            @Override
//            public void onSuccess(Object object) {
//                Log.e(Tag,"云端usertest方法返回:" + object.toString());
//            }
//            @Override
//            public void onFailure(int code, String msg) {
//                Log.e(Tag,"访问云端usertest方法失败:" + msg);
//            }
//        });
//    }
//}
