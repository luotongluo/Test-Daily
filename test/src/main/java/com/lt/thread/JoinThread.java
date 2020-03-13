package com.lt.thread;

/**
 * @author Administrator
 * @description JoinThread
 * 等待线程结束（join）和谦让（yield）：类似现实生活中，我做这件事得等着它做完那件事，我为他让让步等
 * @date 2020/3/13 10:43
 */
public class JoinThread {
    volatile static int i = 0;

    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 10000; i++) ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();
        //主线程等待addthred执行完毕
        addThread.join();
        System.out.println(i);
    }
}
