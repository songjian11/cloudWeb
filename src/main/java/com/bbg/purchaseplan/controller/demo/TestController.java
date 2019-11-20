package com.bbg.purchaseplan.controller.demo;

import com.alibaba.fastjson.JSONObject;
import com.bbg.purchaseplan.bo.request.demo.TestListRequest;
import com.bbg.purchaseplan.bo.request.demo.TestPageRequest;
import com.bbg.purchaseplan.bo.request.demo.TestRequest;
import com.bbg.purchaseplan.bo.response.demo.TestResponse;
import com.bbg.purchaseplan.common.DataHandler;
import com.bbg.purchaseplan.common.PageLayuiInfo;
import com.bbg.purchaseplan.common.ResponseResult;
import com.bbg.purchaseplan.dao.OrderDao;
import com.bbg.purchaseplan.dto.GoodsFeignDto;
import com.bbg.purchaseplan.dto.SupplierFeignDto;
import com.bbg.purchaseplan.dto.SupplierGoodsListFeignDto;
import com.bbg.purchaseplan.dto.SupplierItemmasterLocRefDto;
import com.bbg.purchaseplan.feignService.DepotFeignService;
import com.bbg.purchaseplan.feignService.GoodsFeignService;
import com.bbg.purchaseplan.feignService.Impl.GoodsFeignServiceImpl;
import com.bbg.purchaseplan.feignService.Impl.OrderFeignServiceImpl;
import com.bbg.purchaseplan.feignService.OrderFeignService;
import com.bbg.purchaseplan.service.demo.TestService;
import com.bbg.purchaseplan.utils.GenerateCodeUtil;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Api(value = "test", tags = { "demo用例" })
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;
//    @Autowired
//    TestFeignService testFeignService;
    @Autowired
    OrderFeignService orderFeignService;
    @Autowired
    DepotFeignService depotFeignService;
    @Autowired
    GoodsFeignService goodsFeignService1;
    @Autowired
    GoodsFeignServiceImpl goodsFeignService;
    @Autowired
    OrderFeignServiceImpl orderFeignService2;
    @SuppressWarnings("All")
    @Autowired
    OrderDao orderDao;

    @ApiOperation(value = "測試1", notes = "測試1")
    @GetMapping("/isok")
    public String test(HttpServletRequest request){
        return testService.test();
    }

    @ApiOperation(value = "測試2", notes = "測試2")
    @GetMapping("/query")
    public ResponseResult<List<TestResponse>> query() throws Exception {
        List<TestResponse> list = testService.query();
        ResponseResult responseResult = DataHandler.jsonResult(list);
        return responseResult;
    }

    @ApiOperation(value = "測試3", notes = "測試3")
    @PostMapping("/print")
    public ResponseResult<TestResponse> print(@RequestBody @ApiParam(value = "測試3", required = true, name = "param")
                                              TestRequest param){
        TestResponse response = new TestResponse();
        BeanUtils.copyProperties(param, response);
        ResponseResult responseResult = DataHandler.jsonResult(response);
        return responseResult;
    }

    @ApiOperation(value = "測試4", notes = "測試4")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "名稱", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "age", value = "年齡", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "sex", value = "性別(1-男，2-女)", required = true, dataType = "byte")})
    @GetMapping("/say")
    public ResponseResult<TestResponse> say(String name,int age,byte sex){
        TestResponse testResponse = new TestResponse();
        testResponse.setName(name);
        testResponse.setSex(sex);
        testResponse.setAge(age);
        ResponseResult responseResult = DataHandler.jsonResult(testResponse);
        return responseResult;
    }

    @ApiOperation(value = "測試5", notes = "測試5")
    @PostMapping("/page")
    public ResponseResult<List<TestPageRequest>> page(@RequestBody @ApiParam(value = "測試5", required = true, name = "param") TestPageRequest param) throws Exception {
        PageLayuiInfo pageLayuiInfo = testService.page(param);
        return DataHandler.jsonResult(pageLayuiInfo);
    }

    @ApiOperation(value = "測試6", notes = "測試6")
    @PostMapping("/list")
    public ResponseResult<List> list(@RequestBody @ApiParam(value = "測試6", required = true, name = "param") TestListRequest param){
        return DataHandler.jsonResult(param);
    }

    @ApiOperation(value = "測試7", notes = "測試7")
    @GetMapping("/code")
    public String code(){
        return orderDao.querySeq("order_seq");
    }

//    @GetMapping("/hystrix")
//    public String hystrixTest(){
//        return testFeignService.test();
//    }

    @GetMapping("/feignTest")
    public String feignTest() throws ExecutionException, InterruptedException {
//        JSONObject obj = orderFeignService.selectGoodsByCondition("24003223");
//        JSONObject str1 = orderFeignService.selectGoodsByItemAndLoc("100023074","118015");
//        JSONObject str2 = orderFeignService.selectSuppliersByItemAndSupplier("100023074","35003051");
//        Map<String, String> map1 = new HashMap<>();
//        map.put("supplier", "24003223");
//        JSONObject str3 = orderFeignService.getSupplierInfoo("24003223");
//        System.out.println("================selectGoodsByCondition:" + obj);
//        System.out.println("================selectGoodsByItemAndLoc:" + str1);
//        System.out.println("================selectSuppliersByItemAndSupplier:" + str2);
//        System.out.println("================getSupplierInfoo:" + str3);
//        map1.put("empNo", "");
//        map1.put("dept", "-1");
//        map1.put("createdate", "20180306");
//        JSONObject json1 = depotFeignService.getSupplierGoods(map1);
//        System.out.println("==================getSupplierGoods:"+json1.toJSONString());
//        Map<String, String> map2 = new HashMap<>();
//        map2.put("empNo","");
//        map2.put("dept", "-1");
//        map2.put("goodscode", "");
//        map2.put("createdate", "20180306");
//        map2.put("whld","110010");
//        JSONObject json2 = depotFeignService.getSupplierGoodView(map2);
//        System.out.println("==================getSupplierGoodView:"+json2.toJSONString());
        Map<String, String> map = new HashMap<>();
        map.put("itemCode", "100043366");
        map.put("supplier", "37000154");
        map.put("loc", "118010");
        map.put("storeOrderInd", "Y");
        JSONObject json = orderFeignService.selectSuppOne(map);
//        System.out.println("===========================:" + json.toJSONString());
//        SupplierFeignDto obj = orderFeignService2.selectSuppOne("100003284", "31000289", "118030");
        return json.toJSONString();
    }
}
