package com.lyn.codeLearing.IO.NIO;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName FileChannelTest
 * @Deacription Channel类型之一，FileChannel
 *              读写文件中的数据
 * @Author wrx
 * @Date 2022/6/22/022 14:01
 * @Version 1.0
 **/
@Slf4j
public class FileChannelTest {

    private static final String FILE_NAME="SensitiveWord.txt";

    private static final int[] types={0,1,2,3};

    /**
     * FileChannel流程，fileinputStream->fileChannel->byteBuffer->fileoutchannel->fileoutputSteam
     * 流是没有办法在channel内直接传输，需要拿到流里的管道，数据放到缓存区中，由管道输送
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String filePath=FileChannelTest.class.getResource("/").getPath()+FILE_NAME;
        File file=new File(filePath);
        if(file.exists()){
            log.info("开始操作文件");
            //读入流
            FileInputStream fileInputStream=new FileInputStream(file);
            //输入流的管道
            FileChannel fileInChannel=fileInputStream.getChannel();
            //输出流
            FileOutputStream fileOutputStream=new FileOutputStream(new File("dangdang.txt"));
            //输出流的管道
            FileChannel fileOutChannel=fileOutputStream.getChannel();
            //创建缓冲区，channel只是负责搬运，搬运的东西放在缓冲区中
            ByteBuffer byteBuffer =ByteBuffer.allocate((int) file.length());
            //操作的类型
            writeTxt(types[1],fileInChannel,fileOutChannel,byteBuffer);
            //优雅关闭
            graceClose(fileInChannel,fileInputStream,fileOutChannel,fileOutputStream);
            log.info("文件操作完成");
        }


    }

    private static void graceClose(FileChannel fileInChannel, FileInputStream fileInputStream, FileChannel fileOutChannel, FileOutputStream fileOutputStream) throws IOException {
        //关闭通道
        fileInChannel.close();
        fileInputStream.close();
        fileOutChannel.close();
        fileOutputStream.close();
    }

    /**
     * 读写文件的三种方式
     * 1、读到inchannel中的缓冲区，写入到outchannel中的缓冲区
     * 2、transferTo()，把源通道数据传到目标通道中
     * 3、transferFrom()，目标通道去读取源通道的数据
     * @param type
     */
    private static void writeTxt(int type,FileChannel fileInChannel,FileChannel fileOutChannel,ByteBuffer byteBuffer) throws IOException {
        if(type==1){
            //把输入流数据读进管道中的缓冲区
            fileInChannel.read(byteBuffer);
            //byteBuffer切换读取模式
            byteBuffer.flip();
            //输出流的管道放入缓冲区数据
            fileOutChannel.write(byteBuffer);
        }else if(type==2){
            fileInChannel.transferTo(0,byteBuffer.limit(),fileOutChannel);
        }else if(type==3){
            fileOutChannel.transferFrom(fileInChannel,0,byteBuffer.limit());
        }else{
            log.error("无次类型");
        }

    }
}
