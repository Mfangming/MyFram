package com.fangming.volly;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.fangming.news.R;

/**
 * Created by fangming on 2016-4-29.
 */
public class ImageManager {
    private static ImageManager instance;
    private static RequestQueue mQueue;

    /* 1:懒汉式，静态工程方法，创建实例 */
    public static ImageManager getInstance(Context context) {
        if (instance == null) {
            instance = new ImageManager();
        }
        if (mQueue == null){
            mQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return instance;
    }

    public void loadImageByVolley(String imageUrl,ImageView mImageView){
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(mImageView, R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        imageLoader.get(imageUrl, listener);

    }

    public void connetion(String url, NetworkImageView imageView) {
        doRequest( url, imageView);
    }

    public static void doRequest(String url, final NetworkImageView imageView) {
        //1.实例化ImageLoader
        ImageLoader loader = new ImageLoader(mQueue, new BitmapCache());
        //2.设置监听器
//        ImageLoader.ImageListener listener =
//                ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
//        loader.get(url, listener,100,80);

        imageView.setTag("url");
        imageView.setImageUrl(url,loader);
    }

}
