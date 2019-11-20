package com.bbg.purchaseplan.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据运算工具
 * 
 * @author songj
 * @time 2019年11月16日
 */
public class OperationUtil {
	/**
	 * 单个数值转换
	 * 
	 * @param value
	 * @param unit
	 * @return
	 */
	public static Map<String, Object> doConvert(BigDecimal value, String unit) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (value.compareTo(new BigDecimal(100000000)) == 0 || value.compareTo(new BigDecimal(100000000)) == 1) {// 达到100000000转成亿
			unit = "亿" + unit;
			value = value.divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP);
		} else if (value.compareTo(new BigDecimal(10000)) == 0 || value.compareTo(new BigDecimal(10000)) == 1) {// 达到10000转成万
			unit = "万" + unit;
			value = value.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
		}
		result.put("value", value);
		result.put("unit", unit);
		return result;
	}

	/**
	 * 除法运算处理
	 * 
	 * @param target
	 * @param divisor
	 * @return
	 */
	public static BigDecimal divideHandler(BigDecimal target, BigDecimal divisor) {
		BigDecimal result = BigDecimal.ZERO;
		if (target == null) {
			return result;
		}
		if (divisor.compareTo(BigDecimal.ZERO) != 0) {
			result = target.divide(divisor, 2, BigDecimal.ROUND_HALF_UP);
		}
		return result;
	}

	/**
	 * 非负数处理
	 * 
	 * @author wells
	 * @param data
	 * @return
	 * @time 2018年12月24日
	 */
	public static BigDecimal nonNegativeHandler(BigDecimal data) {
		if (data.compareTo(BigDecimal.ZERO) == -1) {
			return BigDecimal.ZERO;
		}
		return data.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
