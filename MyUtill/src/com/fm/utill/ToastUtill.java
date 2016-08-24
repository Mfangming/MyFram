package com.fm.utill;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast公用类
 * Created by fangming on 2016-8-24.
 */
public class ToastUtill {
    private static Toast toast;

    public static void showToast(Context context,String msg){
        if(toast==null){
            toast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else{
            toast.setText(msg);
        }
        toast.show();
    }

}
