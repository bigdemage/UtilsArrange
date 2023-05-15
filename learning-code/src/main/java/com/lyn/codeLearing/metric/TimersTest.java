package com.lyn.codeLearing.metric;


import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * histogram和meter的结合，histogram某部分代码/调用的耗时，meter统计tps
 */
@Slf4j
public class TimersTest {
    public static Random random=new Random();

    public static void main(String[] args) throws InterruptedException {

        MetricRegistry registry=new MetricRegistry();
        ConsoleReporter reporter=ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        Timer timer=registry.timer(MetricRegistry.name(TimersTest.class,"get-latency"));
        Timer.Context ctx;
        while(true){
            ctx=timer.time();
            Thread.sleep(RandomUtils.nextInt(1,1000));
            ctx.stop();
        }
    }
}
