package com.fm.test;

import java.util.HashMap;
import java.util.Map;

import com.fangming.myframwork.R;
import com.fm.httputill.HttpUtil.ResponseListener;
import com.fm.httputill.Respones;
import com.fm.qxtapp.MyApp;
import com.fm.utill.Md5Utill;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TestHttpActivity extends Activity {
    private MyApp mApp;
    private Button btn_one;
    private Button btn_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_http);
        mApp = MyApp.getInstance();
        initView();

    }

    private void initView() {
        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_one.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", "15271894533");
                params.put("password", Md5Utill.getMD5Str("12345678"));
                mApp.mHttpUtill.threadPost("appLogin", params, new ResponseListener() {

                    @Override
                    public void onResponse(Message msg) {
                        switch (msg.what) {
                            case 0:
                                Respones<String> res = mApp.gsonHttpComExchange.sendRequstObject(msg.obj.toString(), String.class);
                                if (res.getStatus()) {
                                    Toast.makeText(getBaseContext(), res.getMsg(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getBaseContext(), res.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }
                    }
                });

            }
        });

    }

}
