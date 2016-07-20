package com.fangming.volly;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by fangming on 2016-4-29.
 */
public class BitmapCache implements ImageLoader.ImageCache {
    //LruCache对象
    private LruCache<String, Bitmap> lruCache ;
    //设置最大缓存为10Mb，大于这个值会启动自动回收
    private int max = 10*1024*1024;

    public BitmapCache(){
        //初始化 LruCache
        lruCache = new LruCache<String, Bitmap>(max){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
    @Override
    public Bitmap getBitmap(String url) {
        Log.e("getBitmap",url);
        return lruCache.get(url);
    }
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        Log.e("putBitmap",url);
        lruCache.put(url, bitmap);
    }
}
