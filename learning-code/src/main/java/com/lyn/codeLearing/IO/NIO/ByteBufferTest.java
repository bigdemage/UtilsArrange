package com.lyn.codeLearing.IO.NIO;

import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @ClassName ByteBufferTest
 * @Deacription
 * @Author wrx
 * @Date 2022/6/22/022 10:56
 * @Version 1.0
 **/
@Slf4j
public class ByteBufferTest {

    public static void main(String[] args) {
        String str="你就是歌姬吧";
        /**
         * 初始化ByteBuffer缓冲区，设置大小
         * Buffer三个初始化属性，position位置，limit提交/限制，capacity容量
         * 默认是0，size，size
         * 非直接缓冲区allocate  物理磁盘--->物理内存--->jvm空间--->应用程序
         * 直接缓冲区allocateDirect使用堆外内存  物理磁盘--->物理内存映射文件--->应用程序
         */
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        //String转byte数组
        byte[] bytes=str.getBytes();
        /**
         * 将byte数组放入缓冲区
         * put多少数据，position就增加多少
         */
        byteBuffer.put(bytes);
        /**
         * 切换读模式，byteBuffer有读和写两种模式，操作只能用一种模式，所以需要切换
         * 调用flip()后，position置0，limit置成之前postion的长度
         * 怎么从读模式切换成写模式，用clear()清空和compact()压缩方法将缓冲区转换成写模式
         */
        byteBuffer.flip();
        //创建临时数组
        byte[] resultByte =new byte[bytes.length];
        int i=0;
        //只要有数据就继续循环
        //当position==limit时表示数据读取完，跳出循环
        while(byteBuffer.hasRemaining()){
            byte b=byteBuffer.get();
            log.info("b:{},position:{},limit:{},capacity:{}",b,byteBuffer.position(),byteBuffer.limit(),byteBuffer.capacity());
            //放到数据组中
            resultByte[i]=b;
            i++;
        }
        log.info("最终结果:{}",new String(resultByte, CharsetUtil.UTF_8));
    }
}
