package com.lyn.codeLearing.mode.strategyMode;

public class BuyBookMain {

    public static void main(String[] args) {
        VipBookPrice v2 = new VipV2();
        double bookPrice = 150;
        VipProxy vipProxy = new VipProxy(v2);
        System.out.println(vipProxy.finalPrice(bookPrice));
    }
}
