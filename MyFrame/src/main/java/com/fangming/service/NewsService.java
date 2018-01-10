package main.java.com.fangming.service;

import com.fangming.entity.NewsEntity;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by fangming on 2016-4-29.
 */
public interface NewsService {
    /**将doc转换成新闻列表*/
    public List<NewsEntity> pasenews(Document doc, String sumary);
}
