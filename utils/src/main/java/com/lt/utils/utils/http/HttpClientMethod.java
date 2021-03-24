package com.lt.utils.utils.http;

import com.lt.utils.utils.tools.Params;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpClientMethod {
    private static Logger logger = LoggerFactory.getLogger(HttpClientMethod.class);

    public HttpClientMethod() {
    }

    public static void main(String[] args) {
    }

    public static String doGet(String url, String queryString, String charset, boolean pretty) {
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        method.addRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

        try {
            method.getParams().setParameter("http.protocol.cookie-policy", "compatibility");
            if (queryString != null) {
                method.setQueryString(URIUtil.encodeQuery(queryString));
                client.executeMethod(method);
                if (method.getStatusCode() == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (pretty) {
                            response.append(line).append(System.getProperty("line.separator"));
                        } else {
                            response.append(line);
                        }
                    }

                    reader.close();
                }
            }
        } catch (IOException var12) {
        } finally {
            method.releaseConnection();
        }

        return response.toString();
    }

    public static String postHttpRequst(String url, Map<String, String> params, String charset, Integer connTimeOut, Integer readTimeOut) throws IOException {
        HttpURLConnection conn = null;
        OutputStreamWriter out = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer result = new StringBuffer();

        try {
            conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setInstanceFollowRedirects(false);
            conn.setConnectTimeout(connTimeOut);
            conn.setReadTimeout(readTimeOut);
            out = new OutputStreamWriter(conn.getOutputStream(), charset);
            out.write(buildQuery(params, charset));
            out.flush();
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, charset);
            reader = new BufferedReader(inputStreamReader);
            String tempLine = null;

            while ((tempLine = reader.readLine()) != null) {
                result.append(tempLine);
            }
        } catch (IOException var15) {
            var15.printStackTrace();
            throw new RuntimeException("通过Http Post请求对接接口出现异常：" + var15.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return result.toString();
    }

    public static String postHttpRequst(String url, Map<String, String> params, String charset) throws IOException {
        return postHttpRequst(url, params, charset, Params.CONNECT_TIME_OUT, Params.SO_TIME_OUT);
    }

    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params != null && !params.isEmpty()) {
            StringBuffer data = new StringBuffer();
            boolean flag = false;
            Iterator var4 = params.entrySet().iterator();

            while (var4.hasNext()) {
                Entry<String, String> entry = (Entry) var4.next();
                if (entry.getKey() != null && entry.getValue() != null) {
                    if (flag) {
                        data.append("&");
                    } else {
                        flag = true;
                    }

                    data.append((String) entry.getKey()).append("=").append(URLEncoder.encode(String.valueOf(entry.getValue()), charset));
                }
            }

            return data.toString();
        } else {
            return null;
        }
    }

    public static String doPostXml(String url, String xml, String contentType, String charset) {
        PostMethod post = new PostMethod(url);

        try {
            RequestEntity requestEntity = new StringRequestEntity(xml, contentType, charset);
            post.setRequestEntity(requestEntity);
            post.getParams().setParameter("http.protocol.cookie-policy", "compatibility");
            String bodyContent = post.getResponseBodyAsString();
            String var7 = bodyContent;
            return var7;
        } catch (IOException var12) {
            var12.printStackTrace();
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            post.releaseConnection();
        }

        return "";
    }

    public static String GetResponseDataByID(String url, String postData) {
        String data = null;

        try {
            URL dataUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Proxy-Connection", "Keep-Alive");
            con.setDoOutput(true);
            con.setDoInput(true);
            OutputStream os = con.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.write(postData.getBytes());
            dos.flush();
            dos.close();
            InputStream is = con.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            byte[] d = new byte[dis.available()];
            dis.read(d);
            data = new String(d);
            data = URLDecoder.decode(data, "UTF-8");
            con.disconnect();
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return data;
    }

    public static String readContentFromPost(String POST_URL, String content, String charsetName) throws IOException {
        String data = null;
        URL postUrl = new URL(POST_URL);
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(content);
        out.flush();
        out.close();
        if (StringUtils.isEmpty(charsetName)) {
            charsetName = Params.CHARSET;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charsetName));

        for (String line = ""; (line = reader.readLine()) != null; data = line) {
        }

        reader.close();
        connection.disconnect();
        return data;
    }

    public static String PostXml(String url, String xml) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            System.out.println(xml);
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "text/html;charset=UTF-8");
            StringEntity stringEntity = new StringEntity(xml, "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            httpPost.setEntity(stringEntity);
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = (String) httpclient.execute(httpPost, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            return responseBody;
        } catch (Exception var7) {
            System.out.println(var7);
            return null;
        }
    }
}
