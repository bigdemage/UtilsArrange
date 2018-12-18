package com.lyn.codeLearing.mode.strategyMode;

/**
 * 策略模式
 * 抽象策略角色，给出接口
 */
public interface VipBookPrice {

    /**
     * 传入当前书籍的价格，算出书的最终价格
     * @param bookPrice
     * @return
     */
    public double finalPrice(double bookPrice);

}
