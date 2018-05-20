package com.lyn.codeLearing.IO.Stream.input.ownInputStream;


import java.io.IOException;
import java.io.InputStream;

/**
 * 手写inputStream
 * v2
 */
public class MyOwnStream2 extends InputStream {
    //传入数组
    protected byte[] data;
    //当前位置
    protected int ptr = 0;
    //标签位置
    private int mark = 0;

    public MyOwnStream2(byte[] b) {
        this.data = b;
    }

    /**
     * 读取
     *
     * @return
     */
    public int read() {
        return (ptr < data.length ? data[ptr++] : -1);
    }

    /**
     * 剩余长度
     *
     * @return
     */
    public int available() {
        return data.length - ptr;
    }

    /**
     * 关闭
     * 模拟关闭，返回-1读不了了相当于关闭
     */
    public void close() {
        ptr = data.length;
    }

    /**
     * 标签
     *
     * @param readlimit
     */
    public synchronized void mark(int readlimit) {
        this.mark = readlimit;
    }

    /**
     * 重新载入到标签处
     */
    public synchronized void reset() {
        if (mark < 0 || mark >= data.length) {
            System.out.println("标签点不存在");
        }

        ptr = mark;
    }

    public boolean markSupported() {
        return true;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.ptr >= data.length || len < 0) {
            return -1;
        }

        if ((this.ptr + len) > data.length) {
            len=data.length-this.ptr;
        }
        if(len==0){
            return 0;
        }
        System.arraycopy(data,ptr,b,off,len);

        ptr+=len;

        return len;
    }
}
