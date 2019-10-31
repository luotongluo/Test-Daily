package com.lt.utils.utils.http;

import com.lt.utils.utils.tools.Params;
import org.apache.http.HttpEntity;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpConnectionPoolUtil {
    private static CloseableHttpClient httpClient;
    private static Logger logger = LoggerFactory.getLogger(HttpConnectionPoolUtil.class);

    public HttpConnectionPoolUtil() {
    }

    public static String sendPost(String url, String reqBody) {
        return sendPost(url, reqBody, Params.CONNECT_TIME_OUT, Params.CONNECT_TIME_OUT + Params.SO_TIME_OUT, Params.SO_TIME_OUT);
    }

    public static RequestConfig requestConfig(int connectTimeOut, int requestTimeOut, int soTimeOut) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut).setConnectionRequestTimeout(requestTimeOut).setSocketTimeout(soTimeOut).build();
        return requestConfig;
    }

    public static String sendPost(String url, String reqBody, int connectTimeOut, int requestTimeOut, int soTimeOut) {
        HttpPost httpPost = new HttpPost(url);
        String resContent = null;
        CloseableHttpResponse httpResponse = null;

        try {
            StringEntity stringEntity = new StringEntity(reqBody, Params.CHARSET);
            stringEntity.setContentEncoding(Params.CHARSET);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            httpPost.setConfig(requestConfig(connectTimeOut, requestTimeOut, soTimeOut));
            logger.info("发送Post请求参数：{}", reqBody);
            httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            logger.info("相应状态：{}", statusCode);
            if (200 == statusCode) {
                resContent = EntityUtils.toString(httpResponse.getEntity(), Params.CHARSET);
                logger.info("接收到的返回参数：{}", resContent);
            }
        } catch (ClientProtocolException var20) {
            logger.error("send Post ClientProtocolException ", var20);
        } catch (IOException var21) {
            logger.error("send Post IOException ", var21);
        } finally {
            try {
                if (null != httpResponse) {
                    httpResponse.close();
                }
            } catch (IOException var19) {
                logger.error("send Post IOException ", var19);
            }

        }

        return resContent;
    }

    public static String sendGet(String url) {
        return sendGet(url, Params.CONNECT_TIME_OUT, Params.CONNECT_TIME_OUT + Params.SO_TIME_OUT, Params.SO_TIME_OUT);
    }

    public static String sendGet(String url, int connectTimeOut, int requestTimeOut, int soTimeOut) {
        String respBody = null;
        CloseableHttpResponse response = null;

        try {
            HttpGet httpget = new HttpGet(url);
            logger.info("发送httpGet请求参数： " + httpget.getURI());
            httpget.setConfig(requestConfig(connectTimeOut, requestTimeOut, soTimeOut));
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine());
            if (entity != null) {
                respBody = EntityUtils.toString(entity);
                logger.info("接受httpGet返回参数: " + respBody);
            }
        } catch (ClientProtocolException var20) {
            logger.error("send Get ClientProtocolException ", var20);
        } catch (ParseException var21) {
            logger.error("send Get ParseException ", var21);
        } catch (IOException var22) {
            logger.error("send Get IOException ", var22);
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException var19) {
                logger.error("send Get IOException ", var19);
            }

        }

        return respBody;
    }

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(2000);
        cm.setDefaultMaxPerRoute(1000);
        cm.setValidateAfterInactivity(2000);
        httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler((exception, executionCount, context) -> {
            if (executionCount > 2) {
                logger.warn("Maximum tries reached for client http pool ,executionCount:{}", executionCount);
                return false;
            } else if (!(exception instanceof NoHttpResponseException) && !(exception instanceof ConnectTimeoutException)) {
                return false;
            } else {
                logger.warn("NoHttpResponseException on " + executionCount + " call");
                return true;
            }
        }).build();
    }
}
