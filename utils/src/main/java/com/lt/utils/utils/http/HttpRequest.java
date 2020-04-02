package com.lt.utils.utils.http;

import com.lt.utils.utils.tools.Params;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequest {
    public HttpRequest() {
    }

    public static String sendPost(String url, String param) {
        return sendPost(url, param, Params.NOTIFICATION_CONNECT_TIME_OUT, Params.NOTIFICATION_READ_TIME_OUT);
    }

    public static String sendPost(String url, String param, int connTimeout, int readTimeout) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(connTimeout);
            conn.setReadTimeout(readTimeout);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();

            String line;
            for(in = new BufferedReader(new InputStreamReader(conn.getInputStream())); (line = in.readLine()) != null; result = result + line) {
            }
        } catch (Exception var18) {
            System.out.println("发送 POST 请求出现异常！" + var18);
            var18.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    in.close();
                }
            } catch (IOException var17) {
                var17.printStackTrace();
            }

        }

        return result;
    }

    public static String postRaw(String url, String msg) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        StringEntity postingString = new StringEntity(msg);
        post.setEntity(postingString);
        post.setHeader("Content-type", "text/plain");
        HttpResponse response = httpClient.execute(post);
        String content = EntityUtils.toString(response.getEntity());
        return content;
    }
}
