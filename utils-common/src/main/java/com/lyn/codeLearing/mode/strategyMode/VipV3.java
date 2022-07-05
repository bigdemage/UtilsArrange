package com.lyn.codeLearing.mode.strategyMode;


/**
 * v3级别会员
 * 20%折扣
 */
public class VipV3 implements VipBookPrice {
    @Override
    public double finalPrice(double bookPrice) {
        return bookPrice*0.8;
    }
}
