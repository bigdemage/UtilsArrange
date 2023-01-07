package com.lyn.utils.dayuren;

import com.lyn.codeLearing.IO.File.ReadTxt;
import com.lyn.utils.Levenshtein;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件去重
 */
public class QuChong {

    public static void main(String[] args) {
        List<String> downFile= getFiles("G:\\十方俱灭\\归档");

        List<String> gongjiFile=getFiles("G:\\十方俱灭\\大鱼人");
        //比较两个文件夹
        compareFileList(downFile,gongjiFile);

    }



    /**
     * 使用第三方api比较
     * @param downFile
     * @param gongjiFile
     */
    private static void compareFileList(List<String> downFile, List<String> gongjiFile) {
        downFile.forEach(downName->{
            for (String gongji:gongjiFile) {
                float f=Levenshtein.getSimilarityRatio(downName,gongji);
                if(f>0.8){
                    System.out.println(downName+"    "+gongji);
                }
            }
        });
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
            if(name.indexOf("--")>0) name= StringUtils.substring(name,0,name.indexOf("--"));
            files.add(name.toUpperCase());
        }

        return files;
    }
}
