//package com.fm.test;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.fangming.myframwork.R;
//
///**
// * Created by fangming on 2016-9-1.
// */
//public class TestGlideActivity extends Activity{
//
//    private Button btn_test;
//    private ImageView iv_test;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_glide);
//        initView();
//        loadImage();
//    }
//
//    private void initView() {
//        btn_test=(Button)findViewById(R.id.btn_test);
//        iv_test=(ImageView)findViewById(R.id.iv_test);
//    }
//
//    private  void loadImage(){
//        Glide.with(this).load("http://www.baidu.com/img/bdlogo.png").into(iv_test);
//    }
//}
