package com.bbg.purchaseplan.po;

import com.bbg.purchaseplan.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PoTransferDetail implements Serializable {
    private static final long serialVersionUID = -3969722271675939983L;
    /**
     * 发货单号
     */
    private String poAsnSn;
    /**
     * 行号
     */
    private String seqNo;
    /**
     * 商品编码
     */
    private String item;
    /**
     * 商品条码
     */
    private String barcode = "";
    /**
     * 商品单位
     */
    private String uomDesc;
    /**
     * 未税到仓单价
     */
    private String sendUnitPriceEcl;
    /**
     * 含税到仓单价
     */
    private String sendUnitPriceInc;
    /**
     * 进项税率
     */
    private BigDecimal taxRate;
    /**
     * 单位售价
     */
    private String unitRetail = "0";
    /**
     * 发货数量
     */
    private String poAsnQty;
    /**
     * 取消数量
     */
    private String qtyCancelled = "0";
    /**
     * 发布时间
     */
    private String publish_time = "";
    /**
     * 附加属性
     */
    private String addAttribute = "";
    /**
     * 重量单价
     */
    private BigDecimal unitPrice = BigDecimal.ZERO;
    /**
     * 运费
     */
    private BigDecimal freight = BigDecimal.ZERO;
    /**
     * 总数量
     */
    private BigDecimal totalQty;
    /**
     * 标准件
     */
    private BigDecimal standardOfPackage = BigDecimal.ZERO;

    /**
     * 获取发货单行组装数据
     *
     * @param
     * @return java.lang.String
     * @author wells.wong
     * @date 2019/9/5
     */
    public String getTransferDetailData() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getPoAsnSn())
                .append("|").append(this.getSeqNo())
                .append("|").append(this.getItem())
                .append("|").append(this.getBarcode())
                .append("|").append(this.getUomDesc())
                .append("|").append(this.getSendUnitPriceEcl())
                .append("|").append(this.getSendUnitPriceInc())
                .append("|").append(this.getTaxRate())
                .append("|").append(this.getUnitRetail())
                .append("|").append(this.getPoAsnQty())
                .append("|").append(this.getQtyCancelled())
                .append("|").append(this.getPublish_time())
                .append("|").append(this.getAddAttribute())
                .append("\n");
        return sb.toString();
    }

    public String getSendUnitPriceInc() {
        //系统里保存的是含税单价
        String value = "";
        BigDecimal unitRetailBg = new BigDecimal(StringUtils.isEmpty(this.unitRetail) ? "0" : this.unitRetail);
        if(null != taxRate){
            value = unitRetailBg.divide(taxRate.add(BigDecimal.ONE), BigDecimal.ROUND_HALF_UP, 2).toString();
        }else{
            value = unitRetailBg.toString();
        }
        return value;
    }
}
