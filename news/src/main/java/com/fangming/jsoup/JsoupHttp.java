package com.fangming.jsoup;

import org.jsoup.nodes.Document;

/**
 * Created by fangming on 2016-4-28.
 */
public class JsoupHttp {
    public JsoupHttp() {
    }

    public static Document connect(String url) {
        return FmHttpConnection.parse(FmHttpConnection.getStringRes(url));
    }


}
