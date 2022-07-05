package com.lyn.codeLearing.IO.File;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;

public class FileTest {

    public static void main(String[] args) {

        File file = new File("D:\\Downloads");
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
            System.out.println(StringUtils.substringBefore(name, "."));
        }


    }

}
