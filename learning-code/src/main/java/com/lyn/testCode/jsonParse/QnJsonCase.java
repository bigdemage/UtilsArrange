package com.lyn.testCode.jsonParse;

import com.alibaba.fastjson.JSON;
import com.lyn.testCode.jsonParse.vo.QingNiuJsonItems;
import com.lyn.testCode.jsonParse.vo.QingNiuResult;

import java.util.HashMap;
import java.util.Map;

public class QnJsonCase {

    public static void main(String[] args) {

        String str="{\"state\":{\"rc\":0,\"msg\":\"获取成功\"},\"result\":{\"items\":[{\"name\":\"聊聊保险那些事\",\"cid\":3180},{\"name\":\"聊聊保险那些事2\",\"cid\":3298}]}}";



        QingNiuResult qingNiu= JSON.parseObject(str, QingNiuResult.class);

        double i=0.8;
        if(i>1) System.out.println("大于1");i=1.0;

        System.out.println(i);


    }


}
