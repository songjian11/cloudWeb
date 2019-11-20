package com.bbg.purchaseplan.bo.request.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(value = "TestPageRequest", description = "測試用例")
@Data
public class TestPageRequest {
    @ApiModelProperty(value = "頁數", required = true)
    private int pageNum;
    @ApiModelProperty(value = "每頁記錄數", required = true)
    private int pageSize;
}
