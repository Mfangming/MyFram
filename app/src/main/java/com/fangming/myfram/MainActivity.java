package com.fangming.myfram;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str="{\"id\":1,\"pwd\":321}";
        Gson gson=new Gson();
        Person p= gson.fromJson(str,Person.class);
        TextView btn_clic = (TextView) findViewById(R.id.btn_clic);
        btn_clic.setText(p.toString());
    }
}
