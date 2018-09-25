package com.main.controller;


import com.alibaba.fastjson.JSONObject;
import com.main.aspect.Log;
import com.main.aspect.MethodLog;
import com.main.entity.response.Song;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class HelloController {




    @RequestMapping("/hello")
    @Log(description = "hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/eat")
    @Log(description = "eat")
    public String eat(){
        return "eat";
    }

    @RequestMapping("/sing")
    @MethodLog(description = "歌曲controller")
    public Object sing(@RequestParam String song,String name){

        return JSONObject.toJSONString(getSong(song,name,5));
    }


    @MethodLog(description = "歌曲getSong")
    public Song getSong(String name, String userName ,int songTime){
        return new Song(name,userName,songTime);
    }

    public static void main(String[] args) {
        BigDecimal b =new BigDecimal(2.2);
        System.out.println(b.toString());
        System.out.println(String.valueOf(b.doubleValue()));
    }





}
