package com.newtouch.core.errorcode;


public class ErrorCode {

	/**
	 * 根据错误代码，查找提示信息
	 * 
	 * @param msgCode
	 *            错误代码
	 * @return 错误信息
	 */
	public static String getErrorMsg(String msgCode) {
		return ErrorCodeInfo.getInstance().getErrorMsg(msgCode);
	}

}
