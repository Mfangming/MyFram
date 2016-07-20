package com.fm.dialog;

import com.fangming.myframwork.R;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CMCPSystemDialog extends AlertDialog {
	private Context context;
	public int dialogStyle = DIALOG_STRING;// 标记对话框类别
	public static final int DIALOG_STRING = 0;// 字符串提示
	public static final int DIALOG_PROGRESS = 1;// 进度条
	public static final int DIALOG_OTHER = 2;// 需要添加其他的view
	private Button left_btn;// 确定按钮
	private Button right_btn;// 取消按钮
	private android.view.View.OnClickListener leftClick;
	private android.view.View.OnClickListener rightClick;

	private String left_BTString;// 左按钮的文字
	private String right_BTString;// 右按钮的图片
	private TextView tv_content;// 中间内容
	private String content;// 只有文字信息的内容 或者 进度条里面的内容
	private TextView titile_tv;// 标题内容
	private String titleString;// 标题文字

	private static CMCPSystemDialog cmcpSystemDialog;

	public CMCPSystemDialog(Context context, int dialogStyle) {
		super(context, dialogStyle);
		this.context = context;
		this.dialogStyle = dialogStyle;
	}

	/**
	 * dialog 可后退
	 */
	public static CMCPSystemDialog getCMCPSystemDialog(Context context,
													   int dialogStyle) {
		if (cmcpSystemDialog != null) {
			cmcpSystemDialog.cancel();
			cmcpSystemDialog.dismiss();
		}
		cmcpSystemDialog = new CMCPSystemDialog(context, dialogStyle);
		return cmcpSystemDialog;
	}

	/**
	 * 添加一个 设置是否可后退的 实例获取方法，
	 */
	public static CMCPSystemDialog getCMCPSystemDialog(Context context,
													   int dialogStyle, Boolean cancelable) {
		if (cmcpSystemDialog != null) {

			cmcpSystemDialog.dismiss();
		}
		cmcpSystemDialog = new CMCPSystemDialog(context, dialogStyle);
		cmcpSystemDialog.setCancelable(cancelable);
		cmcpSystemDialog.setCanceledOnTouchOutside(cancelable);
		return cmcpSystemDialog;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_one);
		initView();
		freshUi();
	}

	private void initView() {
		// 标题栏图标和内容
		// title_ioc=(ImageView)findViewById(R.id.title_ioc);
		titile_tv = (TextView) findViewById(R.id.titile_tv);
		tv_content = (TextView) findViewById(R.id.tv_content);

		left_btn = (Button) findViewById(R.id.left_btn);// 确定按钮
		right_btn = (Button) findViewById(R.id.right_btn);// 取消按钮
	}

	private void freshUi() {
		if (null != content && !content.trim().equals("")) {
			tv_content.setText(content);
			tv_content.setVisibility(View.VISIBLE);
		} else {
			tv_content.setVisibility(View.GONE);
		}
		if (null != titleString && !titleString.trim().equals("")) {
			titile_tv.setText(titleString);
			titile_tv.setVisibility(View.VISIBLE);
		} else {
			titile_tv.setVisibility(View.GONE);
		}

		left_btn.setOnClickListener(leftClick);
		right_btn.setOnClickListener(rightClick);

		// 标题图标显示与否
		// if(title_icon!=0){
		// img_title_ioc.setImageResource(title_icon);
		// }else{
		// img_title_ioc.setVisibility(View.GONE);
		// }
		// //标题内容显示与否
		// if(null!=title_string && !title_string.trim().equals("")){
		// tv_title_context.setText(title_string);
		// tv_title_context.setVisibility(View.VISIBLE);
		// }else{
		// tv_title_context.setVisibility(View.GONE);
		// }

		// if (left_BTString != null && !left_BTString.trim().equals(""))
		// {
		// left_BT.setText(left_BTString);
		// }
		//
		// if (right_BTString != null && !right_BTString.trim().equals(""))
		// {
		// right_bt.setText(left_BTString);
		// }
		//
	}

	@Override
	public void show() {
		super.show();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public void setLeftBTListnerAndValue(String left_BTString,
										 android.view.View.OnClickListener leftClick) {
		this.left_BTString = left_BTString;
		this.leftClick = leftClick;
	}

	public void setRightListnerAndValue(String right_BTString,
										android.view.View.OnClickListener rightClick) {
		this.right_BTString = right_BTString;
		this.rightClick = rightClick;
	}

}
