package com.lyn.testCode;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ReadInText
 * @Deacription 读取文本文件
 * @Author wrx
 * @Date 2022/6/20/020 15:05
 * @Version 1.0
 **/
@Slf4j
public class ReadInText {

    private static final String ENCODING = "utf-8"; // 字符编码

    public static void main(String[] args) throws IOException {
//        String filePath = "D:\\IdeaProjects\\UtilsArrange\\learning-code\\target\\classes\\SensitiveWord.txt";
//        Set set = readTxt(filePath);
    }

    /**
     * 获取项目路径
     * @return
     */
    public static String getProjectPath(){
        return ReadInText.class.getResource("/").getPath();
    }

    public static Set<String> readTxt(String filePath) throws IOException {
        Set<String> set = new HashSet<String>();
        String fileFullPath=ReadInText.class.getResource("/").getPath()+filePath;
        File file = new File(fileFullPath);
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader read = null;
        try {
            // 文件流是否存在
            if (!file.exists() || !file.isFile()) {
                log.error("文件不存在");
                return null;
            }

            inputStream = new FileInputStream(file);
            read = new InputStreamReader(inputStream, ENCODING);
            bufferedReader = new BufferedReader(read);

            String txt = null;
            while ((txt = bufferedReader.readLine()) != null) { // 读取文件，将文件内容放入到set中
                set.add(txt);
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if(read!=null){
                read.close();
            }
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return set;
    }
}
