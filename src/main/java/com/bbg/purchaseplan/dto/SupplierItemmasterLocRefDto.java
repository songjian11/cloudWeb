package com.bbg.purchaseplan.dto;

import java.math.BigDecimal;
import java.util.Date;

public class SupplierItemmasterLocRefDto {
    private String itemcode;//商品

    private Long supplier;//  供应商编码

    private Long loc;// 地点

    private String locType;//  地点类型

    private String primarySuppInd;//  是否主供应商

    private String originalCountry;//国家描述

    private String primaryCountryInd;//是否主国家

    private String itemParent;//父商品

    private BigDecimal unitCostEclVat;//单位成本

    private BigDecimal unitCostIncVat;//含税成本

    private BigDecimal minOrderQty;//最小订货数量

    private BigDecimal minIncreaseQty;//订货批量

    private String tinyInd;//是否允许拆零

    private String storeOrderInd;//是否可订货

    private String storeReturnInd;//是否可退货

    private BigDecimal consessionGm;//联营扣点

    private String logisticDeliveryModel;//物流模式

    private String creator;//建立者

    private Date createDatetime;//创建时间

    private Date lastUpdateDatetime;//最后更新时间

    private String lastUpdateId;//最后更新ID

    private Date publishTime;//  发布时间

    private String supName;//供应商名称

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public Long getSupplier() {
        return supplier;
    }

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }

    public Long getLoc() {
        return loc;
    }

    public void setLoc(Long loc) {
        this.loc = loc;
    }

    public String getLocType() {
        return locType;
    }

    public void setLocType(String locType) {
        this.locType = locType;
    }

    public String getPrimarySuppInd() {
        return primarySuppInd;
    }

    public void setPrimarySuppInd(String primarySuppInd) {
        this.primarySuppInd = primarySuppInd;
    }

    public String getOriginalCountry() {
        return originalCountry;
    }

    public void setOriginalCountry(String originalCountry) {
        this.originalCountry = originalCountry;
    }

    public String getPrimaryCountryInd() {
        return primaryCountryInd;
    }

    public void setPrimaryCountryInd(String primaryCountryInd) {
        this.primaryCountryInd = primaryCountryInd;
    }

    public String getItemParent() {
        return itemParent;
    }

    public void setItemParent(String itemParent) {
        this.itemParent = itemParent;
    }

    public BigDecimal getUnitCostEclVat() {
        return unitCostEclVat;
    }

    public void setUnitCostEclVat(BigDecimal unitCostEclVat) {
        this.unitCostEclVat = unitCostEclVat;
    }

    public BigDecimal getUnitCostIncVat() {
        return unitCostIncVat;
    }

    public void setUnitCostIncVat(BigDecimal unitCostIncVat) {
        this.unitCostIncVat = unitCostIncVat;
    }

    public BigDecimal getMinOrderQty() {
        return minOrderQty;
    }

    public void setMinOrderQty(BigDecimal minOrderQty) {
        this.minOrderQty = minOrderQty;
    }

    public BigDecimal getMinIncreaseQty() {
        return minIncreaseQty;
    }

    public void setMinIncreaseQty(BigDecimal minIncreaseQty) {
        this.minIncreaseQty = minIncreaseQty;
    }

    public String getTinyInd() {
        return tinyInd;
    }

    public void setTinyInd(String tinyInd) {
        this.tinyInd = tinyInd;
    }

    public String getStoreOrderInd() {
        return storeOrderInd;
    }

    public void setStoreOrderInd(String storeOrderInd) {
        this.storeOrderInd = storeOrderInd;
    }

    public String getStoreReturnInd() {
        return storeReturnInd;
    }

    public void setStoreReturnInd(String storeReturnInd) {
        this.storeReturnInd = storeReturnInd;
    }

    public BigDecimal getConsessionGm() {
        return consessionGm;
    }

    public void setConsessionGm(BigDecimal consessionGm) {
        this.consessionGm = consessionGm;
    }

    public String getLogisticDeliveryModel() {
        return logisticDeliveryModel;
    }

    public void setLogisticDeliveryModel(String logisticDeliveryModel) {
        this.logisticDeliveryModel = logisticDeliveryModel;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(Date lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    public String getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(String lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    @Override
    public String toString() {
        return "supplierItemmasterLocRefDto{" +
                "itemcode='" + itemcode + '\'' +
                ", supplier=" + supplier +
                ", loc=" + loc +
                ", locType='" + locType + '\'' +
                ", primarySuppInd='" + primarySuppInd + '\'' +
                ", originalCountry='" + originalCountry + '\'' +
                ", primaryCountryInd='" + primaryCountryInd + '\'' +
                ", itemParent='" + itemParent + '\'' +
                ", unitCostEclVat=" + unitCostEclVat +
                ", unitCostIncVat=" + unitCostIncVat +
                ", minOrderQty=" + minOrderQty +
                ", minIncreaseQty=" + minIncreaseQty +
                ", tinyInd='" + tinyInd + '\'' +
                ", storeOrderInd='" + storeOrderInd + '\'' +
                ", storeReturnInd='" + storeReturnInd + '\'' +
                ", consessionGm=" + consessionGm +
                ", logisticDeliveryModel='" + logisticDeliveryModel + '\'' +
                ", creator='" + creator + '\'' +
                ", createDatetime=" + createDatetime +
                ", lastUpdateDatetime=" + lastUpdateDatetime +
                ", lastUpdateId='" + lastUpdateId + '\'' +
                ", publishTime=" + publishTime +
                ", supName='" + supName + '\'' +
                '}';
    }
}
