package com.lyn.codeLearing.IO.NIO;


import java.io.FileInputStream;

/**
 * try with resource语法
 */
public class TryWithResource {
    public static void main(String[] args) {
        try(FileInputStream in =new FileInputStream("aaa.txt")){

        }catch (Exception e){

        }
    }
}
