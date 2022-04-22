package com.lyn.codeLearing.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName LockTest
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/3/28/028 16:05
 * @Version 1.0
 **/
public class LockTest {

    private static RedissonClient redissonClient;

    static{
        Config config=new Config();
        String[] ips="redis://172.16.163.60:7000,redis://172.16.163.60:7001,redis://172.16.163.61:7002,redis://172.16.163.61:7003,redis://172.16.163.62:7004,redis://172.16.163.62:7005".split(",");
        config.useClusterServers()
              // 集群状态扫描间隔时间，单位是毫秒
              .setScanInterval(2000)
              .addNodeAddress(ips);
        redissonClient= Redisson.create(config);
    }



        public static void main(String[] args) throws InterruptedException {
        RLock rlock=redissonClient.getLock("wrx_lock");
        //最多等待100s，上锁10s后自动解锁
        if(rlock.tryLock(100,60, TimeUnit.SECONDS)){
            System.out.println("获取锁成功");
        }

        rlock.unlock();

        redissonClient.shutdown();
    }
}
