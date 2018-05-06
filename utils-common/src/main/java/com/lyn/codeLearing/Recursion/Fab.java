package com.lyn.codeLearing.Recursion;

import com.lyn.utils.FileUtils;

import java.io.File;

/**
 * 斐波那契数列
 * 1 1 2 3 5 8 .....
 */
public class Fab {

    /**
     * n代表第几个数，10就是第十个数
     * @param n
     * @return
     */
    public static int compute(int n){
        //递归出口
        if(1==n || 2==n){
            return 1;
        }
        else{
            return compute(n-1) +compute(n-2);
        }


    }

    public static void main(String[] args) {
//        System.out.println(compute(5));

        File file =new File("d:\\dd");
        FileUtils.deleteAllFiles(file);
    }
}
