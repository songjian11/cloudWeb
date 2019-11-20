package com.bbg.purchaseplan.service.impl;

import com.bbg.purchaseplan.bo.request.GoodsOrderInfoRequest;
import com.bbg.purchaseplan.bo.response.GoodsOrderInfoResponse;
import com.bbg.purchaseplan.bo.request.SupplierOrderInfoRequest;
import com.bbg.purchaseplan.bo.response.DepotOrderDetailResponse;
import com.bbg.purchaseplan.bo.response.GoodsOrderDetailResponse;
import com.bbg.purchaseplan.bo.response.OrderSumInfoResponse;
import com.bbg.purchaseplan.dao.GoodsDao;
import com.bbg.purchaseplan.dao.OrderDao;
import com.bbg.purchaseplan.dto.*;
import com.bbg.purchaseplan.exception.BussinessException;
import com.bbg.purchaseplan.feignService.Impl.DepotFeignServiceImpl;
import com.bbg.purchaseplan.feignService.Impl.GoodsFeignServiceImpl;
import com.bbg.purchaseplan.feignService.Impl.OrderFeignServiceImpl;
import com.bbg.purchaseplan.po.*;
import com.bbg.purchaseplan.service.OrderService;
import com.bbg.purchaseplan.utils.DateUtil;
import com.bbg.purchaseplan.utils.FileUtils;
import com.bbg.purchaseplan.utils.GenerateCodeUtil;
import com.bbg.purchaseplan.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * 采购计划-订单信息查询和订货
 * songjian
 * 20191107
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    @SuppressWarnings("all")
    OrderDao orderDao;
    @Autowired
    @SuppressWarnings("all")
    GoodsDao goodsDao;
    @Autowired
    OrderFeignServiceImpl orderFeignService;
    @Autowired
    DepotFeignServiceImpl depotFeignService;
    @Autowired
    GoodsFeignServiceImpl goodsFeignService;
    @Value("${path.transferFilePath}")
    private String transferFilePath;

    /**
     * 根据工号查询订单汇总信息
     * @param personId 工号
     * @return
     * @throws Exception
     */
    @Override
    public OrderSumInfoResponse queryOrderSumInfoByPersonId(String personId) throws Exception {
        OrderSumInfoResponse result = new OrderSumInfoResponse();
        // 仓库商品信息
        List<DepotGoodsFeignDto> depotGoods = null;
        // 需定商品数量
        String purchaseGoodsNum = null;
        // 已定商品数量
        String hasGoodsNum = null;
        if(StringUtils.isEmpty(personId)){
            throw new BussinessException("工号不能为空");
        }
        // 根据工号和要货时间查询仓库商品信息
        depotGoods = depotFeignService.getSupplierGoods(personId, DateUtil.format(new Date(),"yyyyMMdd"));
        if(null == depotGoods){
            throw new BussinessException("仓库信息不存在");
        }
        purchaseGoodsNum = String.valueOf(depotGoods.size());
        // 查询已订货数量
        if(depotGoods.size() > 0){
            String depotCode = depotGoods.get(0).getWhId();
            List<GoodsOrderInfo> goodsOrders = orderDao.queryGoodsOrdersByPersonIdAndDepotCodeAndPurchaseTime(personId, depotCode, DateUtil.format(new Date(),"yyyyMMdd"));
            if(null != goodsOrders){
                hasGoodsNum = String.valueOf(goodsOrders.size());
            }
        }
        result.setPurchaseGoodsNum(StringUtils.isEmpty(purchaseGoodsNum)? "0" : purchaseGoodsNum);
        result.setHasGoodsNum(StringUtils.isEmpty(hasGoodsNum)? "0" : hasGoodsNum);
        return result;
    }

    /**
     * 根据工号查询仓库订单明细
     * @param personId 工号
     * @return
     * @throws Exception
     */
    @Override
    public DepotOrderDetailResponse queryDepotOrderDetailByPersonId(String personId) throws Exception {
        DepotOrderDetailResponse result = new DepotOrderDetailResponse();
        // 仓库订单商品明细
        List<DepotGoodsFeignDto> depotGoods = null;
        // 订单商品信息
        List<GoodsOrderInfo> goodsOrderInfos = null;
        // 排序好的商品列表
        List<GoodsOrderDetailResponse> goodsList = null;
        // 要货时间
        String purchaseTime = DateUtil.format(new Date(),"yyyyMMdd");
        // 仓库编号
        String depotCode = null;
        // 仓库名称
        String depotName = null;
        if(StringUtils.isEmpty(personId)){
            throw new BussinessException("工号不能为空");
        }
        depotGoods = depotFeignService.getSupplierGoods(personId, purchaseTime);
        if(null == depotGoods || 0 == depotGoods.size()){
            throw new BussinessException("仓库信息不存在");
        }
        depotCode = depotGoods.get(0).getWhId();
        depotName = depotGoods.get(0).getWhName();
        //查询本地已经订购的商品订单信息
        goodsOrderInfos = orderDao.queryGoodsOrdersByPersonIdAndDepotCodeAndPurchaseTime(personId, depotCode, purchaseTime);
        // 将仓库和本地已订购的商品信息整合到一起,按照商品分组,且计算已订购商品数量和建议采购数量,同一类商品订单信息按照订货时间排序
        // 排序规则：同一类商品数据,仓库商品数据放在第一位置，本地已订货商品信息按照订货时间排序放在后面
        goodsList = getGoodsOrderDetail(goodsOrderInfos, depotGoods);
        result.setDepotCode(depotCode);
        result.setDepotName(depotName);
        result.setPurchaseTime(purchaseTime);
        result.setGoodsList(goodsList);
        return result;
    }

    /**
     * 保存供应商下单信息
     * @param param
     * @throws Exception
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveOrder(SupplierOrderInfoRequest param) throws Exception {
        List<Goods> list = new ArrayList<>();
        String seq = orderDao.querySeq("order_seq");
        // 订单编号
        String orderCode = GenerateCodeUtil.generateBBgCode(param.getDepotCode(), seq);
        // 字段校验
        saveOrderCheck(param);
        // 提交的商品信息
        List<GoodsOrderInfoRequest> goodsList = param.getGoodsList();
        // 校验订单有效性
        saveOrderValid(param.getSuppCode(), param.getDepotCode(), param.getOrderPersonId(), goodsList);
        // 提交数据
        for(GoodsOrderInfoRequest goodsRequest : goodsList){ // 将bo转换为数据库对应的实体类
            Goods goods = new Goods();
            BeanUtils.copyProperties(goodsRequest, goods);
            Future<GoodsFeignDto> goodsFeignDtoFuture = goodsFeignService.collapsingShowItemMaster(goods.getGoodsCode());
            if(null == goodsFeignDtoFuture || null == goodsFeignDtoFuture.get()){
                throw new BussinessException("商品[" + goods.getGoodsName() + "|" + goods.getGoodsCode() + "]"
                        + "的商品信息不存在");
            }
            goods.setDept(goodsFeignDtoFuture.get().getDeptId().toString());
            goods.setStandardUom(goodsFeignDtoFuture.get().getStandardUom());
            goods.setVatInRate(goodsFeignDtoFuture.get().getVatOutRate().toString());
            goods.setBarcode(goodsFeignDtoFuture.get().getBarcode());
            goods.setOrderCode(orderCode);
            list.add(goods);
        }
        if(null != list && list.size() > 0){ // 商品信息存在时才生成订单
            Order order = new Order();
            order.setOrderCode(orderCode);
            order.setAddTime(new Date());
            order.setAddPersonId(param.getOrderPersonId());
            BeanUtils.copyProperties(param, order);
            // 保存商品订单信息
            orderDao.saveOrder(order);
            // 批量保存商品信息
            goodsDao.saveBatchGoods(list);
        }else{
            throw new BussinessException("商品信息无效(商品订货数量不能为零或者商品信息没有提交)");
        }
    }

    /**
     * 单价是否合法
     * @param depotCode 商品编号
     * @param goodsCode 商品编号
     * @param supplierCode 供应商编号
     * @param price 单价
     * @return
     * @throws Exception
     */
    @Override
    public String isLegalPriceBygoodsCodeAndsupplierCodeAndPrice(String depotCode, String goodsCode, String supplierCode, String price) throws Exception {
        if(StringUtils.isEmpty(goodsCode) || StringUtils.isEmpty(supplierCode) || StringUtils.isEmpty(price)){
            throw new BussinessException("参数错误");
        }
        // 根据商品和供应商插叙供应商商品信息
        SupplierFeignDto supplierFeignDto = orderFeignService.selectSuppOne(goodsCode, supplierCode, depotCode);
        if(null == supplierFeignDto){
            throw new BussinessException("供应商不可订货");
        }
        List<SupplierGoodsFeignDto> itemList = supplierFeignDto.getItemList();
        if(null == itemList || 0 == itemList.size()){
            throw new BussinessException("商品不可订货");
        }
        // 判断商品单价是否小于0
        if(!StringUtils.isPosNumber(price)){
            throw new BussinessException("商品单价必须为大于0的数字");
        }
        SupplierGoodsFeignDto supplierGoodsFeignDto = itemList.get(0);
        if(compareGoodsPrice(price, supplierGoodsFeignDto.getUnitCostEclVat()) < 0){// 比较单价
            return "success";
        }
        throw new BussinessException("商品单价必须小于协议进价");
    }

    /**
     * 根据供应商编号,仓库编号和要货时间查询商品列表
     * @param supplierCode 供应商编号
     * @param depotCode 仓库编号
     * @param purchaseTime 要货时间
     * @return
     * @throws Exception
     */
    @Override
    public List<GoodsOrderInfoResponse> queryGoodsInfo(String personId, String supplierCode, String depotCode, String purchaseTime) throws Exception {
        List<GoodsOrderInfoResponse> result = new ArrayList<>();
        if(StringUtils.isEmpty(personId) || StringUtils.isEmpty(supplierCode) || StringUtils.isEmpty(depotCode) || StringUtils.isEmpty(purchaseTime)){
            throw new BussinessException("参数错误");
        }
        //查询供应商的商品信息
        List<SupplierGoodsFeignDto> supplierGoodsFeignDtos = orderFeignService.selectGoodsByCondition(supplierCode);
        //根据仓库编号查询仓库商品信息
        List<DepotGoodsFeignDto> depotGoodsDtos = depotFeignService.getSupplierGoodView(personId, null, purchaseTime, depotCode);
        //根据要货时间，仓库编号查询订单商品信息
        List<GoodsOrderInfo> goodsOrderInfos = orderDao.queryGoodsOrdersByPersonIdAndDepotCodeAndPurchaseTime(personId, depotCode, purchaseTime);
        // 获取供应商商品,仓库商品,已订货商品三者交集商品数据
        List<GoodsOrderInfoResponse> list = getValidGoodsInfo(supplierGoodsFeignDtos, goodsOrderInfos,depotGoodsDtos);
        for(GoodsOrderInfoResponse goodsOrderInfoResponse : list){// 排除不可订货的商品
            SupplierFeignDto supplierFeignDto = orderFeignService.selectSuppOne(goodsOrderInfoResponse.getGoodsCode(), supplierCode, depotCode);
            if(null != supplierFeignDto){
                result.add(goodsOrderInfoResponse);
            }
        }
        return result;
    }

    /**
     * 同步订单信息
     * @throws Exception
     */
    @Override
    public void transferOrderData() throws Exception {
        //获取文件数据
        List<PoTransferHead> poTransferHeadList = getPoTransferHead();
        if (poTransferHeadList != null && poTransferHeadList.size() > 0) {
            Map<String, List<PoTransferHead>> map =
                    poTransferHeadList.stream().collect(Collectors.groupingBy(PoTransferHead::getWhCode));
            String headFileName = "";
            String detailFileName = "";
            List<PoTransferHead> headList = null;
            PoTransferHead poTransferHead = null;
            StringBuilder headContent = null;
            StringBuilder detailContent = null;
            String now = null;
            List<String> snList = new ArrayList<String>();
            for (Map.Entry<String, List<PoTransferHead>> entry : map.entrySet()) {
                now = DateUtil.dateTimeNow();
                headFileName = "BCS_PO_H_W" + entry.getKey() + "_" + now + ".dat";
                detailFileName = "BCS_PO_L_W" + entry.getKey() + "_" + now + ".dat";
                headList = entry.getValue();
                headContent = new StringBuilder();
                detailContent = new StringBuilder();
                int detailCount = 0;
                for (int i = 0; i < headList.size(); i++) {
                    poTransferHead = headList.get(i);
                    snList.add(poTransferHead.getPoAsnSn());
                    poTransferHead.setPublishTime(now);
                    headContent.append(poTransferHead.getTransferHeadData());
                    List<PoTransferDetail> poTransferDetailList = poTransferHead.getPoTransferDetailList();
                    for (int j = 0; j < poTransferDetailList.size(); j++) {
                        detailCount++;
                        PoTransferDetail poTransferDetail = poTransferDetailList.get(j);
                        poTransferDetail.setPoAsnSn(poTransferHead.getPoAsnSn());
                        poTransferDetail.setSeqNo(String.valueOf(i + 1));
                        poTransferDetail.setPublish_time(now);
                        detailContent.append(poTransferDetail.getTransferDetailData());
                    }
                }
                headContent.append("END|" + headList.size());
                headContent.append("\r\n");
                detailContent.append("END|" + detailCount);
                detailContent.append("\r\n");
                try {
                    //更新数据库，将flag修改为2
                    updateBatchOrderStatus(poTransferHeadList);
                    File file = new File(transferFilePath);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    FileUtils.WriteContentToFile(transferFilePath + File.separator + headFileName,
                            headContent.toString());
                    FileUtils.WriteContentToFile(transferFilePath + File.separator + detailFileName,
                            detailContent.toString());
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }

    /**
     * 更新订单状态
     */
    private void updateBatchOrderStatus(List<PoTransferHead> heads){
        List<String> list = new ArrayList<>();
        if(null != heads && heads.size() > 0){
            for(PoTransferHead poTransferHead : heads){
                list.add(poTransferHead.getPoAsnSn());
            }
            orderDao.updateBatchOrderStatus(list);
        }
    }

    /**
     * 获取头文件数据
     * @return
     */
    private List<PoTransferHead> getPoTransferHead(){
        List<PoTransferHead> result = new ArrayList<>();
        List<GoodsOrderInfo> orders = orderDao.queryOrderInfo();
        if(null != orders && orders.size() > 0){
            Map<String, List<GoodsOrderInfo>> orderMap = orders.stream().collect(Collectors.groupingBy(GoodsOrderInfo::getOrderCode));
            for(String orderCode : orderMap.keySet()){
                PoTransferHead poTransferHead = new PoTransferHead();
                List<GoodsOrderInfo> goods = orderMap.get(orderCode);
                poTransferHead.setPoAsnSn(orderCode);
                poTransferHead.setCreator(goods.get(0).getOrderPersonName());
                poTransferHead.setSupplier(goods.get(0).getSuppCode());
                poTransferHead.setWhCode(goods.get(0).getDepotCode());
                poTransferHead.setAuditor(goods.get(0).getOrderPersonName());
                poTransferHead.setGroupNo(goods.get(0).getDept());
                poTransferHead.setNotAfterDate(DateUtil.format(DateUtil.addDay(new Date(), 7),"yyyyMMdd"));
                poTransferHead.setAuditTime(DateUtil.format(new Date(), "yyyyMMdd"));
                poTransferHead.setCreateTime(DateUtil.format(new Date(), "yyyyMMdd"));
                poTransferHead.setPublishTime(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
                poTransferHead.setExpArrivalDate(DateUtil.format(new Date(), "yyyyMMdd"));
                poTransferHead.setPoTransferDetailList(getPoTransferDetail(goods));
                result.add(poTransferHead);
            }
        }
        return result;
    }

    /**
     * 获取行文件数据
     * @param goods
     */
    private List<PoTransferDetail> getPoTransferDetail(List<GoodsOrderInfo> goods) {
        List<PoTransferDetail> result = new ArrayList<>();
        for(GoodsOrderInfo good : goods){
            PoTransferDetail poTransferDetail = new PoTransferDetail();
            poTransferDetail.setPoAsnSn(good.getOrderCode());
            poTransferDetail.setItem(good.getGoodsCode());
            poTransferDetail.setBarcode(good.getBarcode());
            poTransferDetail.setUomDesc(good.getStandardUom());
            poTransferDetail.setSendUnitPriceEcl(good.getPrice());
            poTransferDetail.setTaxRate(new BigDecimal(StringUtils.isEmpty(good.getVatInRate()) ? "0" : good.getVatInRate()));
            poTransferDetail.setUnitPrice(new BigDecimal(StringUtils.isEmpty(good.getPrice()) ? "0" : good.getPrice()));
            poTransferDetail.setPoAsnQty(good.getGoodsNum());
            poTransferDetail.setPublish_time(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
            result.add(poTransferDetail);
        }
        return result;
    }

    /**
     * 获取商品信息(仓库商品信息和订单商品信息整合)
     * 注意：将仓库和本地已订购的商品信息整合到一起,按照商品分组,且计算已订购商品数量和建议采购数量,同一类商品订单信息按照订货时间排序
     * 排序规则：同一类商品数据,仓库商品数据放在第一位置，本地已订货商品信息按照订货时间排序放在后面
     * @param goodsOrders 订单商品信息
     * @param depotGoodsList 仓库商品信息
     * @return
     */
    private List<GoodsOrderDetailResponse> getGoodsOrderDetail(List<GoodsOrderInfo> goodsOrders,
                                                               List<DepotGoodsFeignDto> depotGoodsList){
        List<GoodsOrderDetailResponse> result = new ArrayList<>();
        if(null == depotGoodsList || 0 == depotGoodsList.size()){
            return result;
        }

        Map<String, List<GoodsOrderInfo>> map = null;
        if(null != goodsOrders && goodsOrders.size() > 0){
            map = goodsOrders.stream().collect(Collectors.groupingBy(GoodsOrderInfo::getGoodsCode));
        }

        for(DepotGoodsFeignDto depotGoodsFeignDto : depotGoodsList){// 将仓库商品和已订货商品的交集部分+仓库所有商品整合
            if(null != map && map.size() > 0 && map.containsKey(depotGoodsFeignDto.getGoodsCode())){// 判断是否有交集，有交集则将已订货商品拼接在仓库商品后面，按时间排序
                List<GoodsOrderInfo> goodsOrderInfoList = map.get(depotGoodsFeignDto.getGoodsCode());
                mergeGoodsOrderDetail(depotGoodsFeignDto, goodsOrderInfoList, result);// 整合已订货商品
            }else{
                GoodsOrderDetailResponse goodsOrderDetailResponse = new GoodsOrderDetailResponse();
                goodsOrderDetailResponse.setGoodsCode(depotGoodsFeignDto.getGoodsCode());
                goodsOrderDetailResponse.setGoodsName(depotGoodsFeignDto.getGoodsName());
                goodsOrderDetailResponse.setGoodsStockNum(depotGoodsFeignDto.getStockQty());
                goodsOrderDetailResponse.setStoreGoodsSumNum(depotGoodsFeignDto.getQty());
                goodsOrderDetailResponse.setRecommendGoodsNum(calcuRecommendGoodsNum(depotGoodsFeignDto.getQty(),
                        depotGoodsFeignDto.getStockQty(), null));
                result.add(goodsOrderDetailResponse);// 默认仓库商品放在第一的位置
            }
        }

        Map<String, List<DepotGoodsFeignDto>> depotGoodsMap = depotGoodsList.stream().collect(Collectors.groupingBy(DepotGoodsFeignDto::getGoodsCode));
        if(null != goodsOrders && goodsOrders.size() > 0){// 处理已订货商品，不在仓库商品信息中的数据
            for(GoodsOrderInfo goodsOrderInfo : goodsOrders){
                if(!depotGoodsMap.containsKey(goodsOrderInfo.getGoodsCode())){
                    GoodsOrderDetailResponse goodsOrderDetailResponse = new GoodsOrderDetailResponse();
                    BeanUtils.copyProperties(goodsOrderInfo, goodsOrderDetailResponse);
                    goodsOrderDetailResponse.setRecommendGoodsNum("0");
                    goodsOrderDetailResponse.setHasGoodsNum(goodsOrderInfo.getGoodsNum());
                    result.add(goodsOrderDetailResponse);
                }
            }
        }
        return result;
    }

    /**
     * 整合已订货商品,将已订货商品拼接到仓库商品后面
     * @param depotGoodsFeignDto 仓库商品信息
     * @param goodsOrderInfoList 订单商品信息
     * @param target 合并结果集
     */
    private void mergeGoodsOrderDetail(DepotGoodsFeignDto depotGoodsFeignDto,
                                       List<GoodsOrderInfo> goodsOrderInfoList,
                                       List<GoodsOrderDetailResponse> target){
        if(null == depotGoodsFeignDto || null == goodsOrderInfoList || 0 == goodsOrderInfoList.size() || null == target){
            return;
        }
        // 建议订货量
        String count = calcuRecommendGoodsNum(depotGoodsFeignDto.getQty(), depotGoodsFeignDto.getStockQty(), null);
        for(GoodsOrderInfo goodsOrderInfo : goodsOrderInfoList){
            GoodsOrderDetailResponse goodsOrderDetailResponse = new GoodsOrderDetailResponse();
            goodsOrderDetailResponse.setSuppCode(goodsOrderInfo.getSuppCode());
            goodsOrderDetailResponse.setSuppName(goodsOrderInfo.getSuppName());
            goodsOrderDetailResponse.setGoodsCode(goodsOrderInfo.getGoodsCode());
            goodsOrderDetailResponse.setGoodsName(goodsOrderInfo.getGoodsName());
            goodsOrderDetailResponse.setGoodsStockNum(depotGoodsFeignDto.getStockQty());
            goodsOrderDetailResponse.setStoreGoodsSumNum(depotGoodsFeignDto.getQty());
            goodsOrderDetailResponse.setPrice(goodsOrderInfo.getPrice());
            goodsOrderDetailResponse.setHasGoodsNum(goodsOrderInfo.getGoodsNum());
            // 计算建议订货数量
            count = calcuRecommendGoodsNum(count, null, goodsOrderInfo.getGoodsNum());
            goodsOrderDetailResponse.setRecommendGoodsNum(count);
            target.add(goodsOrderDetailResponse);
        }
    }

    /**
     * 获取供应商商品,仓库商品,已订货商品三者交集商品数据
     * @param suppGoodsList 供应商提供的所有商品信息
     * @param orderGoodsList 已经订货的商品信息
     * @param orderGoodsList 仓库商品信息
     * @return
     */
    private List<GoodsOrderInfoResponse> getValidGoodsInfo(List<SupplierGoodsFeignDto> suppGoodsList,
                                                           List<GoodsOrderInfo> orderGoodsList,
                                                           List<DepotGoodsFeignDto> depotGoodsDtoList) throws Exception {
        List<GoodsOrderInfoResponse> result = new ArrayList<>();
        List<GoodsOrderInfoResponse> list = getMixGoods(orderGoodsList, depotGoodsDtoList);// 已经订货的商品和仓库商品整合
        if(null != list && list.size() > 0){
            Map<String, List<GoodsOrderInfoResponse>> map = list.stream().collect(Collectors.groupingBy(GoodsOrderInfoResponse::getGoodsCode));
            for(SupplierGoodsFeignDto supplierGoodsFeignDto : suppGoodsList){
                if(null != map && map.size() > 0 && map.containsKey(supplierGoodsFeignDto.getItemcode())){
                    GoodsOrderInfoResponse goodsOrderInfoResponse = map.get(supplierGoodsFeignDto.getItemcode()).get(0);
                    goodsOrderInfoResponse.setPrice("");
                    result.add(goodsOrderInfoResponse);
                }
            }
        }
        return result;
    }

    /**
     * 已经订货的商品信息和仓库商品信息整合
     * @param orderGoodsList 已经订货的商品信息
     * @param depotGoodsDtoList 仓库商品信息
     * @return
     * @throws Exception
     */
    private List<GoodsOrderInfoResponse> getMixGoods(List<GoodsOrderInfo> orderGoodsList, List<DepotGoodsFeignDto> depotGoodsDtoList) throws Exception {
        List<GoodsOrderInfoResponse> result = new ArrayList<>();
        Map<String, List<GoodsOrderInfo>> orderGoodsMap = null;
        Map<String, List<DepotGoodsFeignDto>> depotGoodsMap = null;
        if(null != depotGoodsDtoList && depotGoodsDtoList.size() > 0){// 以depotGoodsDtoList为维度整合商品信息
            depotGoodsMap = depotGoodsDtoList.stream().collect(Collectors.groupingBy(DepotGoodsFeignDto::getGoodsCode));
            if(null != orderGoodsList && orderGoodsList.size() > 0){
                orderGoodsMap = orderGoodsList.stream().collect(Collectors.groupingBy(GoodsOrderInfo::getGoodsCode));
                for(GoodsOrderInfo orderGoods : orderGoodsList){// 整合已订货的商品信息，不在仓库商品信息中的数据
                    GoodsOrderInfoResponse goodsOrderInfoResponse = new GoodsOrderInfoResponse();
                    if(null != depotGoodsMap && depotGoodsMap.size() > 0 && !depotGoodsMap.containsKey(orderGoods.getGoodsCode())){
                        goodsOrderInfoResponse.setGoodsName(orderGoods.getGoodsName());
                        goodsOrderInfoResponse.setGoodsCode(orderGoods.getGoodsCode());
                        goodsOrderInfoResponse.setGoodsNum("0");
                        result.add(goodsOrderInfoResponse);
                    }
                }
            }
            for(DepotGoodsFeignDto depotGoodsFeignDto : depotGoodsDtoList){// 订货的商品信息和仓库商品信息整合
                GoodsOrderInfoResponse goodsOrderInfoResponse = new GoodsOrderInfoResponse();
                if(null != orderGoodsMap && orderGoodsMap.size() > 0 && orderGoodsMap.containsKey(depotGoodsFeignDto.getGoodsCode())){
                    goodsOrderInfoResponse = sumGoodsOrderInfos(depotGoodsFeignDto, orderGoodsMap.get(depotGoodsFeignDto.getGoodsCode()));
                }else{
                    goodsOrderInfoResponse.setGoodsName(depotGoodsFeignDto.getGoodsName());
                    goodsOrderInfoResponse.setGoodsCode(depotGoodsFeignDto.getGoodsCode());
                    goodsOrderInfoResponse.setGoodsNum(calcuRecommendGoodsNum(depotGoodsFeignDto.getQty(), depotGoodsFeignDto.getStockQty(), null));
                }
                result.add(goodsOrderInfoResponse);
            }
        }else{
            if(null != orderGoodsList && orderGoodsList.size() > 0){// 仓库商品信息为空时，直接返回已经订货的商品信息
                for(GoodsOrderInfo goods : orderGoodsList){//
                    GoodsOrderInfoResponse goodsOrderInfoResponse = new GoodsOrderInfoResponse();
                    goodsOrderInfoResponse.setGoodsName(goods.getGoodsName());
                    goodsOrderInfoResponse.setGoodsCode(goods.getGoodsCode());
                    goodsOrderInfoResponse.setGoodsName("0");
                    result.add(goodsOrderInfoResponse);
                }
            }
        }
        return result;
    }

    /**
     * 计算商品数量并且返回商品信息(商品数量 = 合计商品数量 - 库存商品数量 - 所有已订货商品数量)
     * @param depotGoodsFeignDto 仓库商品
     * @param orders 已订货商品
     * @return
     */
    private GoodsOrderInfoResponse sumGoodsOrderInfos(DepotGoodsFeignDto depotGoodsFeignDto, List<GoodsOrderInfo> orders){
        GoodsOrderInfoResponse goodsOrderInfoResponse = new GoodsOrderInfoResponse();
        String count = calcuRecommendGoodsNum(depotGoodsFeignDto.getQty(), depotGoodsFeignDto.getStockQty(), null);
        for(GoodsOrderInfo goodsOrderInfo : orders){
            count = calcuRecommendGoodsNum(count, null, goodsOrderInfo.getGoodsNum());
        }
        goodsOrderInfoResponse.setGoodsNum(count);
        goodsOrderInfoResponse.setGoodsName(depotGoodsFeignDto.getGoodsName());
        goodsOrderInfoResponse.setGoodsCode(depotGoodsFeignDto.getGoodsCode());
        return goodsOrderInfoResponse;
    }

    /**
     * 订单保存校验有效性
     * @param supplierCode 供应商编号
     * @param depotCode 仓库编号
     * @param personId 工号
     * @param goodsList 商品列表
     */
    private void saveOrderValid(String supplierCode, String depotCode, String personId, List<GoodsOrderInfoRequest> goodsList){
        // 供应商下所有的商品信息
        List<SupplierGoodsFeignDto> goods = orderFeignService.selectGoodsByCondition(supplierCode);
        List<DepotGoodsFeignDto> depots = depotFeignService.getSupplierGoods(personId, DateUtil.format(new Date(), "yyyyMMdd"));
        if(null == goods || 0 == goods.size()){
            throw new BussinessException("查询供应商商品信息失败");
        }
        Map<String, List<SupplierGoodsFeignDto>> goodsMap = goods.stream().collect(Collectors.groupingBy(SupplierGoodsFeignDto::getItemcode));
        Map<String, List<DepotGoodsFeignDto>> depotsMap = null;
        if(null != depots && depots.size() > 0){
            depotsMap = depots.stream().collect(Collectors.groupingBy(DepotGoodsFeignDto::getGoodsCode));
        }
        for(GoodsOrderInfoRequest goodsOrder : goodsList){
            String goodsCode = goodsOrder.getGoodsCode();
            if(!goodsMap.containsKey(goodsCode)){ // 判断商品是否属于供应商
                throw new BussinessException("商品[" + goodsOrder.getGoodsName() + "|" + goodsOrder.getGoodsCode() + "]"
                        + ",不是该供应商[" + supplierCode + "]" + "下的商品");
            }

            if(compareGoodsPrice(goodsOrder.getGoodsNum(), "0") <= 0){ // 判断商品是否属于供应商
                throw new BussinessException("商品[" + goodsOrder.getGoodsName() + "|" + goodsOrder.getGoodsCode() + "]"
                        + ",商品数量不能为零");
            }
            // 判断商品单价是否小于0
            if(compareGoodsPrice(goodsOrder.getPrice(), "0") <= 0){
                throw new BussinessException("商品[" + goodsOrder.getGoodsName() + "|" + goodsOrder.getGoodsCode() + "]"
                        + "必须为大于0的数字");
            }
            SupplierFeignDto supplierFeignDto = orderFeignService.selectSuppOne(goodsCode, supplierCode, depotCode);
            if(null == supplierFeignDto){
                throw new BussinessException("供应商不可订货");
            }
            List<SupplierGoodsFeignDto> itemList = supplierFeignDto.getItemList();
            if(null == itemList || 0 == itemList.size()){
                throw new BussinessException("商品不可订货");
            }
            // 判断商品单价是否大于协议进价
            if(compareGoodsPrice(goodsOrder.getPrice(), itemList.get(0).getUnitCostEclVat()) > 0){
                throw new BussinessException("商品[" + goodsOrder.getGoodsName() + "|" + goodsOrder.getGoodsCode() + "]"
                        + "必须小于协议进价");
            }
        }
    }

    /**
     * 比较商品单价
     * @param price 单价
     * @param comparePrice 被比较单价
     * @return 0-相等，1-大于，-1小于
     */
    private static int compareGoodsPrice(String price, String comparePrice){
        BigDecimal priceBig = new BigDecimal(StringUtils.isNotEmpty(price) ? price : "0");
        BigDecimal comparePriceBig = new BigDecimal(StringUtils.isNotEmpty(comparePrice) ? comparePrice : "0");
        return priceBig.compareTo(comparePriceBig);
    }

    /**
     * 计算建议订货数量
     * @param qty 商品需订货数量
     * @param stockQty 库存数量
     * @param hasQty 已订货数量
     * @return
     */
    private String calcuRecommendGoodsNum(String qty,
                                          String stockQty,
                                          String hasQty){
        BigDecimal qtyBg = new BigDecimal(StringUtils.isEmpty(qty) ? "0" : qty);
        BigDecimal stockQtyBg = new BigDecimal(StringUtils.isEmpty(stockQty) ? "0" : stockQty);
        BigDecimal hasQtyBg = new BigDecimal(StringUtils.isEmpty(hasQty) ? "0" : hasQty);
        BigDecimal count = qtyBg.subtract(stockQtyBg).subtract(hasQtyBg);
        if(count.compareTo(BigDecimal.ZERO) < 0){
            count = BigDecimal.ZERO;
        }
        return count.toString();
    }

    /**
     * 校验保存参数
     * @param param
     */
    private void saveOrderCheck(SupplierOrderInfoRequest param){
        String suppCode = param.getSuppCode();
        String suppName = param.getSuppName();
        String depotCode = param.getDepotCode();
        String depotName = param.getDepotName();
        String purchaseTime = param.getPurchaseTime();
        String orderPersonId = param.getOrderPersonId();
        String orderPersonName = param.getOrderPersonName();
        List<GoodsOrderInfoRequest> goodsList = param.getGoodsList();
        if(StringUtils.isEmpty(suppCode)){
            throw new BussinessException("供应商编号[suppCode]不能为空");
        }
        if(StringUtils.isEmpty(suppName)){
            throw new BussinessException("供应商名称[suppName]不能为空");
        }
        if(StringUtils.isEmpty(depotCode)){
            throw new BussinessException("仓库编号[depotCode]不能为空");
        }
        if(StringUtils.isEmpty(depotName)){
            throw new BussinessException("仓库名称[depotName]不能为空");
        }
        if(StringUtils.isEmpty(purchaseTime)){
            throw new BussinessException("要货时间[purchaseTime]不能为空");
        }
        if(StringUtils.isEmpty(orderPersonId)){
            throw new BussinessException("工号[orderPersonId]不能为空");
        }
        if(StringUtils.isEmpty(orderPersonName)){
            throw new BussinessException("名称[orderPersonName]不能为空");
        }
        if(null == goodsList || 0 == goodsList.size()){
            throw new BussinessException("商品信息不能为空");
        }else{
            for(GoodsOrderInfoRequest request : goodsList){
                String goodsCode = request.getGoodsCode();
                String goodsName = request.getGoodsName();
                String price = request.getPrice();
                String goodsNum = request.getGoodsNum();
                if(StringUtils.isEmpty(goodsCode)){
                    throw new BussinessException("商品编号[goodsCode]不能为空");
                }
                if(StringUtils.isEmpty(goodsName)){
                    throw new BussinessException("商品名称[goodsName]不能为空");
                }

                if(StringUtils.isEmpty(price)){
                    throw new BussinessException("单价[price]不能为空");
                }else{
                    if(!StringUtils.isPosNumber(price)){
                        throw new BussinessException("商品[" + goodsName + "]单价必须为大于零的数字");
                    }
                }

                if(StringUtils.isEmpty(goodsNum)){
                    throw new BussinessException("商品编号[商品数量]不能为空");
                }else{
                    if(!StringUtils.isPosInteger(goodsNum)){
                        throw new BussinessException("商品{" + goodsName + "}单价必须为正整数");
                    }
                }
            }
        }
        param.setSuppCode(suppCode.trim());
        param.setSuppName(suppName.trim());
        param.setDepotCode(depotCode.trim());
        param.setDepotName(depotName.trim());
        param.setPurchaseTime(purchaseTime.trim());
        param.setOrderPersonId(orderPersonId.trim());
        param.setOrderPersonName(orderPersonName.trim());
    }

    public static void main(String[] args) throws URISyntaxException {
        System.out.println(System.getProperty("user.dir"));
    }
}
