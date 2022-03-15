package com.lyn.testCode;



import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

/**
 * java8的base64加密解密
 */
public class Java8Base64 {


    public static void main(String[] args) throws UnsupportedEncodingException {


//        jiami(str);

        String jiemihou =jiemi("rBCjQmA9l8yAJKCHAAEd2WT");

        System.out.println(jiemihou);

    }

    private static String jiemi(String str) {

        return new String(Base64.getDecoder().decode(str),StandardCharsets.UTF_8);

    }

    private static String jiami(String str) {
        //获取encoder对象
        Base64.Encoder encoder =Base64.getEncoder();


        String encoderStr=encoder.encodeToString(str.getBytes(StandardCharsets.UTF_8));

        return encoderStr;
    }
}
