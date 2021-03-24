package com.lt.utils.utils.http;

import com.lt.utils.utils.tools.Params;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    public static final String URL_SPLIT_PARAM = "&";

    public HttpUtil() {
    }

    public static String sendPost(String url, String reqBody) {
        return sendPost(url, reqBody, Params.CONNECT_TIME_OUT, Params.SO_TIME_OUT);
    }

    public static String sendPost(String url, String reqBody, String charset) {
        return sendPost(url, reqBody, Params.CONNECT_TIME_OUT, Params.SO_TIME_OUT, charset);
    }

    public static String sendPost(String url, String reqBody, int connectTimeOut, int soTimeOut) {
        return sendPost(url, reqBody, connectTimeOut, soTimeOut, "UTF-8");
    }

    public static String sendPost(String url, String reqBody, int connectTimeOut, int soTimeOut, String charset) {
        HttpClient client = new HttpClient();
        String respBody = null;

        try {
            System.out.println("发送POST报文:" + reqBody);
            PostMethod method = new PostMethod(url);
            method.setRequestEntity(new StringRequestEntity(reqBody, "application/json", charset));
            client.getHttpConnectionManager().getParams().setConnectionTimeout(connectTimeOut);
            client.getHttpConnectionManager().getParams().setSoTimeout(soTimeOut);
            int respCode = client.executeMethod(method);
            System.out.println("应答状态码:" + respCode);
            InputStream in = method.getResponseBodyAsStream();
            respBody = IOUtils.toString(in, charset);
            System.out.println("接收报文:" + respBody);
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return respBody;
    }

    public static Map<String, Object> urlRequest(String url) {
        Map<String, Object> mapRequest = new HashMap();
        String[] arrSplit = null;
        if (url == null) {
            return mapRequest;
        } else {
            arrSplit = url.split("[&]");
            String[] var4 = arrSplit;
            int var5 = arrSplit.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                String strSplit = var4[var6];
                String[] arrSplitEqual = null;
                arrSplitEqual = strSplit.split("[=]");
                if (arrSplitEqual.length > 1) {
                    mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
                } else if (arrSplitEqual[0] != "") {
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }

            return mapRequest;
        }
    }

    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;
        strURL = strURL.trim();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1 && arrSplit.length > 1 && arrSplit[1] != null) {
            strAllParam = arrSplit[1];
        }

        return strAllParam;
    }

    public static String httpGet(String url) throws IOException {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
        InputStream in = method.getResponseBodyAsStream();
        String respBody = IOUtils.toString(in, StandardCharsets.UTF_8.name());
        return respBody;
    }

    public static String httpGetByParam(String url, Map<String, String> param) throws IOException {
        String paramStr = HttpClientMethod.buildQuery(param, Params.CHARSET);
        StringBuffer httpUrl = new StringBuffer(url);
        if (!StringUtils.isEmpty(paramStr)) {
            httpUrl.append("?").append(paramStr);
        }

        return httpGet(httpUrl.toString());
    }

    public static String httpRequest(String url) {
        return httpRequest(url, Params.CONNECT_TIME_OUT, Params.SO_TIME_OUT);
    }

    public static String httpRequest(String reqUrl, Integer connTimeOut, Integer readTimeOut) {
        StringBuffer buffer = new StringBuffer();

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setConnectTimeout(connTimeOut);
            httpUrlConn.setReadTimeout(readTimeOut);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;

            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            httpUrlConn.disconnect();
        } catch (Exception var10) {
            System.out.println(var10.getStackTrace());
        }

        return buffer.toString();
    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;

        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            Iterator var8 = map.keySet().iterator();

            while (var8.hasNext()) {
                String key = (String) var8.next();
                System.out.println(key + "--->" + map.get(key));
            }

            String line;
            for (in = new BufferedReader(new InputStreamReader(connection.getInputStream())); (line = in.readLine()) != null; result = result + line) {
            }
        } catch (Exception var18) {
            System.out.println("发送GET请求出现异常！" + var18);
            var18.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception var17) {
                var17.printStackTrace();
            }

        }

        return result;
    }
}
