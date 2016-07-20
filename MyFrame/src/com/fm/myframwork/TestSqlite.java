package com.fm.myframwork;

import java.util.ArrayList;
import java.util.List;

import com.fangming.myframwork.R;
import com.fm.entity.User;
import com.fm.qxtapp.MyApp;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestSqlite extends Activity {
	private MyApp qxtApp;
	private Button btn_search;
	private EditText et_name;
	private EditText et_paw;
	private Button btn_delete;
	private Button btn_update;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		qxtApp = (MyApp) getApplication();
		Button btn = (Button) findViewById(R.id.btn);
		btn_search = (Button) findViewById(R.id.btn_search);
		et_name = (EditText) findViewById(R.id.et_name);
		et_paw = (EditText) findViewById(R.id.et_paw);
		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_update=(Button) findViewById(R.id.btn_update);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ContentValues obj = new ContentValues();
				obj.put("name", et_name.getText().toString());
				obj.put("password", et_paw.getText().toString());
				qxtApp.dbHelper.insert("User", obj);
			}
		});

		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				List<User> userList = new ArrayList<User>();
				String str = "";
				userList = qxtApp.dbHelper.queryForListBySql(
						"select * from User where name='mingfang'", null,
						User.class);
				if(userList==null || userList.size()==0){
					Toast.makeText(getApplication(), "查询的数据为null", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				for (User u : userList) {
					str = str + "\n name:" + u.getName() + ",password:"
							+ u.getPassword();
				}
				Toast.makeText(getApplication(), str, Toast.LENGTH_SHORT)
						.show();
			}
		});

		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int i = 0;
				i = qxtApp.dbHelper.delete("User", "name=?",
						new String[] { "mingfang" });
				if (i > 0) {
					Toast.makeText(getApplication(), "删除" + i + "记录",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		btn_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int i=0;
				User uer=new User("mingfang","");
				i=qxtApp.dbHelper.update("User", uer, "name=?", new String[]{""});
				if (i > 0) {
					Toast.makeText(getApplication(), "更新" + i + "记录",
							Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplication(), "更新失败",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
