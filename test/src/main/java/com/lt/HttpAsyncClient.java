package com.lt;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;

/**
 * @Author: LT
 * @Date: 2019/11/8 17:42
 * @Description: 内存泄漏处理
 * https://github.com/jasonGeng88/blog/blob/master/201710/java-analysis.md
 * @Version 1.0
 */
public class HttpAsyncClient {
    private CloseableHttpAsyncClient httpclient;

    public HttpAsyncClient() {
        httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
    }

    public void execute(HttpUriRequest request, FutureCallback<HttpResponse> callback) {
        httpclient.execute(request, callback);
    }

    public void close() throws IOException {
        httpclient.close();
    }

}


