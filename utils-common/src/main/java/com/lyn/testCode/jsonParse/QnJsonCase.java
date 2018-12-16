package com.lyn.testCode.jsonParse;

import basic.arch.tools.utils.jsonxml.JsonUtils;
import com.alibaba.fastjson.JSON;
import com.lyn.testCode.jsonParse.vo.QingNiuJsonItems;
import com.lyn.testCode.jsonParse.vo.QingNiuResult;

import java.util.HashMap;
import java.util.Map;

public class QnJsonCase {

    public static void main(String[] args) {

        String str="{\"state\":{\"rc\":0,\"msg\":\"获取成功\"},\"result\":{\"items\":[{\"name\":\"聊聊保险那些事\",\"cid\":3180},{\"name\":\"聊聊保险那些事2\",\"cid\":3298}]}}";



//        QingNiuResult qingNiu= JSON.parseObject(str, QingNiuResult.class);

        QingNiuResult result =json2Bean(str, QingNiuJsonItems.class,null);

        System.out.println(result);
    }

    private static <T> QingNiuResult<T> json2Bean(String json, Class<T> clazz,Map<String, Class<?>> classMap){
        if(classMap==null){
            classMap = new HashMap<String, Class<?>>();
        }
        classMap.put("items", clazz);
        return JsonUtils.json2Obj(json, QingNiuResult.class, classMap);
    }
}
