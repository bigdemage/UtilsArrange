package com.lyn.codeLearing.thread;

public class ThreadTest2 {

    public static void main(String[] args) {
        Runnable go=new HelloThread();



        Thread r1 =new Thread(go);
        Thread r2 =new Thread(go);
        r1.start();
        r2.start();

//        String str="";
//        System.out.println(StringUtils.replace(str,"%",""));

    }
}

class HelloThread implements Runnable{
    int i;
    @Override
    public void run() {
//        int i=0;
        while(true){
            try {
                System.out.println(i++);
                Thread.sleep((long)Math.random()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(50==i){
                break;
            }
        }
    }
}
