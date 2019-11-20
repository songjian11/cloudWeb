/**
 * 
 */
package com.bbg.purchaseplan.exception;

/**
 * 异常辅助类
 *
 * @author 谭楷凡
 * @date 2019年8月14日
 *
 */
public class ExceptionUtil {
	public static void wapperException(Throwable e) {
		if (e == null) {
			return;
		}
		BaseException ex = new BaseException(e);
		ex.setStackTrace(e.getStackTrace());
		throw ex;
	}

	public static void wapperException(String errorMsg) {
		if (errorMsg == null) {
			return;
		}
		BaseException ex = new BaseException(errorMsg);
		throw ex;
	}

	public static void wapperBussinessException(String errorMsg) {
		if (errorMsg == null) {
			return;
		}
		BussinessException ex = new BussinessException(errorMsg);
		throw ex;
	}
}
