package com.fm.fileupload;

import com.fangming.myframwork.R;
import com.fm.file.FileUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class TestFileUpload extends Activity {
	private TestFileUpload _this;
	private GridView img_add;
	private PopupWindow upWindow;
	private View addview;
	private Button btn_cancle;
	private Button btn_takephoto;
	private Button btn_selectff;
	private RelativeLayout layout_rl;
	private View parentView;
	private myAdapter adapter;
	private static final int TAKE_PICTURE = 0x000002;
	public static Bitmap bimap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		parentView = getLayoutInflater().inflate(R.layout.activity_fileupload,
				null);
		setContentView(parentView);
		_this = this;
		findView();

	}

	private void initPopupWindow() {
		LayoutInflater inflater = LayoutInflater.from(_this);
		addview = inflater.inflate(R.layout.pop_fileupload_select, null);
		layout_rl = (RelativeLayout) addview.findViewById(R.id.layout_rl);
		if (upWindow == null) {
			upWindow = new PopupWindow(this);
			upWindow.setWidth(LayoutParams.MATCH_PARENT);
			upWindow.setHeight(LayoutParams.WRAP_CONTENT);
			upWindow.setContentView(addview);
		}
		upWindow.setTouchable(true);
		upWindow.setFocusable(true);
		// 需要设置一下此参数，点击外边可消失
		upWindow.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击窗口外边窗口消失
		upWindow.setOutsideTouchable(true);
		// popupwindow事件监听
		btn_cancle = (Button) addview.findViewById(R.id.btn_cancle);// 取消
		btn_takephoto = (Button) addview.findViewById(R.id.btn_takephoto);// 取消
		btn_selectff = (Button) addview.findViewById(R.id.btn_selectff);// 取消
		btn_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				upWindow.dismiss();
				layout_rl.clearAnimation();
			}
		});
		btn_takephoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				upWindow.dismiss();
				layout_rl.clearAnimation();
				photo();
			}
		});
		btn_selectff.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				upWindow.dismiss();
				layout_rl.clearAnimation();
				/***
				 * 这个是调用android内置的intent，来过滤图片文件 ，同时也可以过滤其他的
				 */
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent, 1);
			}
		});
	}

	private void findView() {
		img_add = (GridView) findViewById(R.id.noScrollgridview);
		img_add.setSelector(new ColorDrawable(Color.TRANSPARENT));
		initPopupWindow();
		adapter = new myAdapter(_this);
		img_add.setAdapter(adapter);
		img_add.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				if (Bimp.tempSelectBitmap.size() == 0 && position == 0) {
					layout_rl.startAnimation(AnimationUtils.loadAnimation(
							_this, R.anim.activity_translate_in));
					upWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
				} else {
					Intent intent = new Intent(_this, GalleryActivity.class);
					startActivityForResult(intent, 1);
				}
			}
		});
		// img_add.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// upWindow.showAtLocation(_this.findViewById(R.id.main), Gravity.BOTTOM
		// | Gravity.CENTER_HORIZONTAL, 0, 0);
		// }
		// });
	}

	class myAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public myAdapter(Context context) {
			this.inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			if (Bimp.tempSelectBitmap.size() == 9) {
				return 9;
			}
			return (Bimp.tempSelectBitmap.size() + 1);
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.item_gradview_tua, null);
				holder = new ViewHolder();
				holder.item_grida_image = (ImageView) convertView
						.findViewById(R.id.item_grida_image);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (position == Bimp.tempSelectBitmap.size()) {
				holder.item_grida_image.setImageBitmap(BitmapFactory
						.decodeResource(getResources(),
								R.drawable.icon_addpic_unfocused));
				if (position == 9) {
					holder.item_grida_image.setVisibility(View.GONE);
				}
			} else {
				holder.item_grida_image.setImageBitmap(Bimp.tempSelectBitmap
						.get(position).getBitmap());
			}
			return convertView;
		}

		/* class ViewHolder */
		private class ViewHolder {
			ImageView item_grida_image;
		}

	}

	/**
	 * 调用系统拍照
	 */
	public void photo() {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case 1:

				break;
			case TAKE_PICTURE:
				if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {
					String fileName = String.valueOf(System.currentTimeMillis());
					Bitmap bm = (Bitmap) data.getExtras().get("data");
					FileUtil.saveBitmap(bm, fileName);
					ImageItem takePhoto = new ImageItem();
					takePhoto.setBitmap(bm);
					Bimp.tempSelectBitmap.add(takePhoto);
					adapter.notifyDataSetChanged();
				}
				// }
				break;

			default:
				break;
		}
	}

}
