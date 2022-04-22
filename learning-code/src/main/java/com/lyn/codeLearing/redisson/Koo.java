package com.lyn.codeLearing.redisson;

import com.lyn.codeLearing.SingleObjMapper;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName Koo
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/4/2/002 15:02
 * @Version 1.0
 **/
public class Koo {

    public static void main(String[] args) throws IOException {
        String str="{\"time\":\"1648881068000\"}";

        P p = SingleObjMapper.getInstatnce().readValue(str,P.class);

        System.out.println(p);
    }

}


class P {
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "P{" +
                "time=" + time +
                '}';
    }
}
