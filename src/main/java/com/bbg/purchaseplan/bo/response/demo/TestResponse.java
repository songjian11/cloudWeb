package com.bbg.purchaseplan.bo.response.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "TestRequest", description = "測試用例")
@Data
public class TestResponse {
    @ApiModelProperty(value = "名稱", required = true)
    private String name;
    @ApiModelProperty(value = "年齡", required = true)
    private Integer age;
    @ApiModelProperty(value = "性別", required = true)
    private Byte sex;
}
