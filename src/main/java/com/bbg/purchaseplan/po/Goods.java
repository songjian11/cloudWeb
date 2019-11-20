package com.bbg.purchaseplan.po;

import lombok.Data;

/**
 * 商品信息-对应表p_purchase_goods
 */
@Data
public class Goods {
    private Long id;
    // 订单编号
    private String orderCode;
    // 商品编号
    private String goodsCode;
    // 商品名称
    private String goodsName;
    // 单价(单位:元)
    private String price;
    // 商品数量
    private String goodsNum;
    // 大类编号
    private String dept;
    // 进项税率
    private String vatInRate;
    // 标准度量单位
    private String standardUom;
    // 主条码
    private String barcode;
    // 预留字段
    private String mark1;
    // 预留字段
    private String mark2;
    // 预留字段
    private String mark3;
}
