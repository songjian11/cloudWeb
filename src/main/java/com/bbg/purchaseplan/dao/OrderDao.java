package com.bbg.purchaseplan.dao;

import com.bbg.purchaseplan.po.GoodsOrderInfo;
import com.bbg.purchaseplan.po.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    /**
     * 不判断是否为空的插入
     * @param order
     * @return
     */
    int insert(Order order);

    /**
     * 会判断是否为空的判断
     * @param order
     * @return
     */
    int insertSelective(Order order);

    /**
     * 添加商品订单
     * @param order
     * @return
     */
    int saveOrder(Order order);

    /**
     * 查询商品信息(且按照采购时间升序排序)
     * @param depotCode 仓库编号
     * @param purchaseTime 要货时间
     * @return
     */
    List<GoodsOrderInfo> queryGoodsOrdersByDepotCodeAndPurchaseTime(@Param("depotCode") String depotCode,
                                                                    @Param("purchaseTime") String purchaseTime);

    List<GoodsOrderInfo> queryGoodsOrdersByPersonIdAndDepotCodeAndPurchaseTime(@Param("personId") String personId,
                                                                               @Param("depotCode") String depotCode,
                                                                               @Param("purchaseTime") String purchaseTime);

    /**
     * 查询商品信息
     * @param depotCode 仓库编号
     * @param suppCode 供应商编号
     * @param purchaseTime 要货时间
     * @return
     */
    List<GoodsOrderInfo> queryGoodsOrdersByDepotCodeAndSuppCodeAndPurchaseTime(@Param("depotCode") String depotCode,
                                                                    @Param("suppCode") String suppCode,
                                                                    @Param("purchaseTime") String purchaseTime);

    /**
     * 查询有效订单商品信息(flag=1)
     * @return
     */
    List<GoodsOrderInfo> queryOrderInfo();

    void updateBatchOrderStatus(List<String> list);

    String querySeq(String key);
}
