package com.lyn.codeLearing.mode.strategyMode;


/**
 * v1级别会员
 * 没有折扣
 */
public class VipV1 implements VipBookPrice {
    @Override
    public double finalPrice(double bookPrice) {
        return bookPrice;
    }
}
