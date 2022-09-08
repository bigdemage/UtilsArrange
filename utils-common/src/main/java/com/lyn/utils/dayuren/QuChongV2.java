package com.lyn.utils.dayuren;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指定文件夹里的文件去重
 */
public class QuChongV2 {

    public static void main(String[] args) {

        List<String> gongjiFile=getFiles("G:\\十方俱灭\\大鱼人");
        myselfquchong(gongjiFile);
        
    }

    /**
     * 当前文件去重
     * @param gongjiFile
     */
    private static void myselfquchong(List<String> gongjiFile) {
        Map<String,Boolean> map =new HashMap<>();
        gongjiFile.stream().forEach(file->{
            Boolean flag=map.get(file);
            if(flag==null){
                map.put(file,false);
            }else{
                map.put(file,true);
            }
        });
        for(Map.Entry<String, Boolean> entry : map.entrySet()){
                 String mapKey = entry.getKey();
                 Boolean mapValue = entry.getValue();
                 if(mapValue==true){
                     System.out.println(mapKey);
                 }
             }
    }


    static List<String> getFiles(String fileUrl){
        List<String> files=new ArrayList<>();
        File file = new File(fileUrl);
        /**
         * 只返回后缀为pdf的文件名称
         */
        String[] names = file.list(new FilenameFilter() {
            /**
             * @param dir 当前这个file的目录
             * @param name 文件名称
             * @return
             */
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        });

        for (String name : names
        ) {
            if(name.indexOf(".")<0) continue;
            name=StringUtils.substringBefore(name, ".");
            if(name.toUpperCase().indexOf("CH--")>0){
                name= StringUtils.substring(name,0,name.toUpperCase().indexOf("CH--"));
            } else if(name.toUpperCase().indexOf("C--")>0){
                name= StringUtils.substring(name,0,name.toUpperCase().indexOf("C--"));
            }else if(name.toUpperCase().indexOf("--")>0){
                name=StringUtils.substring(name,0,name.indexOf("--"));
            }
            files.add(name.toUpperCase());
        }
        return files;
    }
}
