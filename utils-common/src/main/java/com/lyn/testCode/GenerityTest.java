package com.lyn.testCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerityTest {


    public static void main(String[] args) {

        Map map =get();
        System.out.println(map.get("list") instanceof List);
        System.out.println(map.get("list") instanceof String);
        System.out.println(map.get("name") instanceof String);


    }


    public static Map<String,Object> get(){
        BB[] bbs ={new BB("1"),new BB("2")};
        List<BB> list = Arrays.asList(bbs);

        Map map =new HashMap();
        map.put("name","1");
        map.put("list",list);
        return map;
    }
}

class BB{
    private String name;

    public BB(String name) {
        this.name = name;
    }

    public BB() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
