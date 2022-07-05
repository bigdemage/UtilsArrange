package com.lyn.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 单例线程池
 * 创建三种线程池
 */
public class ThreadPoolSin {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolSin.class);
    /**
     * Fix线程池默认线程池大小10
     */
    private final static int MAX_POOL = 10;


    /**
     * 简单翻译一下就是说这个类创建了些个固定数目的共享线程，即实现规定好创建的线程的数量，后面不能更改数量。
     * 在任何一个时间点，活跃工作的线程数量都不能够超过这个最大的数量。
     * 当所有的线程都在工作时，如果这时候有一个新的线程需要被创建，那么这个需要创建的线程需要就会被放进一个等待队列BlockingQueue，直到有空余的线程可以使用才执行。
     * 如果有一个线程在执行期间由于发生了错误而终止运行，一个新的线程会接替这个终止的线程完成就下来的任务。
     * 线程池中的线程们会一直存在直到被要求关闭。
     * 初始化时指定n Threads线程池线程数量上限。
     */
    private static class FixExecutorInstance {
        private static ExecutorService executorService = Executors.newFixedThreadPool(MAX_POOL);
    }

    public static ExecutorService getFixedThread() {
        ExecutorService executorService = FixExecutorInstance.executorService;
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        logger.info("[服务启动初始化线程池]-核心池大小:{},最大线程数:{},当前线程数:{},当前活动线程数:{}", threadPoolExecutor.getCorePoolSize(),
                threadPoolExecutor.getMaximumPoolSize(), threadPoolExecutor.getPoolSize(), threadPoolExecutor.getActiveCount());
        logger.info("[服务启动初始化线程池]-总任务数:{},已经执行完毕的任务数:{},出现过最大的线程数:{}", threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(), threadPoolExecutor.getLargestPoolSize());
        return executorService;
    }

    /*
        创建一个可以动态增长的线程池，当我们有新的任务提交时，但是当前线程池并没有空余的线程可以供我们使用，这时候就会新建一个线程，如果有以前创建的线程空闲下来，则不会创建新的线程而是使用以前已经创建的。
        当一个线程空闲下来超过60秒，这个线程就会被从缓存线程池中移除。
        这种缓存线程池在应对那些执行时间很短的异步任务时表现很好。
     */
    private static class CacheExecutorInstance {
        private static ExecutorService executorService = Executors.newCachedThreadPool();
    }

    public static ExecutorService getCacheThread() {
        ExecutorService executorService = CacheExecutorInstance.executorService;
        return executorService;
    }

    /**
     * 顾名思义，这个线程池里只有一个线程，任何一个时刻，最多只有一个线程在工作。
     * 每个被提交地任务按照先提交先执行的原则依次执行。
     * 当原来的线程出现问题被终止了，会有一个新的线程被创建去执行后面的任务。
     * 这个线程的阻塞队列采用了无边界的LinkedBlockingQueue。
     */
    private static class SinExecutorThread {
        private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

    public static ExecutorService getSinThread() {
        ExecutorService executorService = SinExecutorThread.executorService;
        return executorService;
    }




}
