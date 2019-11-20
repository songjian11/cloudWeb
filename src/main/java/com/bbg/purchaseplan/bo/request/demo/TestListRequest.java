package com.bbg.purchaseplan.bo.request.demo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel(value = "TestListRequest", description = "測試用例")
@Data
public class TestListRequest {
    private List<TestRequest> list;
}
