package com.bbg.purchaseplan.dao;

import com.bbg.purchaseplan.po.Goods;

import java.util.List;

public interface GoodsDao {
    void saveBatchGoods(List<Goods> list);
}
