package com.bbg.purchaseplan.po;

import lombok.Data;

/**
 * 商品订单信息
 */
@Data
public class GoodsOrderInfo {
    //主键
    private Long id;
    //订单编号
    private String orderCode;
    //仓库编号
    private String depotCode;
    //仓库名称
    private String depotName;
    //订货人工号
    private String orderPersonId;
    //订货人名称
    private String orderPersonName;
    //供应商编号
    private String suppCode;
    //供应商名称
    private String suppName;
    //要货时间(yyyyMMdd)
    private String purchaseTime;
    //商品编号
    private String goodsCode;
    //商品名称
    private String goodsName;
    //单价
    private String price;
    //订货数量
    private String goodsNum;
    //进项税率
    private String vatInRate;
    //标准度量单位
    private String standardUom;
    //大类编号
    private String dept;
    //条形码
    private String barcode;
}
