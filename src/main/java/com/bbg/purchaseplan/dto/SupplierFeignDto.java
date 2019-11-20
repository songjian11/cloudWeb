package com.bbg.purchaseplan.dto;

import java.util.List;

/**
 * 供应商信息
 * songjian
 * 20191109
 */
public class SupplierFeignDto {
    private Long supplier;//供应商编码

    private String supName;//供应商名称

    private Long supplierParent;//父供应商

    private String supplierType;//供应商类别  取（A:当地合作社  B:采购到农户收货   C：全国专业供应商  D:地采 E:厂家直供 F:市场直采 G:厂家 H:经销商  I:I:湖南市场供应商）

    private String contactName;//联系名称

    private String contactPhone;//联系电话

    private String contactFax;//联系传真

    private String contactEmail;//联系人邮箱

    private Integer groups;//部门 (供应商大类信息取这个字段) (32:水产，33：干杂，35：鲜肉，36：蔬菜，37：水果，38：米蛋)

    private String taxNumber;//纳税登记号

    private String paymentMethod;//支付方式

    private String bankName;//开户银行

    private String accountHolder;//  开户名

    private String bankAccount;//开户帐号

    private String businessType;//经营方式  经销，代销，联营、租赁

    private String payingInd;//总部代付

    private String supStatus;//状态      I-Inactive失效  A-Active生效

    private String retAllowInd;//允许退货

    private String ediPoInd;//EDI订单标识

    private String ediAsn;// EDI装运单

    private String ediPoChg;//EDI订单修改

    private String ediPoConfirm;//EDI订单确认

    private String dsdInd;//直送标识

    private String address;// 地址

    private String city;//城市

    private String state;//省

    private String countryId;//国家

    private String post;//    邮编

    private String terms;// 帐期

    private String termsDesc;//描述

    private String advancePaymentInd;//预付款标识Y-预付款，N-非预付款

    private String centralOrderInd;//    统采标识

    private String purchaseInd;//是否收购       Y-收购供应商，N-非收购供应商

    private String publishTime;// 发布时间

    private String createDatetime;//创建时间

    private String lastUpdateDatetime;//最后更新时间

    private List<SupplierGoodsFeignDto> itemList;//商品信息列表

    public List<SupplierGoodsFeignDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<SupplierGoodsFeignDto> itemList) {
        this.itemList = itemList;
    }

    public Long getSupplier() {
        return supplier;
    }

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public Long getSupplierParent() {
        return supplierParent;
    }

    public void setSupplierParent(Long supplierParent) {
        this.supplierParent = supplierParent;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactFax() {
        return contactFax;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getPayingInd() {
        return payingInd;
    }

    public void setPayingInd(String payingInd) {
        this.payingInd = payingInd;
    }

    public String getSupStatus() {
        return supStatus;
    }

    public void setSupStatus(String supStatus) {
        this.supStatus = supStatus;
    }

    public String getRetAllowInd() {
        return retAllowInd;
    }

    public void setRetAllowInd(String retAllowInd) {
        this.retAllowInd = retAllowInd;
    }

    public String getEdiPoInd() {
        return ediPoInd;
    }

    public void setEdiPoInd(String ediPoInd) {
        this.ediPoInd = ediPoInd;
    }

    public String getEdiAsn() {
        return ediAsn;
    }

    public void setEdiAsn(String ediAsn) {
        this.ediAsn = ediAsn;
    }

    public String getEdiPoChg() {
        return ediPoChg;
    }

    public void setEdiPoChg(String ediPoChg) {
        this.ediPoChg = ediPoChg;
    }

    public String getEdiPoConfirm() {
        return ediPoConfirm;
    }

    public void setEdiPoConfirm(String ediPoConfirm) {
        this.ediPoConfirm = ediPoConfirm;
    }

    public String getDsdInd() {
        return dsdInd;
    }

    public void setDsdInd(String dsdInd) {
        this.dsdInd = dsdInd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getTermsDesc() {
        return termsDesc;
    }

    public void setTermsDesc(String termsDesc) {
        this.termsDesc = termsDesc;
    }

    public String getAdvancePaymentInd() {
        return advancePaymentInd;
    }

    public void setAdvancePaymentInd(String advancePaymentInd) {
        this.advancePaymentInd = advancePaymentInd;
    }

    public String getCentralOrderInd() {
        return centralOrderInd;
    }

    public void setCentralOrderInd(String centralOrderInd) {
        this.centralOrderInd = centralOrderInd;
    }

    public String getPurchaseInd() {
        return purchaseInd;
    }

    public void setPurchaseInd(String purchaseInd) {
        this.purchaseInd = purchaseInd;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(String lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    @Override
    public String toString() {
        return "SupplierFeignDto{" +
                "supplier=" + supplier +
                ", supName='" + supName + '\'' +
                ", supplierParent=" + supplierParent +
                ", supplierType='" + supplierType + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactFax='" + contactFax + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", groups=" + groups +
                ", taxNumber='" + taxNumber + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", businessType='" + businessType + '\'' +
                ", payingInd='" + payingInd + '\'' +
                ", supStatus='" + supStatus + '\'' +
                ", retAllowInd='" + retAllowInd + '\'' +
                ", ediPoInd='" + ediPoInd + '\'' +
                ", ediAsn='" + ediAsn + '\'' +
                ", ediPoChg='" + ediPoChg + '\'' +
                ", ediPoConfirm='" + ediPoConfirm + '\'' +
                ", dsdInd='" + dsdInd + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", countryId='" + countryId + '\'' +
                ", post='" + post + '\'' +
                ", terms='" + terms + '\'' +
                ", termsDesc='" + termsDesc + '\'' +
                ", advancePaymentInd='" + advancePaymentInd + '\'' +
                ", centralOrderInd='" + centralOrderInd + '\'' +
                ", purchaseInd='" + purchaseInd + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", createDatetime='" + createDatetime + '\'' +
                ", lastUpdateDatetime='" + lastUpdateDatetime + '\'' +
                '}';
    }
}
