package com.bbg.purchaseplan.dto;


import java.util.List;

/**
 * 供应商+商品信息
 * songjian
 * 20191109
 */
public class SupplierGoodsListFeignDto extends SupplierFeignDto {
    private List<SupplierGoodsFeignDto> itemList; // 商品列表

    public List<SupplierGoodsFeignDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<SupplierGoodsFeignDto> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "SupplierGoodsListFeignDto{" +
                "itemList=" + itemList +
                '}';
    }
}
