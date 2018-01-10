package main.java.com.fangming.fragments;

import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        onVisibleLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
        invisibleLoad();
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void onVisibleLoad();

    /**
     * 不可见时子类的操作
     */
    protected abstract void invisibleLoad();

}
