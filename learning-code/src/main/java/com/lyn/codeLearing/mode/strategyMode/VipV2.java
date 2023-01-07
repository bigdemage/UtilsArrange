package com.lyn.codeLearing.mode.strategyMode;


/**
 * v2级别会员
 * 10%的折扣
 */
public class VipV2 implements VipBookPrice {
    @Override
    public double finalPrice(double bookPrice) {
        return bookPrice*0.9;
    }
}
