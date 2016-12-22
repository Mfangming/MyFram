package com.fm.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fangming.myframwork.R;
import com.fm.file.FileActivityHelper;
import com.fm.file.FileAdapter;
import com.fm.file.FileInfo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FileBrowserActivity extends ListActivity {
	private String _rootPath = "/sdcard";
	private String _currentPath = _rootPath;
	private FileBrowserActivity _this;
	private String callbackId;
	private Button btn_home;
	private TextView file_path;
	private List<FileInfo> _files;
	public static int RESULTCODE = 12;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIntent() != null) {
			callbackId = getIntent().getStringExtra("callbackId");
		}
		_this = this;
		_this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题�?
		setContentView(R.layout.file_browser_activity);
		initView();
		ViewFiles(_currentPath);
	}

	private void initView() {
		btn_home = (Button) findViewById(R.id.btn_home);
		file_path = (TextView) findViewById(R.id.file_path);
		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				File f = new File(_currentPath);
				String parentPath = f.getParent();
				if (parentPath != null) {
					ViewFiles(parentPath);
				} else {
					finish();
				}
			}
		});
	}

	private void ViewFiles(String filePath) {
		ArrayList<FileInfo> tmp = FileActivityHelper.getFiles(_this, filePath);
		if (tmp != null) {
			if (_files != null) {
				_files.clear();
			}
			_files = tmp;
			_currentPath = filePath;
			file_path.setText(_currentPath);
			setListAdapter(new FileAdapter(this, _files));
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		FileInfo f = _files.get(position);
		if (f.IsDirectory) {
			ViewFiles(f.Path);
		} else {
			// 关闭该页面，传出入文件封装后的T_attachment
			if (!f.Name.contains(".")) {
				Toast.makeText(_this, "该文件无后缀，无法确认文件类型，请重新�?�择�?",
						Toast.LENGTH_SHORT).show();
				return;
			}else{
				String houzhui = f.Name.substring(f.Name.lastIndexOf("."),
						f.Name.length());
				if (".log".equals(houzhui) || ".apk".equals(houzhui)
						|| ".db".equals(houzhui) || ".xml".equals(houzhui)
						|| ".java".equals(houzhui) || ".html".equals(houzhui))
				{

					Toast.makeText(_this, "系统不支�?" + houzhui + "类型的文件上传，请重新�?�择�?",
							Toast.LENGTH_SHORT).show();
					return;

				}
			}
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putLong("Size", f.Size);
			bundle.putString("Name", f.Name);
			bundle.putString("Path", f.Path);
			bundle.putString("callbackId", callbackId);
			intent.putExtras(bundle);
			// 设置返回数据
			_this.setResult(RESULTCODE, intent);
			// 关闭Activity
			_this.finish();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			File f = new File(_currentPath);
			String parentPath = f.getParent();
			if (parentPath != null)
			{
				ViewFiles(parentPath);
			}
			else
			{
				this.finish();

			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
