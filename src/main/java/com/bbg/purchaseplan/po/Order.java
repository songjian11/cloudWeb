package com.bbg.purchaseplan.po;

import lombok.Data;

import java.util.Date;

/**
 * 订单信息-对应表p_purchase_order
 */
@Data
public class Order {
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
    //添加时间
    private Date addTime;
    //添加人工号
    private String addPersonId;
    //标志
    private Short flag;
    //备注
    private String remark;
    //预留
    private String mark1;
    //预留
    private String mark2;
}
