package com.shihaiyang.world.effective_java.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/*
78:同步访问共享的可变数据
 */
public class StopThread_78 {
    // 无法停止主线程，因为修改不可见
//    private static boolean stopRequested;
    private static volatile boolean stopRequested;
    private static AtomicLong atomicLong = new AtomicLong();
    private static int i = 0;

    private static long generateLong() {
        return atomicLong.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            while (!stopRequested) {
                i++;
                generateLong();
            }
        });
        backgroundThread.start();
        Thread backgroundThread2 = new Thread(() -> {
            while (!stopRequested) {
                i++;
                generateLong();
            }
        });
        backgroundThread2.start();
        TimeUnit.MILLISECONDS.sleep(1);
        stopRequested = true;
        // 14299==11669
        System.out.println(atomicLong.get() + "==" + i);
    }
}

