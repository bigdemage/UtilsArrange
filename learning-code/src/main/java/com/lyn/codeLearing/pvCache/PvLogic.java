package com.lyn.codeLearing.pvCache;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.Constants;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName PvLogic
 * @Deacription pv计算存储逻辑
 * @Author wrx
 * @Date 2022/4/22 16:55
 * @Version 1.0
 **/
public class PvLogic {

    public static final Map<Long, Map<Integer,Integer>> PV_MAP=new ConcurrentHashMap<>();

    /**
     * pv请求调用
     * @param id
     */
    public void addPV(Integer id){
        Long now=System.currentTimeMillis();
        //距离1970年00:00:00第多少个时间块,用小时做key，也可用分钟，天等做key，具体情况具体计算
        long timeblock=now/(1000*60*60);
        Map<Integer,Integer> pvInfo= PV_MAP.get(timeblock);
        if(!pvInfo.isEmpty()){
            Integer pv=pvInfo.get(id);
            if(pv==null){
                pvInfo.put(id,1);
            }else{
                pvInfo.put(id,pv+1);
            }
        }else {
            pvInfo=new ConcurrentHashMap<>();
            pvInfo.put(id,1);
            PV_MAP.put(timeblock,pvInfo);
        }
    }

    /**
     * 定时取出jvm中的pvMap推送到redis中
     */
    public void pvConsumer(){
        Long nowBlock= System.currentTimeMillis()/(1000*60*60);
        Iterator<Long> iterator=PV_MAP.keySet().iterator();
        while(iterator.hasNext()){
            Long key=iterator.next();
            //把小于当前时间块的数据推入redis队列
            if(key<nowBlock){
                Map<Integer,Integer> blockPv=PV_MAP.get(key);
                //TODO 用lpush把数据推入redis队列
                //push后删除，不然一直重复推
                PV_MAP.remove(key);
            }
        }
    }



}
