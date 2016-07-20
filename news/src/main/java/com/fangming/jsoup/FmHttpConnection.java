package com.fangming.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fangming on 2016-4-28.
 */
public class FmHttpConnection{

    /**
     * @param url
     * @return string类型请求结果
     */
    public static String getStringRes(String url) {
        HttpURLConnection httpConn = null;
        String html = "";
        try {
            URL murl = new URL(url);
            httpConn = (HttpURLConnection) murl.openConnection();
            httpConn.setConnectTimeout(10000);
            //读取数据超时也是10s
            httpConn.setReadTimeout(15000);
            httpConn.setChunkedStreamingMode(0);
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            InputStream inStrm = httpConn.getInputStream();
            byte[] data = read(inStrm);
            inStrm.close();
            html = new String(data, "UTF-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpConn.disconnect();
        }
        return html;
    }

    /**
     * 从流中读取结果
     *
     * @param inStream 输出流
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        return outStream.toByteArray();
    }

    public static Document parse(String res){
        Document document = Jsoup.parse(res);
        return document;
    }
}
