package com.bbg.purchaseplan.controller;

import com.bbg.purchaseplan.bo.response.GoodsOrderInfoResponse;
import com.bbg.purchaseplan.bo.request.SupplierOrderInfoRequest;
import com.bbg.purchaseplan.bo.response.DepotOrderDetailResponse;
import com.bbg.purchaseplan.bo.response.OrderSumInfoResponse;
import com.bbg.purchaseplan.common.DataHandler;
import com.bbg.purchaseplan.common.ResponseResult;
import com.bbg.purchaseplan.dto.SupplierFeignDto;
import com.bbg.purchaseplan.exception.BussinessException;
import com.bbg.purchaseplan.feignService.Impl.OrderFeignServiceImpl;
import com.bbg.purchaseplan.service.OrderService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * 采购计划-订单信息查询和订货
 * songjian
 * 20191107
 */
@Slf4j
@Api(value = "order", tags = { "采购计划-订单信息查询和订货" })
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderFeignServiceImpl orderFeignService;

    /**
     *查询订单汇总信息
     * @return
     * songjian
     */
    @ApiOperation(value = "根据工号查询订单汇总信息", notes = "根据工号查询订单汇总信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "personId", value = "工号", required = true, dataType = "String")
    })
    @GetMapping("/queryOrderSumInfo")
    public ResponseResult<OrderSumInfoResponse> queryOrderSumInfo(HttpServletRequest request, String personId) throws IOException {
        ServletInputStream in = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader read = new BufferedReader(isr);
        read.readLine();

        ResponseResult<OrderSumInfoResponse> result = null;
        try {
            OrderSumInfoResponse orderSumInfoResponse = orderService.queryOrderSumInfoByPersonId(personId);
            result = DataHandler.jsonResult(orderSumInfoResponse);
        }catch (BussinessException e){
            result = DataHandler.jsonErrorResult(e.getMessage());
        }catch (Exception e){
            log.error("\n【采购计划-根据工号查询订单汇总信息异常】:\n【请求地址】:{}\n【请求参数】:{}\n【异常】:", "order/queryOrderSumInfo", personId, e);
            result = DataHandler.jsonErrorResult("系统异常,请联系系统管理人员");
        }
        return result;
    }

    /**
     * 查询仓库订单明细
     * @return
     * songjian
     */
    @ApiOperation(value = "根据工号查询仓库商品订单明细", notes = "根据工号查询仓库商品订单明细")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "personId", value = "工号", required = true, dataType = "String")
    })
    @GetMapping("/queryDepotOrderDetail")
    public ResponseResult<DepotOrderDetailResponse> queryDepotOrderDetail(String personId){
        ResponseResult<DepotOrderDetailResponse> result = null;
        try {
            DepotOrderDetailResponse response = orderService.queryDepotOrderDetailByPersonId(personId);
            result = DataHandler.jsonResult(response);
        }catch (BussinessException e){
            result = DataHandler.jsonErrorResult(e.getMessage());
        }catch (Exception e){
            log.error("\n【采购计划-根据工号查询仓库商品订单明细异常】:\n【请求地址】:{}\n【请求参数】:{}\n【异常】:", "order/queryDepotOrderDetail", personId, e);
            result = DataHandler.jsonErrorResult("系统异常,请联系系统管理人员");
        }
        return result;
    }

    /**
     * 根据供应商编号,仓库编号和要货时间查询商品列表
     * @param supplierCode
     * @param depotCode
     * @param purchaseTime
     * @return
     */
    @ApiOperation(value = "根据供应商编号,仓库编号和要货时间查询商品列表", notes = "根据供应商编号,仓库编号和要货时间查询商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "personId", value = "工号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "supplierCode", value = "供应商编号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "depotCode", value = "仓库编号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "purchaseTime", value = "要货时间(格式yyyyMMdd)", required = true, dataType = "String")
    })
    @GetMapping("/queryGoodsInfo")
    public ResponseResult<List<GoodsOrderInfoResponse>> queryGoodsInfo(String personId, String supplierCode, String depotCode, String purchaseTime){
        ResponseResult<List<GoodsOrderInfoResponse>> result = null;
        try {
            List<GoodsOrderInfoResponse> list = orderService.queryGoodsInfo(personId, supplierCode, depotCode, purchaseTime);
            result = DataHandler.jsonResult(list);
        }catch (BussinessException e){
            result = DataHandler.jsonErrorResult(e.getMessage());
        }catch (Exception e){
            log.error("\n【采购计划-根据供应商编号,仓库编号和要货时间查询商品列表异常】:\n【请求地址】:{}\n【请求参数】:{},{},{},{}\n【异常】:", "order/queryGoodsInfo",
                    personId, supplierCode, depotCode, purchaseTime, e);
            result = DataHandler.jsonErrorResult("系统异常,请联系系统管理人员");
        }
        return result;
    }

    /**
     * 保存供应商下单信息
     * @param param
     * @return
     * songjian
     */
    @ApiOperation(value = "保存供应商下单信息", notes = "保存供应商下单信息")
    @PostMapping("/saveOrder")
    public ResponseResult saveOrder(@RequestBody @ApiParam(value = "供应商下单信息", required = true, name = "param")
                                            SupplierOrderInfoRequest param){
        ResponseResult result = null;
        try {
            orderService.saveOrder(param);
            result = DataHandler.jsonNoDataResult("保存成功");
        }catch (BussinessException e){
            result = DataHandler.jsonErrorResult(e.getMessage());
        }catch (Exception e){
            log.error("\n【采购计划-保存供应商下单信息异常】:\n【请求地址】:{}\n【请求参数】:{}\n【异常】:", "order/saveOrder", param.toString(), e);
            result = DataHandler.jsonErrorResult("系统异常,请联系系统管理人员");
        }
        return result;
    }

    /**
     * 单价是否合法
     * @return
     */
    @ApiOperation(value = "判断单价是否合法")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "depotCode", value = "仓库编号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodsCode", value = "商品编码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "supplierCode", value = "供应商编码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "price", value = "单价", required = true, dataType = "String")
    })
    @GetMapping("/isLegalPrice")
    public ResponseResult<String> isLegalPrice(String depotCode, String goodsCode, String supplierCode, String price){
        ResponseResult<String> result = null;
        try {
            result = DataHandler.jsonResult(orderService.isLegalPriceBygoodsCodeAndsupplierCodeAndPrice(depotCode, goodsCode, supplierCode, price));
        }catch (BussinessException e){
            result = DataHandler.jsonErrorResult(e.getMessage());
        }catch (Exception e){
            log.error("\n【采购计划-判断单价是否合法异常】:\n【请求地址】:{}\n【请求参数】:{},{},{}\n【异常】:", "order/isLegalPrice", goodsCode, supplierCode, price, e);
            result = DataHandler.jsonErrorResult("系统异常,请联系系统管理人员");
        }
        return result;
    }

    @ApiOperation(value = "判断供应商是否合法")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "depotCode", value = "仓库编号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodsCode", value = "商品编码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "supplierCode", value = "供应商编码", required = true, dataType = "String")
    })
    @GetMapping("/isLegalSupplier")
    public ResponseResult<Boolean> isLegalSupplier(String depotCode, String goodsCode, String supplierCode){
        ResponseResult<Boolean> result = null;
        try {
            SupplierFeignDto supplierFeignDto = orderFeignService.selectSuppOne(goodsCode, supplierCode, depotCode);
            if(null == supplierFeignDto){
                result = DataHandler.jsonResult(false);
            }else{
                result = DataHandler.jsonResult(true);
            }
        }catch (BussinessException e){
            result = DataHandler.jsonErrorResult(e.getMessage());
        }catch (Exception e){
            log.error("\n【采购计划-判断单价是否合法异常】:\n【请求地址】:{}\n【请求参数】:{},{},{}\n【异常】:", "order/isLegalPrice", depotCode, goodsCode, supplierCode, e);
            result = DataHandler.jsonErrorResult("系统异常,请联系系统管理人员");
        }
        return result;
    }

    /**
     * 同步订单信息
     * @return
     */
    @GetMapping("/transferOrderData")
    public ResponseResult<String> transferOrderData() {
        ResponseResult<String> result = null;
        try {
            orderService.transferOrderData();
            DataHandler.jsonErrorResult("同步成功");
        }catch (BussinessException e){
            DataHandler.jsonErrorResult(e.getMessage());
        }catch (Exception e){
            log.error("\n【采购计划-同步订单信息异常】:\n【请求地址】:{}\n【异常】:", "order/transferOrderData", e);
            DataHandler.jsonErrorResult("系统异常,请联系系统管理人员");
        }
        return DataHandler.jsonResult("同步成功");
    }
}
