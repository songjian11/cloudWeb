package com.bbg.purchaseplan.dto;

/**
 * 商品信息
 * songjian
 * 20191109
 */
public class SupplierGoodsFeignDto {
    // 商品编号
    private String itemcode;
    // 商品名称
    private String itemdesc;
    // 供应商
    private String supplier;
    // 是否主供应商
    private String primarySuppInd;
    // 国家描述
    private String originalCountry;
    // 是否主国家
    private String primaryCountryInd;
    // 成本计价单位
    private String costUom;
    // 商品进价
    private String unitCostEclVat;
    // 含税进价
    private String unitCostIncVat;
    // 默认采购单位
    private String defaultUop;
    // 内箱数量
    private String innerPackSize;
    // 供应商包装数量
    private String suppPackSize;
    // 箱数
    private String ti;
    // 层数
    private String hi;
    // 订单级别
    private String roundLvl;
    // 舍入比例
    private String roundRate;
    // 最后更新时间(2018-11-22 10:42:00)
    private String lastUpdateDatetime;
    // 建立时间(2018-11-22 10:42:00)
    private String createDatetime;
    // 发布时间(2019-08-29 10:59:00)
    private String publishTime;
    // 最后更新者(T1000079021)
    private String lastUpdateId;

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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

    public String getCostUom() {
        return costUom;
    }

    public void setCostUom(String costUom) {
        this.costUom = costUom;
    }

    public String getUnitCostEclVat() {
        return unitCostEclVat;
    }

    public void setUnitCostEclVat(String unitCostEclVat) {
        this.unitCostEclVat = unitCostEclVat;
    }

    public String getUnitCostIncVat() {
        return unitCostIncVat;
    }

    public void setUnitCostIncVat(String unitCostIncVat) {
        this.unitCostIncVat = unitCostIncVat;
    }

    public String getDefaultUop() {
        return defaultUop;
    }

    public void setDefaultUop(String defaultUop) {
        this.defaultUop = defaultUop;
    }

    public String getInnerPackSize() {
        return innerPackSize;
    }

    public void setInnerPackSize(String innerPackSize) {
        this.innerPackSize = innerPackSize;
    }

    public String getSuppPackSize() {
        return suppPackSize;
    }

    public void setSuppPackSize(String suppPackSize) {
        this.suppPackSize = suppPackSize;
    }

    public String getTi() {
        return ti;
    }

    public void setTi(String ti) {
        this.ti = ti;
    }

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

    public String getRoundLvl() {
        return roundLvl;
    }

    public void setRoundLvl(String roundLvl) {
        this.roundLvl = roundLvl;
    }

    public String getRoundRate() {
        return roundRate;
    }

    public void setRoundRate(String roundRate) {
        this.roundRate = roundRate;
    }

    public String getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(String lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(String lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    @Override
    public String toString() {
        return "GoodsFeignDto{" +
                "itemcode='" + itemcode + '\'' +
                ", itemdesc='" + itemdesc + '\'' +
                ", supplier='" + supplier + '\'' +
                ", primarySuppInd='" + primarySuppInd + '\'' +
                ", originalCountry='" + originalCountry + '\'' +
                ", primaryCountryInd='" + primaryCountryInd + '\'' +
                ", costUom='" + costUom + '\'' +
                ", unitCostEclVat='" + unitCostEclVat + '\'' +
                ", unitCostIncVat='" + unitCostIncVat + '\'' +
                ", defaultUop='" + defaultUop + '\'' +
                ", innerPackSize='" + innerPackSize + '\'' +
                ", suppPackSize='" + suppPackSize + '\'' +
                ", ti='" + ti + '\'' +
                ", hi='" + hi + '\'' +
                ", roundLvl='" + roundLvl + '\'' +
                ", roundRate='" + roundRate + '\'' +
                ", lastUpdateDatetime='" + lastUpdateDatetime + '\'' +
                ", createDatetime='" + createDatetime + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", lastUpdateId='" + lastUpdateId + '\'' +
                '}';
    }
}
