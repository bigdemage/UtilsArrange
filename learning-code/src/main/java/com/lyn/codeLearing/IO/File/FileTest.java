package com.lyn.codeLearing.IO.File;

import java.io.File;
import java.io.FilenameFilter;

public class FileTest {

    public static void main(String[] args) {

        File file =new File("D:\\视频学习\\圣思源小\\Java SE 第八十七讲 File类详解及使用陷阱深度剖析");
        /**
         * 只返回后缀为pdf的文件名称
         */
        String[] names=file.list(new FilenameFilter() {
            /**
             * @param dir 当前这个file的目录
             * @param name 文件名称
             * @return
             */
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".pdf")){
                    return true;
                }
                return false;
            }
        });

        for (String name:names
             ) {
            System.out.println(name);
        }


    }

}
