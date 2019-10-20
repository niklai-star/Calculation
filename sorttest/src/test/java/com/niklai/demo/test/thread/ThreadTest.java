package com.niklai.demo.test.thread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    @DisplayName("Thread Test")
    public void test() throws InterruptedException {
        int count = 10;

        ThreadDemo demo = new ThreadDemo(count);
        new Thread(demo).start();
        new Thread(demo).start();

        Thread.sleep(30000);
    }

    @Test
    @DisplayName("Thread Print Test")
    public void printTest() throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        // 交替输出ABC共10次
        new Thread(new PrintThread("A", c, a)).start();
//        Thread.sleep(10);
        new Thread(new PrintThread("B", a, b)).start();
        new Thread(new PrintThread("C", b, c)).start();
//        new Thread(new PrintThread("C")).start();

        Thread.sleep(1000);
    }
}

class ThreadDemo implements Runnable {
    private static final Object LOCK = new Object();

    private int c;

    public ThreadDemo(int count) {
        c = count;
    }

    @Override
    public void run() {
        int threadNum = Integer.parseInt(Thread.currentThread().getName().substring(7));

        while (true) {
            synchronized (LOCK) {
                try {
                    if (c > 0) {
                        Thread.sleep(200);
                        System.out.println("线程" + threadNum + "输出：" + c--);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class PrintThread implements Runnable {
    private static final Object LOCK = new Object();

    private Object prevLock;
    private Object currLock;

    private String content;

    public PrintThread(String content, Object prevLock, Object currLock) {
        this.content = content;
        this.prevLock = prevLock;
        this.currLock = currLock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            synchronized (prevLock) {
                synchronized (currLock) {
                    System.out.println(this.content);
                    currLock.notify();
                }

                try {
                    prevLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
