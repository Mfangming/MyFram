package com.fangming.dao;

import com.fangming.entity.NewsEntity;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by fangming on 2016-4-29.
 */
public interface NewsDao {
    /**将doc转换成新闻列表*/
    public List<NewsEntity> pasenews(Document doc,String sumary);
}
