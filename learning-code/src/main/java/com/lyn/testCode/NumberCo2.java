package com.lyn.testCode;

/**
 * @ClassName: SimpleLetterAndNumCheck.java
 * @Description: JAVA实现连续字母或者数字：<br/>
   实现思路：统一转成ASCII进行计数判断，纯数字、纯字母<br/>
   纯数字(数字0 -- 数字9,对应ASCII为48 -- 57)<br/>
   大写纯字母(大写字母A -- 大写字母Z,对应ASCII为65 -- 90)<br/>
   小写纯字母(小写字母a -- 小写字母z，对应ASCII为97 -- 122)<br/>
 */
public class NumberCo2 {

    /**
     * SM 校验字符串连续多少位是纯数字或者纯字母，默认6位(字母区分大小写)
     * @param password 密码
     * @return
     */
    public static boolean simpleLetterAndNumCheck(String value){
        //
        return NumberCo2.simpleLetterAndNumCheck(value, 6);
    }
    /**
     * SM 校验字符串连续多少位是纯数字或者纯字母，默认6位(字母区分大小写)
     * @param password 密码
     * @param length    校验长度,默认6为
     * @return
     */
    public static boolean simpleLetterAndNumCheck(String value, int length){
        //是否不合法
        boolean isValidate = false;
        //
        int i = 0;
        //计数器
        int counter = 1;
        //
        for(; i < value.length() -1;) {
            //当前ascii值
            int currentAscii = Integer.valueOf(value.charAt(i));
            //下一个ascii值
            int nextAscii = Integer.valueOf(value.charAt(i + 1));
            //满足区间进行判断
            if( (NumberCo2.rangeInDefined(currentAscii, 48, 57) || NumberCo2.rangeInDefined(currentAscii, 65, 90) || NumberCo2.rangeInDefined(currentAscii, 97, 122))
                    && (NumberCo2.rangeInDefined(nextAscii, 48, 57) || NumberCo2.rangeInDefined(nextAscii, 65, 90) || NumberCo2.rangeInDefined(nextAscii, 97, 122)) ) {
                //计算两数之间差一位则为连续
                if(Math.abs((nextAscii - currentAscii)) == 1){
                    //计数器++
                    counter++;
                }else{
                    //否则计数器重新计数
                    counter = 1;
                }
            }
            //满足连续数字或者字母
            if(counter >= length) return !isValidate;
            //
            i++;
        }

        //
        return isValidate;
    }

    /**
     * SM 判断一个数字是否在某个区间
     * @param current 当前比对值
     * @param min   最小范围值
     * @param max   最大范围值
     * @return
     */
    public static boolean rangeInDefined(int current, int min, int max) {
        //
        return Math.max(min, current) == Math.min(current, max);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //
        //String str = "1234677A!@#B0abcdeg123456DDzZ09";
        //连续不合法区间值校验
        String str = "123457";
        //
        boolean validate = NumberCo2.simpleLetterAndNumCheck(str);
        //
        if(validate){
            System.out.println("连续字母或者数字");
        }else {
            System.out.println("合法的校验");
        }
    }
}