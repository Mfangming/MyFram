package com.fm.testmain;

import java.util.ArrayList;
import java.util.List;

import com.fangming.myframwork.R;
import com.fangming.testffmpeg.TestffmpegActivity3;
import com.fm.entity.ProjectInfo;
import com.fm.test.ActivityA;
import com.fm.test.TestFileCreateActivity;
import com.fm.test.TestImmersionActivity;
import com.fm.test.TestServiceActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


/**
 * listview 展示功能
 * @author fangming
 *
 */
public class MainListActivity extends Activity {
	private MainListActivity _this;
	private ListView mlistview;
	private ProjectAdapter madapter;
	private List<ProjectInfo> mdate=new ArrayList<ProjectInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_this = this;
		setContentView(R.layout.activity_main_list);
		initView();
		initDate();
	}

	/**
	 * @describe:初始化数
	 */
	private void initDate() {
		mdate.add(new ProjectInfo("FFmpeg移植android测试", new Intent(_this,TestffmpegActivity3.class)));
		mdate.add(new ProjectInfo("测试沉浸式title栏", new Intent(_this,TestImmersionActivity.class)));
		mdate.add(new ProjectInfo("测试ActivityA跳转到ActivityB", new Intent(_this,ActivityA.class)));
		mdate.add(new ProjectInfo("测试部分手机文件夹的创建", new Intent(_this,TestFileCreateActivity.class)));
		mdate.add(new ProjectInfo("测试Service", new Intent(_this,TestServiceActivity.class)));
		madapter.notifyDataSetChanged();
	}

	/**
	 * @describe:初始化view()
	 */
	private void initView() {
		mlistview=(ListView)findViewById(R.id.mlistview);
		madapter=new ProjectAdapter(_this, mdate);
		mlistview.setAdapter(madapter);
		mlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ProjectInfo p=(ProjectInfo) parent.getItemAtPosition(position);
				startActivity(p.getMintent());
			}
		});
	}

}
