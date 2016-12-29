package com.fm.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.fangming.myframwork.R;
import com.fm.file.FileUtil;

import java.io.IOException;

/**
 * Created by fangming on 2016-12-22.
 */

public class TestFileCreateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testfilecreate);
    }

    public void createFile(View view){
        try {
            FileUtil.createFiles(FileUtil.SDPATH+"fm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
