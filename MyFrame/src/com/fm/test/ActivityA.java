package com.fm.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fangming.myframwork.R;

import java.lang.reflect.InvocationHandler;

/**
 * Created by fangming on 2016-12-19.
 */

public class ActivityA extends Activity {
    private ActivityA _this;
    private String TAG=ActivityA.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");
        setContentView(R.layout.activity_a);
        _this=this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    public void startActivityB(View v){
        Intent intent=new Intent(_this,ActivityB.class);
        startActivity(intent);
    }
}
