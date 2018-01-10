package main.java.com.fm.dialog;

import com.fangming.myframwork.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

public class Mydialog {
	private Context context;

	public Mydialog(Context context) {
		super();
		this.context = context;
	}

	/**
	 * 是否退出dilog
	 */
	private void dialog1() {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示"); // 设置标题
		builder.setMessage("是否确认退出?"); // 设置内容
		builder.setIcon(R.drawable.ic_launcher);// 设置图标，图片id即可
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { // 设置确定按钮
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss(); // 关闭dialog
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // 设置取消按钮
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {// 设置忽略按钮
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(context, "忽略" + which,
						Toast.LENGTH_SHORT).show();
			}
		});
		// 参数都设置完成了，创建并显示出来
		builder.create().show();
	}

	private void dialog2() {
		// 先new出一个监听器，设置好监听
		DialogInterface.OnClickListener dialogOnclicListener = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
					case Dialog.BUTTON_POSITIVE:
						Toast.makeText(context, "确认" + which, Toast.LENGTH_SHORT)
								.show();
						break;
					case Dialog.BUTTON_NEGATIVE:
						Toast.makeText(context, "取消" + which, Toast.LENGTH_SHORT)
								.show();
						break;
					case Dialog.BUTTON_NEUTRAL:
						Toast.makeText(context, "忽略" + which, Toast.LENGTH_SHORT)
								.show();
						break;
				}
			}
		};
		// dialog参数设置
		AlertDialog.Builder builder = new AlertDialog.Builder(context); // 先得到构造器
		builder.setTitle("提示"); // 设置标题
		builder.setMessage("是否确认退出?"); // 设置内容
		builder.setIcon(R.drawable.ic_launcher);// 设置图标，图片id即可
		builder.setPositiveButton("确认", dialogOnclicListener);
		builder.setNegativeButton("取消", dialogOnclicListener);
		builder.setNeutralButton("忽略", dialogOnclicListener);
		builder.create().show();
	}

	/**
	 * 列表式dialog
	 */
	private void dialog3() {
		final String items[] = { "张三", "李四", "王五" };
		// dialog参数设置
		AlertDialog.Builder builder = new AlertDialog.Builder(context); // 先得到构造器
		builder.setTitle("提示"); // 设置标题
		// builder.setMessage("是否确认退出?"); //设置内容
		builder.setIcon(R.drawable.ic_launcher);// 设置图标，图片id即可
		// 设置列表显示，注意设置了列表显示就不要设置builder.setMessage()了，否则列表不起作用。
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(context, items[which], Toast.LENGTH_SHORT)
						.show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().show();
	}

	/**
	 * 单选dilog
	 */
	private void dialog4() {
		final String items[] = { "男", "女" };
		AlertDialog.Builder builder = new AlertDialog.Builder(context); // 先得到构造器
		builder.setTitle("提示"); // 设置标题
		builder.setIcon(R.drawable.ic_launcher);// 设置图标，图片id即可
		builder.setSingleChoiceItems(items, 0,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// dialog.dismiss();
						Toast.makeText(context, items[which],
								Toast.LENGTH_SHORT).show();
					}
				});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().show();
	}

	/**
	 * 多选dilog
	 */
	private void dialog5() {
		final String items[] = { "篮球", "足球", "排球" };
		final boolean selected[] = { true, false, true };
		AlertDialog.Builder builder = new AlertDialog.Builder(context); // 先得到构造器
		builder.setTitle("提示"); // 设置标题
		builder.setIcon(R.drawable.ic_launcher);// 设置图标，图片id即可
		builder.setMultiChoiceItems(items, selected,
				new DialogInterface.OnMultiChoiceClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which,
										boolean isChecked) {
						// dialog.dismiss();
						Toast.makeText(context, items[which] + isChecked,
								Toast.LENGTH_SHORT).show();
					}
				});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();
				// android会自动根据你选择的改变selected数组的值。
				for (int i = 0; i < selected.length; i++) {
					Log.e("hongliang", "" + selected[i]);
				}
			}
		});
		builder.create().show();
	}

	/**
	 * 进度条Dialog
	 */
	private void progressDialog() {
		final ProgressDialog mProgress = new ProgressDialog(context);
		mProgress.setIcon(R.drawable.ic_launcher);
		mProgress.setTitle("带进度条的Ｄialog");
		mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgress.setButton(DialogInterface.BUTTON_NEGATIVE,"确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();
			}
		});
		mProgress.setButton2("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
			}
		});

		mProgress.show();
		new Thread(new Runnable() {
			int progress = 0;

			@Override
			public void run() {
				while (progress <= 100) {
					mProgress.setProgress(progress);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					progress++;
				}
			}
		}).start();
	}
}
