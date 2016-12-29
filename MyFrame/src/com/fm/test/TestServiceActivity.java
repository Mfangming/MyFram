package com.fm.test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.view.View;

import com.fangming.myframwork.R;

/**
 * Created by fangming on 2016-12-22.
 */


public class TestServiceActivity extends Activity {
    private TestServiceActivity _this;

    @RequiresPermission(Manifest.permission.INTERNET)
    public static final String TESTSERVICE2 = "com.test.push";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _this=this;
        setContentView(R.layout.activity_testservice);
    }

    public void startMyService(View view){
        Intent intent=new Intent();
        intent.setAction("com.test.action");
        startService(intent);
    }

    public void stopMyService(View view){
        Intent intent=new Intent(_this,TestService.class);
        stopService(intent);
    }

    public void startMyService2(View view){
        Intent intent=new Intent();
        intent.setAction(TESTSERVICE2);
        startService(intent);
    }

    public void stopMyService2(View view){
        Intent intent=new Intent();
        intent.setAction(TESTSERVICE2);
        stopService(intent);
    }

}
