package com.lyn.codeLearing;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;


/**
 * 单例ObjectMapper
 * 下图证明单例比多例快
 * https://blog.csdn.net/luckyman98/article/details/104082170?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.channel_param
 */
@Data
public class SingleObjMapper {

    private static class InstanceHolder{
        private static  final ObjectMapper instance=new ObjectMapper();
    }

    public static ObjectMapper getInstatnce(){
        ObjectMapper objectMapper = InstanceHolder.instance;
        //排除json字符串中实体类没有的字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}

