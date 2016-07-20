package com.fangming.jsoup;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.fangming.daoimpl.NewsDaoImpl;
import com.fangming.entity.NewsEntity;
import com.fangming.entity.Respones;
import com.fangming.file.FileUtil;
import com.fangming.service.NewsService;
import com.fangming.serviceimpl.NewsServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JsoupUtil {

    private String Tag = "JsoupUtil";
    public ResponseListener mListener;
    private String address;

    private static JsoupUtil instance;

    /* 1:懒汉式，静态工程方法，创建实例 */
    public static JsoupUtil getInstance() {
        if (instance == null) {
            instance = new JsoupUtil();
        }
        return instance;
    }

    /**
     * @param urlString
     * @return html����
     */
    public void getHtmlString(String urlString) {
        try {
            Document doc = Jsoup.connect(urlString).timeout(5000).post();
            Document content = Jsoup.parse(doc.toString());
            Elements divs = content.select("#siteNav");
            Document divcontions = Jsoup.parse(divs.toString());
            Elements element = divcontions.getElementsByTag("li");
            System.out.println(element.toString());
            for (Element links : element) {
                String title = links.getElementsByTag("a").text();

                String link = links.select("a").attr("href").replace("/", "").trim();
                String url = urlString + link;
                System.out.print(url);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTitle(String urlString) {
        List<String> urlListWithJS = new ArrayList<String>();
        Set<String> urlSetWithJS = new HashSet<String>();
        Map<String, String> list = new HashMap<String, String>();
        long start = System.currentTimeMillis();
        Document doc = null;
        try {
            doc = Jsoup.connect(urlString).timeout(5000).get();
            // System.out.println("doc is:" + doc.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Time is:" + (System.currentTimeMillis() - start) + "ms");
        }
        Elements elem = doc.getElementsByTag("Title");
        System.out.println("Title is:" + elem.text());
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            System.out.println(link.toString());
            urlListWithJS.add(link.toString());
            System.out.println(link.attr("href"));
            System.out.println(link.attr(">"));

        }
        System.out.println("ȥ��ǰ��ҳ���ܹ���:" + urlListWithJS.size() + "������.");
        urlSetWithJS.addAll(urlListWithJS);
        System.out.println("ȥ�غ��ҳ���ܹ���:" + urlSetWithJS.size() + "������.");

    }

    /**
     * ��htmlת���Ʊ�׼��document
     */
    public void Jsoupparse(String html) {
        // String html = "<html><head><title>First parse</title></head>"
        // + "<body><p>Parsed HTML into a doc.</p></body></html>";
        // ��������ܹ��������ܴ����ṩ��HTML�ĵ�������һ���ɾ��Ľ������������HTML�ĸ�ʽ�Ƿ�����
        Document doc = Jsoup.parse(html);
        System.out.println("doc is:" + doc.toString());
    }

    /**
     * ��htmlת���Ʊ�׼��document
     */
    public void Jsoupparse(String html, String baseurl) {
        Document doc = Jsoup.parse(html, baseurl);
        System.out.println("doc is:" + doc.toString());
    }

    /**
     * ������Ƭ��תΪ��׼��document
     *
     * @param html
     */
    public void parseBodyFragment(String html) {
        // String html1 = "<div><p>Lorem ipsum.</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        System.out.println("doc is:" + doc.toString());
        Element body = doc.body();
        System.out.println("doc is:" + body.toString());
    }

    /**
     * ͨ��url��ȡDocument
     *
     * @param url
     * @return
     */
    public void connect(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            // System.out.println("doc is:" + doc.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element link = doc.select("a").first();
        String relHref = link.attr("href"); // == "/"
        System.out.println("relHref--->" + relHref);
        String absHref = link.attr("abs:href"); // "http://www.open-open.com/"
        // ��ȡ����·��
        System.out.println("absHref--->" + absHref);
    }

    /**
     * �����ͨ��url��ȡDocument
     *
     * @param url
     * @return
     */
    public void connect1(String url) {
        try {
            Document doc = Jsoup.connect(url).data("query", "Java").userAgent("Mozilla").cookie("auth", "token")
                    .timeout(3000).post();
            System.out.println("doc is:" + doc.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listLinkes(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s>  alt:(%s)", src.tagName(), src.attr("abs:src"), src.attr("alt"));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }

    }

    /**
     * 爬取剩余页面
     *
     * @param out
     * @param url
     */
    public synchronized static void selectHongjiuInfoTwo(FileOutputStream out, String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links1 = doc.select("li[class=item]>div[class=goods-content]");
        print("\nImports: (%d)", links1.size());
        try {
            for (Element link : links1) {
                StringBuffer sb = new StringBuffer();
                sb.append(link.select("div[class=goods-pic]>a[target=_blank]").attr("title") + "\r\n");
                sb.append(link.select("div[class=goods-price]>em[class=sale-price]").attr("title") + "\r\n");
                out.write(sb.toString().getBytes("utf-8"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 爬取红酒第一个页面
     */
    public synchronized static void selectHongjiuInfoOne(Document doc, FileOutputStream out) {
        Elements links1 = doc.select("li[class=item]>div[class=goods-content]");
        print("\nImports: (%d)", links1.size());
        try {
            for (Element link : links1) {
                StringBuffer sb = new StringBuffer();
                sb.append(link.select("div[class=goods-pic]>a[target=_blank]").attr("title") + "\r\n");
                sb.append(link.select("div[class=goods-price]>em[class=sale-price]").attr("title") + "\r\n");
                out.write(sb.toString().getBytes("utf-8"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 爬取红酒
     *
     * @param url
     */
    public static void selectHongjiu(String url) {
        // 获取doc文档
        Document doc = null;
        try {
            doc = Jsoup.connect(url + "category.php?id=123").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建文件写入流
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(FileUtil.createFile("D:/news.txt"));
            Elements links1 = doc.select("form[name=selectPageForm]>div[id=pager]>div>a[class=numbers]");
            print("\nImports: (%s)", links1.size());
            selectHongjiuInfoOne(doc, out);
            for (Element link : links1) {
                selectHongjiuInfoTwo(out, url + link.attr("href"));
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void selectnews(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Elements links1 = doc.select("a[href~=.(html|shtml)]");
        Elements links1 = doc.select("div>ul>li>a[href~=.(html|shtml)]");
        // Elements links=links1.select("a[target=_blank]");
        // .select("a[class=linkto]").select("a[href$=.htm]").select("a[target=_blank]")
        print("\nImports: (%d)", links1.size());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(FileUtil.createFile("D:/news.txt"));
            int i = 0;
            for (Element link : links1) {
                StringBuffer sb = new StringBuffer();
                if (null == link.text() || "".equals(link.text())) {
                    continue;
                }
                if (!link.attr("abs:href").startsWith("http://news.sina.com.cn")) {
                    continue;
                }
                if (link.attr("abs:href").startsWith("http://m.")) {
                    continue;
                }
                i++;
                sb.append("" + link.attr("abs:href") + "-----" + link.text() + "\r\n");
                out.write(sb.toString().getBytes("utf-8"));

            }
            print("\nhttp://news.sina.com.cn----: (%d)", i);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }

    private String selectnewsList(String url) {
        // 获取doc文档
        Document doc = JsoupHttp.connect(url);
        NewsService as=new NewsServiceImpl(new NewsDaoImpl());
        List<NewsEntity> list=as.pasenews(doc,"yaowen");
        Respones<List<NewsEntity>> res=new Respones<List<NewsEntity>>();
        res.setCode("0000");
        res.setData(list);
        res.setMsg("获取成功");
        res.setStatus(true);
        Gson g=new GsonBuilder().disableHtmlEscaping().create();
//        pasenews(doc);
        return g.toJson(res);
    }

    public void pasenews(Document doc) {
        Elements links1 = doc.select("section[data-channel=yaowen]");
        print("\nImports: (%d)", links1.size());
        for (Element link : links1) {
            Elements link2 = link.select("a[href]");
            for (Element link3 : link2) {
                Log.e(Tag,link3.attr("abs:href"));
                Log.e(Tag,link3.select("a[href]>dl>dd>h3").text());
//                print("\nImports: (%d)","" + link3.attr("abs:href") + "-----" + link3.select("a[href]>dl>dd>h3").text() + "-----"
//                        + link3.select("a[href]>dl>dd>p").text() + "\r\n");
            }
        }
    }

    public void getNewsList(String url, ResponseListener responseListener) {
        this.mListener = responseListener;
        this.address = url;
        new Thread(new PostTread()).start();
    }

    /**
     * 异步线程
     */
    class PostTread implements Runnable {

        @Override
        public void run() {
            if (null == handler) {
                return;
            }
            Message msg = handler.obtainMessage();
            msg.what = 0;
            msg.obj = selectnewsList(address);
            handler.sendMessage(msg);
        }
    }

    /**
     * 回主线程刷新
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mListener != null) {
                Log.e(Tag,(String) msg.obj);
                mListener.onResponse((String) msg.obj);
            }
        }
    };

    public interface ResponseListener {

        public void onResponse(String respons);

    }
}
