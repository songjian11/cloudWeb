package com.bbg.purchaseplan.bo.request.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "TestRequest", description = "測試用例")
@Data
public class TestRequest {
    @ApiModelProperty(value = "名稱", required = true)
    private String name;
    @ApiModelProperty(value = "年齡", required = true)
    private Integer age;
    @ApiModelProperty(value = "性別", required = true)
    private Byte sex;
    @ApiModelProperty(value = "數組", required = true)
    private List<TestDemoRequest> arr;

   /* @Override
    public String toString() {
        return "TestRequest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", arr=" + arr +
                '}';
    }*/
}
