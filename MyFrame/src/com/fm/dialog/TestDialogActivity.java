package com.fm.dialog;

import com.fangming.myframwork.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestDialogActivity extends Activity {
	private CMCPSystemDialog dialog;
	private Button btn_dialog;
	private TestDialogActivity _this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_dialog_activity);
		_this = this;
		initView();
	}

	private void initView() {
		btn_dialog = (Button) findViewById(R.id.btn_dialog);
		dialog = CMCPSystemDialog.getCMCPSystemDialog(_this,
				CMCPSystemDialog.DIALOG_PROGRESS, true);
		dialog.setContent("是否退出");
		dialog.setTitleString("系统更新");
		dialog.setRightListnerAndValue("取消", new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.setLeftBTListnerAndValue("确认", new OnClickListener() {

			@Override
			public void onClick(View v) {
//				dialog.dismiss();
//				_this.finish();
			}
		});

		btn_dialog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.show();
			}
		});
	}
}
