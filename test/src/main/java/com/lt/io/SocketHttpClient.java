package com.lt.io;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lt.enums.HttpConstant;
import com.lt.utils.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LT
 * @Date: 2019/11/6 14:17
 * @Description:原生 socket 下的创建连接、发送请求与接收响应的所有核心代码
 * @Version 1.0
 */
public class SocketHttpClient {
    public static void main(String[] args) {

//        new SocketHttpClient().start("www.baidu.com", 80);
        SocketHttpClient.useThreadPool();
    }

    /**
     * 关于启动的线程数，一般 CPU 密集型会设置在 N+1（N为CPU核数），IO 密集型设置在 2N + 1
     * 这种方式，看起来是最优的了。那有没有更好的呢，如果一个线程能同时处理多个 socket 连接，
     * 并且在每个 socket 输入输出数据没有准备好的情况下，不进行阻塞，那是不是更优呢。
     * 这种技术叫做“IO多路复用”。在 JAVA 的 nio 包中，提供了相应的实现
     */
    public static void useThreadPool() {
        long start = System.currentTimeMillis();
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("use-thread-%d").build();
        /*
        默认执行的是抛弃策略
        默认创建的是 LinkedBlockingQueue
        默认创建的是timeunit 为 millseconds
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 9, 0L
                , TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), factory, new ThreadPoolExecutor.AbortPolicy());

        for (final String host : HttpConstant.HOSTS) {
            poolExecutor.execute(() -> {
                new SocketHttpClient().start(host, HttpConstant.PORT);
                System.out.println("haoshi:" + (System.currentTimeMillis() - start+"name:" + Thread.currentThread().getName()));
                System.out.println("count:" + poolExecutor.getActiveCount() + "-isShutdown:" + poolExecutor.isShutdown()
                        + "-count-alive:" + poolExecutor.getActiveCount());
            });
        }

        poolExecutor.shutdown();
    }

    public void start(String host, int port) {

        // 初始化 socket
        Socket socket = new Socket();

        try {
            // 设置 socket 连接
            SocketAddress remote = new InetSocketAddress(host, port);
            socket.setSoTimeout(5000);
            socket.connect(remote);

            // 发起请求
            PrintWriter writer = getWriter(socket);
            System.out.println(HttpUtil.compositeRequest(host));
            writer.write(HttpUtil.compositeRequest(host));
            writer.flush();

            // 读取响应
            String msg;
            BufferedReader reader = getReader(socket);
            while ((msg = reader.readLine()) != null) {
                System.out.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(new OutputStreamWriter(out));
    }
}
