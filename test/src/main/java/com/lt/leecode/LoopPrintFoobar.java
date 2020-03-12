package com.lt.leecode;

/**
 * @author LT
 * @description LoopPrintFoobar
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * @date 2020/3/11 16:54
 */
public class LoopPrintFoobar {
    public static void main(String[] args)throws Exception {
        FooBar fooBar = new FooBar(5);
        fooBar.bar(new Runnable() {
            @Override
            public void run() {
//                System.out.println("foo");
            }
        });
        fooBar.foo(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

}

class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            System.out.println("foo");
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            System.out.println("bar");
        }
    }
}