package com.bbg.purchaseplan.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsFeignDto {
    // 商品编码
    private Integer itemId;
    // 父商品
    private String itemParent;
    // 包装商品标识
    private String packInd;
    // 商品层级
    private BigDecimal itemLevel;
    // 交易层级
    private BigDecimal tranLevel;
    // 商品组
    private Integer groupNo;
    // 大类编码
    private Integer deptId;
    // 大类名称
    private String deptName;
    // 中类编码
    private Integer classId;
    // 中类名称
    private String className;
    // 小类编码
    private Integer subclassId;
    // 小类名称
    private String subclassName;
    // 细分类编码
    private Integer ssubclassId;
    // 细分类名称
    private String ssubclassName;
    // 状态
    private String status;
    // 商品名称
    private String itemDesc;
    // 品牌
    private String brand;
    //产地
    private String placeOfProduction;
    // 标准度量单位
    private String standardUom;
    //包装数量
    private BigDecimal packageSize;
    // 包装度量单位-ID转为枚举值统一管理
    private String packageUom;
    // 建议零售价
    private BigDecimal mfgRecRetail;
    // 称重标识
    private String catchWeightInd;
    // 默认损耗比率
    private BigDecimal defaultWastePct;
    // 商品短描述
    private String shortDesc;
    // 库存标识 Y-可库存商品，N-非可库存商品
    private String inventoryInd;
    // 是否为商品
    private String merchandiseInd;
    // 是否生鲜商品
    private String freshItemInd;
    // 是否为柜组码
    private String jointItemInd;
    // 进项税率
    private BigDecimal vatInRate;
    // 销项税率
    private BigDecimal vatOutRate;
    // 条码
    private String barcode;
    // 编码类型
    private String itemNumberType;
    // 是否为主条形码
    private String primaryRefItemInd;
    // 发布时间
    private Date publishTime;
    // 创建时间
    private String createDatetime;
    // xxxx
    private String lastUpdateId;
    // xxxx
    private String lastUpdateDatetime;

    //糖度
    private String brix;

    //酸度
    private String acidity;

    //熟度
    private String maturity;

    //鲜活度
    private String freshness;

    //含冰率
    private String iceRate;

    //规格
    private String specification;

    private String spaceclassNew;

    // 棚格图分类
    private String spacenameNew;

    // 主合同供应商
    public String masterContractSupplier;

    // 供应商名称
    public String supplierName;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemParent() {
        return itemParent;
    }

    public void setItemParent(String itemParent) {
        this.itemParent = itemParent;
    }

    public String getPackInd() {
        return packInd;
    }

    public void setPackInd(String packInd) {
        this.packInd = packInd;
    }

    public BigDecimal getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(BigDecimal itemLevel) {
        this.itemLevel = itemLevel;
    }

    public BigDecimal getTranLevel() {
        return tranLevel;
    }

    public void setTranLevel(BigDecimal tranLevel) {
        this.tranLevel = tranLevel;
    }

    public Integer getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(Integer groupNo) {
        this.groupNo = groupNo;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getSubclassId() {
        return subclassId;
    }

    public void setSubclassId(Integer subclassId) {
        this.subclassId = subclassId;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }

    public Integer getSsubclassId() {
        return ssubclassId;
    }

    public void setSsubclassId(Integer ssubclassId) {
        this.ssubclassId = ssubclassId;
    }

    public String getSsubclassName() {
        return ssubclassName;
    }

    public void setSsubclassName(String ssubclassName) {
        this.ssubclassName = ssubclassName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPlaceOfProduction() {
        return placeOfProduction;
    }

    public void setPlaceOfProduction(String placeOfProduction) {
        this.placeOfProduction = placeOfProduction;
    }

    public String getStandardUom() {
        return standardUom;
    }

    public void setStandardUom(String standardUom) {
        this.standardUom = standardUom;
    }

    public BigDecimal getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(BigDecimal packageSize) {
        this.packageSize = packageSize;
    }

    public String getPackageUom() {
        return packageUom;
    }

    public void setPackageUom(String packageUom) {
        this.packageUom = packageUom;
    }

    public BigDecimal getMfgRecRetail() {
        return mfgRecRetail;
    }

    public void setMfgRecRetail(BigDecimal mfgRecRetail) {
        this.mfgRecRetail = mfgRecRetail;
    }

    public String getCatchWeightInd() {
        return catchWeightInd;
    }

    public void setCatchWeightInd(String catchWeightInd) {
        this.catchWeightInd = catchWeightInd;
    }

    public BigDecimal getDefaultWastePct() {
        return defaultWastePct;
    }

    public void setDefaultWastePct(BigDecimal defaultWastePct) {
        this.defaultWastePct = defaultWastePct;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getInventoryInd() {
        return inventoryInd;
    }

    public void setInventoryInd(String inventoryInd) {
        this.inventoryInd = inventoryInd;
    }

    public String getMerchandiseInd() {
        return merchandiseInd;
    }

    public void setMerchandiseInd(String merchandiseInd) {
        this.merchandiseInd = merchandiseInd;
    }

    public String getFreshItemInd() {
        return freshItemInd;
    }

    public void setFreshItemInd(String freshItemInd) {
        this.freshItemInd = freshItemInd;
    }

    public String getJointItemInd() {
        return jointItemInd;
    }

    public void setJointItemInd(String jointItemInd) {
        this.jointItemInd = jointItemInd;
    }

    public BigDecimal getVatInRate() {
        return vatInRate;
    }

    public void setVatInRate(BigDecimal vatInRate) {
        this.vatInRate = vatInRate;
    }

    public BigDecimal getVatOutRate() {
        return vatOutRate;
    }

    public void setVatOutRate(BigDecimal vatOutRate) {
        this.vatOutRate = vatOutRate;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItemNumberType() {
        return itemNumberType;
    }

    public void setItemNumberType(String itemNumberType) {
        this.itemNumberType = itemNumberType;
    }

    public String getPrimaryRefItemInd() {
        return primaryRefItemInd;
    }

    public void setPrimaryRefItemInd(String primaryRefItemInd) {
        this.primaryRefItemInd = primaryRefItemInd;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(String lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public String getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(String lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    public String getBrix() {
        return brix;
    }

    public void setBrix(String brix) {
        this.brix = brix;
    }

    public String getAcidity() {
        return acidity;
    }

    public void setAcidity(String acidity) {
        this.acidity = acidity;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getFreshness() {
        return freshness;
    }

    public void setFreshness(String freshness) {
        this.freshness = freshness;
    }

    public String getIceRate() {
        return iceRate;
    }

    public void setIceRate(String iceRate) {
        this.iceRate = iceRate;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSpaceclassNew() {
        return spaceclassNew;
    }

    public void setSpaceclassNew(String spaceclassNew) {
        this.spaceclassNew = spaceclassNew;
    }

    public String getSpacenameNew() {
        return spacenameNew;
    }

    public void setSpacenameNew(String spacenameNew) {
        this.spacenameNew = spacenameNew;
    }

    public String getMasterContractSupplier() {
        return masterContractSupplier;
    }

    public void setMasterContractSupplier(String masterContractSupplier) {
        this.masterContractSupplier = masterContractSupplier;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "GoodsFeignDto{" +
                "itemId=" + itemId +
                ", itemParent='" + itemParent + '\'' +
                ", packInd='" + packInd + '\'' +
                ", itemLevel=" + itemLevel +
                ", tranLevel=" + tranLevel +
                ", groupNo=" + groupNo +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", subclassId=" + subclassId +
                ", subclassName='" + subclassName + '\'' +
                ", ssubclassId=" + ssubclassId +
                ", ssubclassName='" + ssubclassName + '\'' +
                ", status='" + status + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                ", brand='" + brand + '\'' +
                ", placeOfProduction='" + placeOfProduction + '\'' +
                ", standardUom='" + standardUom + '\'' +
                ", packageSize=" + packageSize +
                ", packageUom='" + packageUom + '\'' +
                ", mfgRecRetail=" + mfgRecRetail +
                ", catchWeightInd='" + catchWeightInd + '\'' +
                ", defaultWastePct=" + defaultWastePct +
                ", shortDesc='" + shortDesc + '\'' +
                ", inventoryInd='" + inventoryInd + '\'' +
                ", merchandiseInd='" + merchandiseInd + '\'' +
                ", freshItemInd='" + freshItemInd + '\'' +
                ", jointItemInd='" + jointItemInd + '\'' +
                ", vatInRate=" + vatInRate +
                ", vatOutRate=" + vatOutRate +
                ", barcode='" + barcode + '\'' +
                ", itemNumberType='" + itemNumberType + '\'' +
                ", primaryRefItemInd='" + primaryRefItemInd + '\'' +
                ", publishTime=" + publishTime +
                ", createDatetime='" + createDatetime + '\'' +
                ", lastUpdateId='" + lastUpdateId + '\'' +
                ", lastUpdateDatetime='" + lastUpdateDatetime + '\'' +
                ", brix='" + brix + '\'' +
                ", acidity='" + acidity + '\'' +
                ", maturity='" + maturity + '\'' +
                ", freshness='" + freshness + '\'' +
                ", iceRate='" + iceRate + '\'' +
                ", specification='" + specification + '\'' +
                ", spaceclassNew='" + spaceclassNew + '\'' +
                ", spacenameNew='" + spacenameNew + '\'' +
                ", masterContractSupplier='" + masterContractSupplier + '\'' +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
