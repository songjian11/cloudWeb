/**
 * 
 */
package com.bbg.purchaseplan.exception;

/**
 * 基础异常
 *
 * @author 谭楷凡
 * @date 2019年8月14日
 *
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
