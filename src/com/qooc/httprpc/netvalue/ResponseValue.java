package com.qooc.httprpc.netvalue;

import java.io.Serializable;

/**
 * @ClassName: ResponseValue
 * @Description: 提供者执行完毕后返回的结果
 * @author pangjianhai
 * @date 2014-9-27 上午10:37:24
 * 
 */
public class ResponseValue implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4157353432699112797L;

	/**
	 * 返回结果的类型
	 */
	private int response_format;

	/**
	 * 结果状态
	 */
	private int status;

	/**
	 * 返回结果的字符串
	 */
	private Object responseObj;

	public int getResponse_format() {
		return response_format;
	}

	public void setResponse_format(int response_format) {
		this.response_format = response_format;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getResponseObj() {
		return responseObj;
	}

	public void setResponseObj(Object responseObj) {
		this.responseObj = responseObj;
	}

}
