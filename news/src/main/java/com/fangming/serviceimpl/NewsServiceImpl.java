package com.fangming.serviceimpl;

import com.fangming.dao.NewsDao;
import com.fangming.entity.NewsEntity;
import com.fangming.service.NewsService;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by fangming on 2016-4-29.
 */
public class NewsServiceImpl implements NewsService{
    private NewsDao newsDao;

    public NewsServiceImpl(NewsDao newsDao) {
        super();
        this.newsDao = newsDao;
    }

    @Override
    public List<NewsEntity> pasenews(Document doc, String sumary) {
        return newsDao.pasenews(doc,sumary);
    }
}
