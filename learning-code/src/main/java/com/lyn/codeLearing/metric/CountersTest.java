package com.lyn.codeLearing.metric;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 计数器
 */
@Slf4j
public class CountersTest {

    public static Queue<String> q=new LinkedBlockingDeque<String>();
    public static Counter pendingJobs;
    public static Random random=new Random();
    public static void addJob(String job){
        pendingJobs.inc();
        q.offer(job);
    }
    public static String takeJob(){
        pendingJobs.dec();
        return q.poll();
    }

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry=new MetricRegistry();
        //控制台，用于输出
        ConsoleReporter reporter= ConsoleReporter.forRegistry(registry).build();
        reporter.start(2, TimeUnit.SECONDS);


        pendingJobs=registry.counter(MetricRegistry.name(CountersTest.class,"pending-jobs","size"));


        int num=1;
        while(true){
            Thread.sleep(200);
            if (random.nextDouble() > 0.7){
                String job = takeJob();
                log.info("take job : {}",job);
            }else{
                String job = "Job-"+num;
                addJob(job);
                log.info("add job : {}",job);
            }
            //用api获取count
            log.info("log count:{}",pendingJobs.getCount());
            num++;
        }
    }
}
