package com.qooc.httprpc.clientside;

import java.util.concurrent.Callable;

import com.qooc.httprpc.netvalue.RequestMethodArgs;
import com.qooc.httprpc.netvalue.RequestParam;

/**
 * @ClassName: TaskForRequest
 * @Description: 请求任务封装
 * @author pangjianhai
 * @date 2014-9-27 下午20:43:49
 * 
 */
public class TaskForRequest implements Callable {
	/**
	 * 请求地址
	 */
	private RequestParam requestParam;

	/**
	 * 执行参数
	 */
	private RequestMethodArgs args;

	public TaskForRequest() {
		super();
	}

	public TaskForRequest(RequestParam requestParam, RequestMethodArgs args) {
		super();
		this.requestParam = requestParam;
		this.args = args;
	}

	/**
	 * 需要异步执行的task
	 */
	@Override
	public Object call() throws Exception {
		return RquestUtil.reqeustServer(requestParam, args);
	}

	/**
	 * 
	 * @Description: 创建新任务
	 * @author pangjianhai
	 * @date 2014-9-27 下午3:09:12
	 * @TaskForRequest
	 * 
	 * @param url
	 * @return
	 */
	public static TaskForRequest createRequestTask(RequestParam requestParam,
			RequestMethodArgs args) {
		return new TaskForRequest(requestParam, args);
	}

}
