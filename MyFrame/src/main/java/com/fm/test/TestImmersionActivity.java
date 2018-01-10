package main.java.com.fm.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.fangming.myframwork.R;

/**
 * Created by fangming on 2016-12-7.
 */

public class TestImmersionActivity extends Activity {
    private TestImmersionActivity _this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_testimmersion);
        _this = this;
    }

    public void gotoNextActivity(View v) {
        Intent intent = new Intent();
        intent.setClass(_this, SecondActivity.class);
        startActivity(intent);
    }
}
