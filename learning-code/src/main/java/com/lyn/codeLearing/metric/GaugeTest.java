package com.lyn.codeLearing.metric;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 统计待处理队列中的任务个数
 */
@Slf4j
public class GaugeTest {
    public static Queue<String> q = new LinkedList<String>();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        //长度
        Gauge gauge=()->q.size();
        //2秒一次
        reporter.start(2, TimeUnit.SECONDS);
        registry.register(MetricRegistry.name(GaugeTest.class, "queue", "size"),gauge);
        while (true) {
            Thread.sleep(RandomUtils.nextInt(500, 2000));
            q.add("job-xxx");
        }
    }
}
