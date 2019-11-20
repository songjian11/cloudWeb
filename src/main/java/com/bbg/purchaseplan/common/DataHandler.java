package com.bbg.purchaseplan.common;

import com.bbg.purchaseplan.constant.ResultCodeConstant;
import com.bbg.purchaseplan.exception.BussinessException;
import com.bbg.purchaseplan.utils.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;

public class DataHandler {
    /**
     * 封装app接口返回json结构
     *
     * @param code
     *            返回代码[0:正常,-1:错误,... 其它自定义]
     * @param msg
     *            提示信息
     * @param data
     *            响应数据
     * @return
     */
    public static ResponseResult jsonResult(String code, String msg, Object data) {
        return new ResponseResult(code, msg, data);
    }

    public static ResponseResult jsonResultByPage(String code, String msg, PageLayuiInfo data) {
        return new ResponseResult(code, msg, data.getResult(), data.getTotal(), data.getPage(), data.getPageSize());
    }

    public static ResponseResult exceptionJsonResult(String msg, Throwable e) {
        return errorJsonResult(ResultCodeConstant.CODE_9999, msg, e);
    }

    public static ResponseResult errorJsonResult(String code, String msg, Throwable e) {
        if (e instanceof EmptyResultDataAccessException) {
            return jsonNoDataResult("未找到相应数据");
        }
        if (e instanceof BussinessException) {
            msg = e.getMessage();
        }
        if (StringUtils.isEmpty(msg)) {
            msg = e.getMessage();
        }
        return new ResponseResult(code, msg);
    }

    public static ResponseResult jsonResult(Object data) {
        if (data instanceof PageLayuiInfo) {
            return jsonResultByPage(ResultCodeConstant.CODE_0000, "success", (PageLayuiInfo) data);
        }
        return jsonResult(ResultCodeConstant.CODE_0000, "success", data);
    }

    public static ResponseResult jsonNoDataResult(String msg) {
        return jsonResult(ResultCodeConstant.CODE_0000, msg, null);
    }

    public static ResponseResult jsonErrorResult(String msg) {
        return jsonResult(ResultCodeConstant.CODE_9999, msg, null);
    }
}
