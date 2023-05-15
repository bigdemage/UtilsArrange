package com.lyn.codeLearing.metric;


import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 度量发生的速率，如tps，meters会统计最近1分钟，5分钟，15分钟还有全部时间的速率
 */
@Slf4j
public class MetersTest {
    public static Random random=new Random();

    public static void request(Meter meter){
        log.info("request");
        meter.mark();
    }

    public static void request(Meter meter,int n){
        while(n>0){
            request(meter);
            n--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry=new MetricRegistry();
        ConsoleReporter reporter= ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        Meter meterTps=registry.meter(MetricRegistry.name(MetersTest.class,"request","tps"));

        while(true){
            request(meterTps,random.nextInt(5));
            Thread.sleep(1000);
        }
    }

}
