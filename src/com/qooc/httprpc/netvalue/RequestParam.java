package com.qooc.httprpc.netvalue;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: RequestParam
 * @Description: 请求消息
 * @author pangjianhai
 * @date 2014-9-27 上午9:27:07
 * 
 */
public class RequestParam implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2694858444699200313L;
	/**
	 * sping托管bean名字
	 */
	private String beanName;
	/**
	 * 接口名字
	 */
	private String interfaceOrClassName;

	/**
	 * 方法名
	 */
	private String methodName;

	/**
	 * 请求参数的类型，因为提供者需要对参数进行反序列化
	 */
	private int request_format;
	/**
	 * 所需返回值类型，因为消费者需要对返回结果反序列化
	 */
	private int response_format;

	public RequestParam() {
		super();
	}

	public RequestParam(String beanName, String interfaceOrClassName,
			String methodName, int request_format, int response_format) {
		super();
		this.beanName = beanName;
		this.interfaceOrClassName = interfaceOrClassName;
		this.methodName = methodName;
		this.request_format = request_format;
		this.response_format = response_format;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getInterfaceOrClassName() {
		return interfaceOrClassName;
	}

	public void setInterfaceOrClassName(String interfaceOrClassName) {
		this.interfaceOrClassName = interfaceOrClassName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getRequest_format() {
		return request_format;
	}

	public void setRequest_format(int request_format) {
		this.request_format = request_format;
	}

	public int getResponse_format() {
		return response_format;
	}

	public void setResponse_format(int response_format) {
		this.response_format = response_format;
	}

}
