package com.main.controller;


import com.main.aspect.ExecuteTime;
import com.main.aspect.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @ExecuteTime()
    public Object sing(@RequestParam String song){
        return "sing the song :"+song;
    }

}
