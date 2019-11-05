package com.lt.rabbit.demo.listen;

/**
 * @Author: LT
 * @Date: 2019/11/5 17:45
 * @Description:
 * @Version 1.0
 */
public class Listen {
    //具体执行业务的方法
    public void listen(String msg) {
        System.out.println("\n消费者B开始处理消息： " + msg + "\n");
    }
}
