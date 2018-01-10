package main.java.com.fangming.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangming.news.R;

/**
 * 自定义title
 *
 * @author fangming
 *
 */
public class HeaderLayout extends LinearLayout {
	private LayoutInflater mInflater;
	private View mHeader;
	private TextView mTitle;

	private Button mRightButton;
	private onRightButtonClickListener mRightButtonClickListener;

	private Button mLeftButton;
	private onLeftButtonClickListener mLeftButtonClickListener;

	public HeaderLayout(Context context) {
		super(context);
	}

	public HeaderLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void init(Context context) {
		mInflater = LayoutInflater.from(context);
		mHeader = mInflater.inflate(R.layout.titile_bar, null);
		addView(mHeader);
		initViews();
	}

	/**
	 * @describe:初始化view
	 */
	private void initViews() {
		mLeftButton = (Button) findViewById(R.id.btn_left_back);
		mTitle = (TextView) findViewById(R.id.tv_title);
		mRightButton = (Button) findViewById(R.id.btn_right_cmd);
	}

	/**
	 * @describe:设置右边按钮
	 * @param resId
	 * @param text
	 * @param Listener
	 */
	public void setRightButton(int resId, CharSequence text, onRightButtonClickListener Listener) {
		if (mRightButton != null) {
			mRightButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0);
			mRightButton.setText(text);
			mRightButton.setVisibility(View.VISIBLE);
			setOnRightButtonClickListener(Listener);
			mRightButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (mRightButtonClickListener != null) {
						mRightButtonClickListener.onClick(v);
					}
				}
			});
		}
	}

	/**
	 * @describe:设置左边按钮
	 * @param title
	 * @param resId
	 * @param text
	 * @param listener
	 */
	@SuppressLint("NewApi")
	public void setLeftButton(CharSequence title, int resId, CharSequence text, onLeftButtonClickListener listener) {
		if (mLeftButton != null) {
			mLeftButton.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
			mLeftButton.setText(text);
			mLeftButton.setVisibility(View.VISIBLE);
			setOnLeftButtonClickListener(listener);
			mLeftButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (mLeftButtonClickListener != null) {
						mLeftButtonClickListener.onClick();
					}
				}
			});
		}

	}

	/**
	 * @describe:设置标题
	 * @param title标题
	 */
	public void setTitle(String title) {
		if (title != null) {
			mTitle.setText(title);
			mTitle.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * @describe:设置右边按钮监听
	 * @param listener
	 */
	public void setOnRightButtonClickListener(onRightButtonClickListener listener) {
		mRightButtonClickListener = listener;
	}

	public interface onRightButtonClickListener {
		void onClick(View v);
	}

	/**
	 * @describe:设置左边按钮监听
	 * @param listener
	 */
	public void setOnLeftButtonClickListener(onLeftButtonClickListener listener) {
		mLeftButtonClickListener = listener;
	}

	public interface onLeftButtonClickListener {
		void onClick();
	}

	/**
	 * @describe:设置标题和右边按钮事件
	 * @param title
	 * @param resId
	 * @param text
	 * @param Listener
	 */
	public void setTitleAndRightButton(CharSequence title, int resId, CharSequence text,
									   onRightButtonClickListener Listener) {
		setTitle(title.toString());
		setRightButton(resId, text, Listener);
	}

	/**
	 * @describe:设置标题和左边按钮事件
	 * @param resId
	 * @param text
	 * @param listener
	 */
	public void setTitleAndLeftButton(CharSequence title, int resId, CharSequence text,
									  onLeftButtonClickListener listener) {
		setTitle(title.toString());
		setLeftButton(title, resId, text, listener);
	}

}
