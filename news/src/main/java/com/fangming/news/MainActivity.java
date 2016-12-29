package com.fangming.news;

import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.fangming.adapter.NewsFragmentPagerAdapter;
import com.fangming.fragments.NewsFragment;
import com.fangming.myapp.MyApp;
import com.fangming.view.HeaderLayout;


import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private MyApp myApp;
    private MainActivity _this;
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private NewsFragmentPagerAdapter mAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _this = this;
        myApp = MyApp.getInstance();
        initView();
        initTitleBar("开心时刻");
        Looper.prepare();
        Looper.myLooper();
        Looper.loop();
    }

    private void toast(String msg) {
        Toast.makeText(_this, msg, Toast.LENGTH_SHORT).show();
    }

    public void initTitleBar(String title) {
        HeaderLayout mheaderLayout = (HeaderLayout) findViewById(R.id.layout_title);
        mheaderLayout.init(_this);
        mheaderLayout.setTitleAndLeftButton(title, R.mipmap.ic_menu_user_off, "", new HeaderLayout.onLeftButtonClickListener() {

            @Override
            public void onClick() {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        fragments.clear();
        fragments.add(new NewsFragment());
        fragments.add(new NewsFragment());
        mAdapter = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(pageListener);
//        btn_insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Person p2 = new Person();
//                p2.setName("lucky");
//                p2.save(getBaseContext(), new SaveListener() {
//                    @Override
//                    public void onSuccess() {
//                        Toast.makeText(getApplicationContext(), "添加数据成功，返回objectId为：" + p2.getObjectId(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(int code, String msg) {
//                        Toast.makeText(getApplicationContext(), "创建数据失败：" + msg, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//        btn_select = (Button) findViewById(R.id.btn_select);
//        btn_select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                JSONObject mJsonObject = new JSONObject();
//                try {
//                    mJsonObject.put("name", "lucky");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                myApp.mBmobHttp.mAsyTask("GetNews", mJsonObject);
//            }
//        });
    }

    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    private void selectTab(int tab_postion) {
//        columnSelectIndex = tab_postion;
//        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
//            View checkView = mRadioGroup_content.getChildAt(tab_postion);
//            int k = checkView.getMeasuredWidth();
//            int l = checkView.getLeft();
//            int i2 = l + k / 2 - mScreenWidth / 2;
//            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
//        }
//        //判断是否选中
//        for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
//            View checkView = mRadioGroup_content.getChildAt(j);
//            boolean ischeck;
//            if (j == tab_postion) {
//                ischeck = true;
//            } else {
//                ischeck = false;
//            }
//            checkView.setSelected(ischeck);
//        }
    }


}
