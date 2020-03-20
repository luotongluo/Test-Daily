package com.lt.thread.cooperation;

/**
 * @author Administrator
 * @description DeadLock
 * @date 2020/3/19 11:04
 */
public class DeadLock extends Thread {
    protected Object tool;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public DeadLock(Object tool) {
        this.tool = tool;
        if (tool == o1) {
            this.setName("哲学家A");
        }
        if (tool == o2) {
            this.setName("哲学家B");
        }
    }

    @Override
    public void run() {
        if (tool == o1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            synchronized (o2){

            }
        }
    }
}
