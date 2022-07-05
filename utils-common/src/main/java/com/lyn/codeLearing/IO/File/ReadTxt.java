package com.lyn.codeLearing.IO.File;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadTxt {

    public static List<String> readTxt(String filePath){
        List<String> files=new ArrayList<>();
        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {//数据以逗号分隔
                files.add(lineTxt);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();



        System.out.println(map);

//        /* 输出数据 */
//        try {
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/value_map.txt")),
//                    "UTF-8"));
//
//            for (String name : map.keySet()) {
//                bw.write(name + " " + map.get(name));
//                bw.newLine();
//            }
//            bw.close();
//        } catch (Exception e) {
//            System.err.println("write errors :" + e);
//        }
    }
}
