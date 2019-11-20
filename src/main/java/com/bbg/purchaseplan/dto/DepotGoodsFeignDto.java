package com.bbg.purchaseplan.dto;

/**
 * 仓库商品信息
 */
public class DepotGoodsFeignDto {
    //商品名称
    private String goodsName;
    // 商品编号
    private String goodsCode;
    // 仓库ID
    private String whId;
    // 仓库名称
    private String whName;
    // 要货时间
    private String createDate;
    // 合计数量
    private String qty;
    // 订单类型（要货/批发）
    private String orderType;
    // 订货人
    private String buyer;
    // 大类
    private String dept;
    // 库存数量
    private String stockQty;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getWhId() {
        return whId;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }

    @Override
    public String toString() {
        return "DepotGoodsFeignDto{" +
                "goodsName='" + goodsName + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", whId='" + whId + '\'' +
                ", whName='" + whName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", qty='" + qty + '\'' +
                ", orderType='" + orderType + '\'' +
                ", buyer='" + buyer + '\'' +
                ", dept='" + dept + '\'' +
                ", stockQty='" + stockQty + '\'' +
                '}';
    }
}
