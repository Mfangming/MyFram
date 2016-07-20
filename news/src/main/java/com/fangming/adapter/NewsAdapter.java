package com.fangming.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.fangming.entity.NewsEntity;
import com.fangming.news.R;
import com.fangming.volly.ImageManager;

import java.util.List;

/**
 * Created by fangming on 2016-4-28.
 */
public class NewsAdapter extends BaseAdapter {
    private LayoutInflater _inflater;
    private List<NewsEntity> newsEntityList;
    private ImageManager imageManager;
    private Context context;

    public NewsAdapter(Context context, List<NewsEntity> files, ImageManager imageManager) {
        newsEntityList = files;
        _inflater = LayoutInflater.from(context);
        this.context=context;
        this.imageManager=imageManager;
    }

    public int getCount() {
        return newsEntityList.size();
    }

    public Object getItem(int position) {
        return newsEntityList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = _inflater.inflate(R.layout.newsadapter_item, null);
            holder = new ViewHolder();
            holder.image_right = (ImageView) convertView.findViewById(R.id.image_right);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.layout_image = (LinearLayout) convertView.findViewById(R.id.layout_image);
            holder.image1 = (ImageView) convertView.findViewById(R.id.image1);
            holder.image2 = (ImageView) convertView.findViewById(R.id.image2);
            holder.image3 = (ImageView) convertView.findViewById(R.id.image3);
            holder.tv_source = (TextView) convertView.findViewById(R.id.tv_source);
            holder.tv_plnumber = (TextView) convertView.findViewById(R.id.tv_plnumber);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_pro = (TextView) convertView.findViewById(R.id.tv_pro);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsEntity newsEntity = newsEntityList.get(position);
        holder.tv_title.setText(newsEntity.getTitle());
        if(null==newsEntity.getProfil() || "".equals(newsEntity.getProfil())){
            holder.tv_pro.setVisibility(View.GONE);
        }else{
            holder.tv_pro.setText(newsEntity.getProfil());
        }
        if(null==newsEntity.getIsthreepng() || newsEntity.getIsthreepng()==false){
            holder.layout_image.setVisibility(View.GONE);
        }else{

        }
        if(null==newsEntity.getNewssource() || "".equals(newsEntity.getNewssource())){
            holder.tv_source.setVisibility(View.GONE);
        }else{
            holder.tv_source.setText(newsEntity.getNewssource());
        }
        if(null==newsEntity.getUpdatetime() || "".equals(newsEntity.getUpdatetime())){
            holder.tv_time.setVisibility(View.GONE);
        }else{
            holder.tv_time.setText(newsEntity.getUpdatetime());
        }
        if(null==newsEntity.getCommentnumbers() || "".equals(newsEntity.getCommentnumbers())){
            holder.tv_plnumber.setVisibility(View.GONE);
        }else{
            holder.tv_plnumber.setText("评论数量："+ Html.fromHtml(newsEntity.getCommentnumbers()));
        }
        if(newsEntity.getIsbooktip()!=null && !"".equals(newsEntity.getIsbooktip())){
            holder.tv_plnumber.setText(Html.fromHtml(newsEntity.getIsbooktip()));
            holder.tv_plnumber.setVisibility(View.VISIBLE);
        }
        if(null==newsEntity.getRightpictureurl() || "".equals(newsEntity.getRightpictureurl())){
            holder.image_right.setVisibility(View.GONE);
        }else{
            holder.image_right.setVisibility(View.VISIBLE);
            imageManager.loadImageByVolley(newsEntity.getRightpictureurl(),holder.image_right);
        }
        return convertView;
    }

    /* class ViewHolder */
    class ViewHolder {
        ImageView image_right;//右图
        TextView tv_title;//标题
        LinearLayout layout_image;//3个图片的布局
        ImageView image1;//第一图片
        ImageView image2;//第二图片
        ImageView image3;//第三图片
        TextView tv_source;//来源
        TextView tv_plnumber;//评论数
        TextView tv_time;//时间
        TextView tv_pro;//概要
    }
}
