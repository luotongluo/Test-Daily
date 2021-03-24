package com.lt.leecode;

import java.util.concurrent.Semaphore;

/**
 * @author luotong
 * @description DiningPhilosophers
 * 5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）
 * <p>
 * 所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，而同一根叉子在同一时间只能被一个哲学家使用。每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。
 * <p>
 * 假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。
 * <p>
 * 设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。
 * 哲学家从 0 到 4 按 顺时针 编号。请实现函数 void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork)：
 * <p>
 * philosopher 哲学家的编号。
 * pickLeftFork 和 pickRightFork 表示拿起左边或右边的叉子。
 * eat 表示吃面。
 * putLeftFork 和 putRightFork 表示放下左边或右边的叉子。
 * 由于哲学家不是在吃面就是在想着啥时候吃面，所以思考这个方法没有对应的回调。
 * 给你 5 个线程，每个都代表一个哲学家，请你使用类的同一个对象来模拟这个过程。在最后一次调用结束之前，可能会为同一个哲学家多次调用该函数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-dining-philosophers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/4/13 16:13
 */
public class DiningPhilosophers {
    int num = 5;
    //五个叉子的信号量
    private Semaphore[] semaphores = new Semaphore[5];

    public DiningPhilosophers() {
        for (int i = 0; i < num; i++) {
            //每只叉子只有1个
            semaphores[i] = new Semaphore(1);
        }

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        //左边叉子的位置
        int left = philosopher;
        //右边叉子的位置
        int right = (philosopher + 1) % num;
        while (true) {
            if (semaphores[left].tryAcquire()) {
                //先尝试获取左边叉子，如果成功再尝试获取右边叉子
                if (semaphores[right].tryAcquire()) {
                    //两个叉子都得到了，进餐
                    pickLeftFork.run();
                    pickRightFork.run();
                    eat.run();
                    putLeftFork.run();
                    //释放左边叉子
                    semaphores[left].release();
                    putRightFork.run();
                    //释放右边边叉子
                    semaphores[right].release();

                    //吃完了，就跳出循环
                    break;
                } else {
                    //如果拿到了左边的叉子，但没拿到右边的叉子： 就释放左边叉子
                    semaphores[left].release();
                    //让出cpu等一会
                    Thread.yield();
                }
            } else {
                //连左边叉子都没拿到，就让出cpu等会吧
                Thread.yield();
            }
        }

    }
}
