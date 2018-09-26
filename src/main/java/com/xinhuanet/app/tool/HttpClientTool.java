package com.xinhuanet.app.tool;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 模拟HTTP客户端访问，测试相关接口
 * 
 * @author lvwei
 *
 */
@SuppressWarnings("all")
public class HttpClientTool {
    private final static boolean DEBUG = true;

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientTool.class);

    public static void main(String args[]) throws Exception {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("contentId", "11111111");
//        map.put("channelIds", "123,123,2,3,23,2,332");
////        map.put("Content-type", "text/xml; charset=utf-8");
//
//        String res = HttpClientTool.getURL("https://x.app.news.cn/wxapp/api/v1/del_news", map,null);
////       String res = HttpClientTool.getURL("http://news.xinhuanet.com/politics/2016-10/10/c_129316007.htm", null,null);
//        System.out.println(res);
////        System.out.println(new String(res.getBytes(),"gb2312"));
        String resStr = "<script> \n" +
                "window.alert(\"url不合法\"); \n" +
                "window.location = unescape(\"/auto/Auto_task_short_video_list.html?1505890532\"); \n" +
                "</script> ";
        System.out.println(resStr.substring(resStr.indexOf("window.alert(") + 14, resStr.indexOf("window.location")-5));
    }

    /**
     * 
     * @param pageUrl
     *            eg: "http://www.news.cn"
     * @param pdata
     * @param proxy
     *            eg:new HttpHost("192.168.2.59",8080), 如果没有使用代理 ：null
     * @return
     * @throws IOException
     */
    public static String post(String pageUrl, Map<String, String> pdata, HttpHost proxy) throws IOException {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : pdata.entrySet()) {
            String name = entry.getKey().toString();
            String value = entry.getValue();
            if (value != null) {
                formparams.add(new BasicNameValuePair(name, value));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
        HttpPost httppost = new HttpPost(pageUrl);
        httppost.setEntity(entity);

        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse res = null;
        try {
            if (proxy != null) {
                RequestConfig config = RequestConfig.custom().setProxy(proxy).setMaxRedirects(3)
                        .setConnectTimeout(30000).build();
                httppost.setConfig(config);
            }
            res = httpclient.execute(httppost);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity hentity = res.getEntity();
                return EntityUtils.toString(hentity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                res.close();
            }
        }

        return null;
    }

    /**
     *
     * @param pageUrl
     * @param header
     * @param pdata
     * @param proxy
     * @param charset
     * @return
     * @throws IOException
     */
    public static String post(String pageUrl,Map<String, String> header, Map<String, String> pdata, HttpHost proxy, String charset) throws IOException {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : pdata.entrySet()) {
            String name = entry.getKey().toString();
            String value = entry.getValue();
            if (value != null) {
                formparams.add(new BasicNameValuePair(name, value));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, charset);
        entity.setContentEncoding(charset);
        HttpPost httppost = new HttpPost(pageUrl);
        for (Map.Entry<String, String> entry : header.entrySet()) {
            String name = entry.getKey().toString();
            String value = entry.getValue();
            if (value != null) {
                httppost.setHeader(name, value);
            }
        }
        httppost.setEntity(entity);

        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse res = null;
        try {
            if (proxy != null) {
                RequestConfig config = RequestConfig.custom().setProxy(proxy).setMaxRedirects(3)
                        .setConnectTimeout(30000).build();
                httppost.setConfig(config);
            }
            res = httpclient.execute(httppost);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity hentity = res.getEntity();
                return EntityUtils.toString(hentity, Charset.forName(charset));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                res.close();
            }
        }

        return null;
    }

    public static String getURL(String url, Map<String, String> pdata, HttpHost proxy) throws Exception {
        url = getParamsURL(url, pdata);
        HttpGet get = new HttpGet(url);
        return get(get, proxy);

    }

    public static String getParamsURL(String url, Map<String, String> pdata) throws Exception {
        log("Request:");
        log("GET:" + url);
        if (null != pdata && pdata.size() > 0) {
            String encodedParams = HttpClientTool.encodeParameters(pdata);
            if (-1 == url.indexOf("?")) {
                url += "?" + encodedParams;
            } else {
                url += "&" + encodedParams;
            }
        }
        return url;
    }

    public static String encodeParameters(Map<String, String> postParams) {
        StringBuffer buf = new StringBuffer();
        Iterator<String> it = postParams.keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (i != 0) {
                buf.append("&");
            }
            try {
                String key = it.next();
                buf.append(URLEncoder.encode(key, "UTF-8")).append("=")
                        .append(URLEncoder.encode(postParams.get(key), "UTF-8"));
            } catch (java.io.UnsupportedEncodingException neverHappen) {
            }
            i++;
        }
        return buf.toString();
    }

    public static String get(HttpGet method, HttpHost proxy) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse res = null;
        try {
            if (proxy != null) {
                RequestConfig config = RequestConfig.custom().setProxy(proxy).setMaxRedirects(3)
                        .setConnectTimeout(30000).build();
                method.setConfig(config);
                method.addHeader("Content-type" , "text/html; charset=utf-8");
            }
            res = httpclient.execute(method);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                return EntityUtils.toString(entity,"UTF-8");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                res.close();
            }
        }
        return null;
    }


    private static void log(String message) {
        if (DEBUG) {
            LOGGER.debug(message);
        }
    }

}
