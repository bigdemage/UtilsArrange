package com.lyn.codeLearing.modelLearning.singleton;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName CasSingleton
 * @Deacription 自旋形式不推荐使用，会对cpu造成巨大负荷
 * @Author wrx
 * @Date 2022/3/15/015 11:09
 * @Version 1.0
 **/
public class CasSingleton {

    private static final AtomicReference<CasSingleton> INSTANCE=new AtomicReference<>();

    public static CasSingleton getInstance(){
        //死循环，跟wihie(true)的区别https://blog.csdn.net/weixin_44395686/article/details/103409425
        for(;;){
            CasSingleton obj =INSTANCE.get();

            if(obj==null){
                obj=new CasSingleton();
                if(INSTANCE.compareAndSet(null,obj)){
                    return obj;
                }
            }else{
                return obj;
            }

        }
    }

}
