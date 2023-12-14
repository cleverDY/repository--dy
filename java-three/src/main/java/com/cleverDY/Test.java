package com.cleverDY;

import com.cleverDY.good_and_order.Management;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException,NullPointerException {
        Management management =new Management();
        //int [] a={1,2,3,4,5,6,7,8,9};
        //.deleteGoodsInBatches(a);
        //management.AddGoods(1,"A",5,100);
        //management.AddGoods(2,"B",6,200);
        //management.AddGoods(3,"C",7,300);
        //management.AddGoods(4,"D",8,400);
        //management.AddGoods(6,"f",15,500);
        //management.deleteGoodById(7);
        //management.SortGoodsIdASC();
        //.SortGoodsPriceASC();
        //management.updateGoodsById(1,"A",5,6000);
        //management.AddOrder(2,"A",100);
        //management.AddOrder(5,"A",100);
        //management.AddOrder(4,"A",100);
        //management.AddOrder(29,"A",100);
        //management.OrderUpdate(29,"A",300);
        management.getOrderByName("A");

    }
}