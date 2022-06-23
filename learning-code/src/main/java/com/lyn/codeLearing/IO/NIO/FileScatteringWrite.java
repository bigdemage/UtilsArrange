package com.lyn.codeLearing.IO.NIO;

import com.lyn.testCode.ReadInText;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @ClassName FileScatteringWrite
 * @Deacription 分散读取文件->聚合写入
 * @Author wrx
 * @Date 2022/6/22/022 16:53
 * @Version 1.0
 **/
@Slf4j
public class FileScatteringWrite {

    /**
     * FileChannel继承了GatheringByteChannel(聚合), ScatteringByteChannel(分散)
     * 使用一个缓存区数组，自动根据需求去分配缓冲区，减少内存消耗
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String filePath= ReadInText.getProjectPath()+"guigui.txt";
        //输入流
        FileInputStream fileInputStream=new FileInputStream(new File(filePath));
        //输出流
        FileOutputStream fileOutputStream=new FileOutputStream("dingding.txt");
        //输入输出的管道
        FileChannel inChannel=fileInputStream.getChannel();
        FileChannel outChannel=fileOutputStream.getChannel();
        //创建多个缓冲区和缓冲区数组
        ByteBuffer byteBuffer1=ByteBuffer.allocate(5);
        ByteBuffer byteBuffer2=ByteBuffer.allocate(5);
        ByteBuffer byteBuffer3=ByteBuffer.allocate(5);
        ByteBuffer[] byteBuffers={byteBuffer1,byteBuffer2,byteBuffer3};
        //循环写入缓冲区数组中，分散读取
        long read;
        long sumlength=0;
        while((read=inChannel.read(byteBuffers))!=-1){
            log.info("------------------进入循环------------------");
            //读取到的
            sumlength+=read;
            //日志输出
            Arrays.stream(byteBuffers)
                  .map(buffer -> "posstion=" + buffer.position() + ",limit=" + buffer.limit())
                  .forEach(System.out::println);
            //批量切换模式
            Arrays.stream(byteBuffers).forEach(ByteBuffer::flip);
            //聚合写入outchannel
            outChannel.write(byteBuffers);
            //清空缓存区
            Arrays.stream(byteBuffers).forEach(ByteBuffer::clear);
        }
        log.info("总长度:{}",sumlength);
        //关闭通道
        fileInputStream.close();
        fileOutputStream.close();
        inChannel.close();
        outChannel.close();

    }
}
