package com.cleverDY.mapper;

import com.cleverDY.pojo.Order;

import java.util.List;

public interface OrderMapper {
    //查看所有
    List<Order> selectAll();
    //查看详情
    Order selectById(int id);
    Order selectByGoodName(String goodName);
    //动态条件查询
    //时间降序
    List<Order> selectAllDateDESC();
    //时间升序
    List<Order>selectAllDateASC();
    //id降序
    List<Order> selectAllIdDESC();
    //改
    //动态修改
    void updateDynamic(Order order);
    //增
    void insertOrder(Order orders);
    //删
    //删除指定
    void deleteById(int id);
    //批量删除
    int batchDelete(List<Integer> list);
    List<Order> selectAllPriceASC();
    List<Order> selectAllPriceDESC();
    List<Order> selectList(String goodsName);
}
