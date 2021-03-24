package com.lt;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LT
 * @Date: 2019/11/8 17:43
 * @Description:
 * @Version 1.0
 */
public class ReplayWithProblem {

    public List<HttpUriRequest> loadMockRequest(int n) {

        List<HttpUriRequest> cache = new ArrayList<HttpUriRequest>(n);
        for (int i = 0; i < n; i++) {
            HttpGet request = new HttpGet("http://www.baidu.com?a=" + i);
            cache.add(request);
        }
        return cache;

    }

    public void start(List<HttpUriRequest> cache) throws InterruptedException {

        HttpAsyncClient httpClient = new HttpAsyncClient();
        int i = 0;

        while (true) {

            final HttpUriRequest request = cache.get(i % cache.size());
            httpClient.execute(request, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(final HttpResponse response) {
                    System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                }

                @Override
                public void failed(final Exception ex) {
                    System.out.println(request.getRequestLine() + "->" + ex);
                }

                @Override
                public void cancelled() {
                    System.out.println(request.getRequestLine() + " cancelled");
                }

            });
            i++;
            Thread.sleep(100);
        }
    }
}
