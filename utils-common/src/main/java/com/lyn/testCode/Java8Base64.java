package com.lyn.testCode;



import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

/**
 * java8的base64加密解密
 */
public class Java8Base64 {

    private List< String>  getList(){
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {


        


        //获取encoder对象
        Base64.Encoder encoder =Base64.getEncoder();

        String password ="alibaba";

        String encoderStr=encoder.encodeToString(password.getBytes(StandardCharsets.UTF_8));

        System.out.println(encoderStr);

        //以上是加密
        //以下是解密

        Base64.Decoder decoder =Base64.getDecoder();

        byte[] decoderByte =decoder.decode(encoderStr);

        System.out.println(new String(decoderByte,StandardCharsets.UTF_8));


    }
}
