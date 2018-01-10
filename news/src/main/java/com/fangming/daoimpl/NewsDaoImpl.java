package com.fangming.daoimpl;

import com.fangming.dao.NewsDao;
import com.fangming.entity.NewsEntity;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangming on 2016-4-29.
 */
public class NewsDaoImpl implements NewsDao {
    @Override
    public List<NewsEntity> pasenews(Document doc,String sumary) {
        List<NewsEntity> newsList=new ArrayList<NewsEntity>();
        Elements links1 = doc.select("section[data-channel="+sumary+"]");
        for (Element link : links1) {
            Elements link2 = link.select("a[href]");
            for (Element link3 : link2) {
                NewsEntity news=new NewsEntity();
                String url=link3.attr("abs:href");
                if(null==url || "".equals(url)){
                    continue;
                }
                news.setNewurl(url);
                String title=link3.select("a[href]>dl>dd>h3").text();
                if(null==title || "".equals(title)){
                    continue;
                }
                news.setTitle(title);
                String pro=link3.select("a[href]>dl>dd>p").text();
                news.setProfil(pro);
                String commentnumber=link3.select("a[href]>dl>dd>div[class]>span[class=comment]").html();
                news.setCommentnumbers(commentnumber);
                String book_tip=link3.select("a[href]>dl>dd>div[class]>span[class=book_tip]").html();
                news.setIsbooktip(book_tip);
                String rightpictureurl=link3.select("a[href]>dl>dt>img[src~=.jpg]").attr("data-src");
                news.setRightpictureurl(rightpictureurl);

                newsList.add(news);
//                print("\nImports: (%d)","" + link3.attr("abs:href") + "-----" + link3.select("a[href]>dl>dd>h3").text() + "-----"
//                        + link3.select("a[href]>dl>dd>p").text() + "\r\n");
            }
        }
        return newsList;
    }
}
