package com.shihaiyang.world.effective_java.concurrency;

import java.util.concurrent.TimeUnit;

/*
78:同步访问共享的可变数据
 */
public class StopThread_78 {
    // 无法停止主线程，因为修改不可见
//    private static boolean stopRequested;
    private static volatile boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}

