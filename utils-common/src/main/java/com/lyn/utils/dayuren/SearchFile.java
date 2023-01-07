package com.lyn.utils.dayuren;

import com.lyn.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 查询文件
 */
public class SearchFile {
    public static void main(String[] args) throws Exception {

        searchFile("F:\\十方俱灭\\大鱼人");
    }

    public static void replaceFileName(File f,String newName){
        String c=f.getParent();
        File mm=new File(newName);
        if(f.renameTo(mm))
        {
            System.out.println("修改成功!");
        }
        else
        {
            System.out.println("修改失败");
        }
    }

    public static void searchFile(String path){
        File file = new File(path);		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        for(File f:fs){					//遍历File[]数组
            if(!f.isDirectory())		//若非目录(即文件)，则打印
                if(f.getName().indexOf("--")<0){
                    String fileName=f.getName();
                    System.out.println(fileName);
                    replaceFileName(f,"F:/十方俱灭/"+fileName);
                }
        }
    }


}
