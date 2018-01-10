package com.fm.test;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import com.fangming.myframwork.R;


/**
 * Created by fangming on 2016-12-7.
 */

public class SecondActivity extends Activity {

    private LinearLayout layoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimmersion);
        layoutView= (LinearLayout) findViewById(R.id.ll_root);
        layoutView.post(new Runnable() {
            @Override
            public void run() {
                // 圆形动画的x坐标  位于View的中心
                int cx = (layoutView.getLeft() + layoutView.getRight()) / 2;

                //圆形动画的y坐标  位于View的中心
                int cy = (layoutView.getTop() + layoutView.getBottom()) / 2;

                //起始大小半径
                float startX=0f;

                //结束大小半径 大小为图片对角线的一半
                float startY= (float) Math.sqrt(cx*cx+cy*cy);
                Animator animator= ViewAnimationUtils.createCircularReveal(layoutView,cx,cy,startX,startY);

                //在动画开始的地方速率改变比较慢,然后开始加速
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(600);
                animator.start();
            }
        });
    }
}
