package org.interview.concurrent.singleton;

import java.time.LocalTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Singleton {

    private static volatile Singleton instance = null;

    // 私有构造方法
    private Singleton() {
        System.out.println("懒汉模式初始化。。。");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //核心线程数
        int corePoolSize = 300;
        //最大线程数
        int maximumPoolSize = 600;
        //超过 corePoolSize 线程数量的线程最大空闲时间
        long keepAliveTime = 2;
        //以秒为时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        //创建工作队列，用于存放提交的等待执行任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(300);
        ThreadPoolExecutor threadPoolExecutor = null;
        //ExecutorService pool = Executors.newFixedThreadPool(5);
        try {
            //创建线程池
            threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    unit,
                    workQueue,
                    new ThreadPoolExecutor.CallerRunsPolicy());

            //循环提交任务
            for (int i = 0; i < 10; i++) {
                //提交任务的索引
                final int index = (i + 1);
                threadPoolExecutor.submit(() -> {
                    //线程打印输出
                    System.out.println("【" + LocalTime.now() + "】线程 " + Thread.currentThread() + "正在执行任务" + index);
                    try {
                        //模拟线程执行时间
                        Thread.sleep(50);
                        Singleton.getInstance();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                //每个任务提交后休眠500ms再提交下一个任务，用于保证提交顺序
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
