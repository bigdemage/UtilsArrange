package com.lyn.codeLearing.metric;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 统计数据分布请款
 * 最小值，最大值，中间值，还有中位数，75%，90%，95%，98%，99%，99.9%
 * 下面代码展示的是request大小的分布
 * api指标如何获取 https://blog.csdn.net/qq330983778/article/details/124657979
 */
@Slf4j
public class HistogramTest {
    private static  List<Integer> list=new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry=new MetricRegistry();
        ConsoleReporter reporter=ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        Histogram histogram=new Histogram(new ExponentiallyDecayingReservoir());
        registry.register(MetricRegistry.name(HistogramTest.class,"request","histogram"),histogram);
        while(true){
            Thread.sleep(RandomUtils.nextInt(10,500));
            Integer l=RandomUtils.nextInt(1,100000);
            list.add(l);
            list=list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            log.info("list：{}",list);
            histogram.update(l);
            if(list.size()==100) break;
        }
    }

}
