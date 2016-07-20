package com.fm.jsoup;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

public class JsoupUtil {
	private static final JsoupUtil getinstance = new JsoupUtil();

	public static JsoupUtil getInstance() {
		return getinstance;
	}

	public void getdDocument(String urlString) {
		Document document = null;
		try {
			document = Jsoup.parse(new URL(urlString), 5000);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Elements es = document.getElementsByClass("topic");
		for (Element e : es) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", e.getElementsByTag("a").text());
			map.put("href", "http://www.cnbeta.com"
					+ e.getElementsByTag("a").attr("href"));
			Log.d("test", e.getElementsByTag("a").attr("href"));
			list.add(map);
		}
	}

	/**
	 * @param urlString
	 * @return html内容
	 */
	public String getHtmlString(String urlString) {
		String returnStr = "";
		try {
			URL url = new URL(urlString);
			URLConnection ucon = url.openConnection();
			InputStream instr = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(instr);
			ByteArrayBuffer baf = new ByteArrayBuffer(500);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}
			returnStr = EncodingUtils.getString(baf.toByteArray(), "UTF-8");
			System.out.print(returnStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnStr;
	}

	public String getTitle(String urlString) {
		String htmlString = getHtmlString(urlString);
		Document document = Jsoup.parse(htmlString);
		String title = document.head().getElementsByTag("title").text();
		System.out.print(title);
		return title;
	}

	public String getbody(String urlString) {
		String htmlString = getHtmlString(urlString);
		Document document = Jsoup.parse(htmlString);
		String title = document.head().getElementsByTag("title").text();
		System.out.print(title);
		return title;
	}

}
