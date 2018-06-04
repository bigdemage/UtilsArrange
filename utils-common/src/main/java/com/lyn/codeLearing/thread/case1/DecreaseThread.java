package com.lyn.codeLearing.thread.case1;

public class DecreaseThread extends Thread {


    private Sample sample;

    public DecreaseThread(Sample sample){
        this.sample=sample;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){
            try {
                Thread.sleep((long)Math.random()*1000);
                sample.decrease();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

