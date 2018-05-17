package com.lyn.codeLearing.strategyMode;


/**
 * vip级别的代理
    环境角色，持有一个stagegy的引用
 */
public class VipProxy {


   private VipBookPrice vipBookPrice;

   public VipProxy(VipBookPrice vipBookPrice){
       this.vipBookPrice=vipBookPrice;
   }

    /**
     * 计算书籍最终价格
     * @param bookPrice
     * @return
     */
   public double finalPrice(double bookPrice){
       return this.vipBookPrice.finalPrice(bookPrice);
   }


}
