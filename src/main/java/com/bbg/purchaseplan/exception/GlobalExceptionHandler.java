package com.bbg.purchaseplan.exception;

import com.bbg.purchaseplan.constant.ResultCodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * 统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Map<String, String> handleException(Exception e){
		logger.error("程序发生异常", e);
		Map<String, String> retMap = new HashMap<String, String>(10);
		retMap.put("resultCode", ResultCodeConstant.CODE_9999);
		retMap.put("resultMsg", "程序发生异常,请查看purchase_plane_logs日志");
		return retMap;
	}
}
