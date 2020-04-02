package com.lt;

import org.apache.http.client.methods.HttpUriRequest;

import java.util.List;

/**
 * @Author: LT
 * @Date: 2019/11/8 18:05
 * @Description:
 * @Version 1.0
 */
public class ReplayApplication {

    public static void main(String[] args) throws InterruptedException {

        //创建有内存泄露的回放客户端
        ReplayWithProblem replay1 = new ReplayWithProblem();

        //加载一万条请求数据放入缓存
        List<HttpUriRequest> cache1 = replay1.loadMockRequest(10000);

        //开始循环回放
        replay1.start(cache1);

    }
}
