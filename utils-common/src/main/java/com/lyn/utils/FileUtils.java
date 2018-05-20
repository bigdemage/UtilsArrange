package com.lyn.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.TreeMap;

public class FileUtils {

    public static void main(String[] args) throws Exception {
        alterShengSiYuanFileName("D:\\BaiduNetdiskDownload\\北京圣思园\\javaSE");

    }


    /**
     * 遍历圣思源文件
     * 创建JAVA.SE课程的目录
     *
     * @param folder 文件夹路径
     */
    private static void alterShengSiYuanFileName(String folder) throws Exception {
        File file = new File(folder);
        File[] fa = file.listFiles();
        TreeMap map = new TreeMap();
        for (File fl : fa) {
            //文件名称
            String fullFileName = fl.getCanonicalPath();
            System.out.println(fullFileName);
            System.out.println(StringUtils.substringBetween(fullFileName, "]", "("));
            String newName = StringUtils.substringBetween(fullFileName, "]", "[");
            if (StringUtils.isNotBlank(newName)) {
                alterFileName(fullFileName, newName);
            }
//            String subFileName = StringUtils.substringBetween(fullFileName, "第", "讲");
//            if(StringUtils.isNotBlank(subFileName)){
//                try{
//                    int index=Integer.parseInt(subFileName);
//                    map.put(index,fullFileName);
//                }catch (Exception e){
//                    map.put(NumberUtil.chineseNumber2Int(subFileName),fullFileName);
//                }
//            }
        }
    }


    /**
     * 修改文件名
     *
     * @param oldName 旧文件全路径 d:/lyn/www.jpg
     * @param newName 新文件名称 lyn.jpg
     */
    private static void alterFileName(String oldName, String newName) throws Exception {
        File oldFile = new File(oldName);
        if (oldFile.exists()) {
            String fileName = oldFile.getCanonicalPath();
            //文件路径前缀：d:/lyn
            String preFileName = fileName.substring(0, fileName.lastIndexOf("\\") + 1);
            //文件后缀.jpg
            String sufFileName = fileName.substring(fileName.lastIndexOf("."));
            //改名
            oldFile.renameTo(new File(preFileName + newName + sufFileName));
        } else {

            throw new Exception("文件不存在");
        }
    }

    /**
     * 删除此目录的所有文件
     * 因为delete方法只能删除一个文件或者是空目录
     * 用递归方式删除
     *
     * @param file
     */
    public static void deleteAllFiles(File file) {
        if ( file.isFile() || file.list().length==0 ) {
            file.delete();
        } else {
            File[] files = file.listFiles();

            for (File newFile : files) {
                deleteAllFiles(newFile);

                newFile.delete();
            }
        }
        file.delete();
    }


}
