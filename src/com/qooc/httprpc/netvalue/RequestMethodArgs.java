package com.qooc.httprpc.netvalue;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RequestMethodArgs
 * @Description: 远程调用的方法所需参数
 * @author pangjianhai
 * @date 2014-9-26 下午4:01:54
 * 
 */
public class RequestMethodArgs {
	/**
	 * 参数列表（注：只支持简单数据类型和string的引用数据类型）
	 */
	private List args;

	public List getArgs() {
		return args;
	}

	public void setArgs(List args) {
		this.args = args;
	}
	

}
