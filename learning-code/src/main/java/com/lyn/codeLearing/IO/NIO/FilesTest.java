package com.lyn.codeLearing.IO.NIO;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
public class FilesTest {

    public static void main(String[] args) throws IOException {

        Path path=Paths.get("D:\\lyn\\game\\fc\\save\\Whore Of Wall Street\\kks-whofwast.rar");
        //文件是否存在
        log.info("文件是否存在:{}", Files.exists(path));

        //创建--如果存在则异常
        Path txt=Paths.get("aa.txt");
//        Files.createFile(txt);


        //拷贝--存在则异常，要使用覆盖，则用StandardCopyOption
        Path txt2=Paths.get("bb.txt");
        Path txt3=Paths.get("cc.txt");

        Files.copy(txt2,txt3, StandardCopyOption.REPLACE_EXISTING);

        //移动文件--使用standard保证原子性
        Path rmTxt=Paths.get("D:/ooo.txt");
        Files.move(txt2,rmTxt,StandardCopyOption.ATOMIC_MOVE);

        //删除文件--文件不存在抛出异常；目录中有内容抛出异常
//        Files.delete();




    }
}
