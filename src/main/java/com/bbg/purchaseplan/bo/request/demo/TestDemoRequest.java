package com.bbg.purchaseplan.bo.request.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "TestDemoRequest", description = "測試用例")
@Data
public class TestDemoRequest {
    @ApiModelProperty(value = "名稱", required = true)
    private String name;
}
