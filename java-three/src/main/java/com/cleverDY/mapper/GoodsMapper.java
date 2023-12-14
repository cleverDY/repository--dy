package com.cleverDY.mapper;

import com.cleverDY.pojo.Goods;

import java.util.List;

public interface GoodsMapper {
    //查看所有
    List<Goods> selectAll();
    //查看详情
    Goods selectByName(String goodName);
    Goods selectById(int goodsId);
    //动态条件查询
    List<Goods> selectAllPriceASC();
    //价格降序
    List<Goods> selectAllPriceDESC();

    List<Goods> selectAllIdASC();

    void insertGood(Goods goods);

    void deleteById(int id);

    int batchDelete(List<Integer> list);

    void updateDynamic(Goods goods);

    List<Goods> selectList(String goodsName);
}
