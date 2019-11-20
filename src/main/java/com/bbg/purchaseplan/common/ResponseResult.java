package com.bbg.purchaseplan.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 返回对象封装
 * @author
 * @data
 */
@Data
@ApiModel(description= "返回对象")
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 3997124446365032582L;
    @ApiModelProperty(value = "结果码")
    private String code;//结果码
    @ApiModelProperty(value = "返回信息")
    private String msg;//结果信息
    @ApiModelProperty(value = "返回对象")
    private T object;
    @ApiModelProperty(value = "总记录数")
    private long total;
    @ApiModelProperty(value = "当前页")
    private int page;
    @ApiModelProperty(value = "每页数据数")
    private int pageSize;
    @ApiModelProperty(value = "总页数")
    private long totalPage;

    public ResponseResult(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(String code, String msg, T object) {
        super();
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

    public ResponseResult(String code, String msg, T object, int total) {
        this.code = code;
        this.msg = msg;
        this.object = object;
        this.total = total;
    }

    public ResponseResult(String code, String msg, T object, long total, int page, int pageSize) {
        super();
        this.code = code;
        this.msg = msg;
        this.object = object;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPage = (total + pageSize - 1) / pageSize;
    }
}
