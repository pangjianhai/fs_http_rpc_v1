package com.qooc.httprpc.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.qooc.httprpc.clientside.HttpThreadPool;
import com.qooc.httprpc.clientside.TaskForRequest;
import com.qooc.httprpc.codec.JSON2Object;
import com.qooc.httprpc.netvalue.RequestMethodArgs;
import com.qooc.httprpc.netvalue.RequestParam;
import com.qoocc.httprpc.example.IBookService;

public class FormalTest {

	public static void gg() {

	}

	public static void main(String[] args) throws Exception {
		testRequest();
	}

	public static void testRequest() throws Exception {
		RequestParam rp = td();
		RequestMethodArgs ra = ra();
		TaskForRequest task = TaskForRequest.createRequestTask(rp, ra);
		Future<?> f = HttpThreadPool.getHttpThreadPool().submitTask(task);
		try {
			Object result = f.get();
			System.out.println("result:" + result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		HttpThreadPool.getHttpThreadPool().getExecutor().shutdown();

	}

	public static RequestMethodArgs ra() {
		return null;
	}

	public static RequestParam td() throws Exception, Exception {
		RequestParam r = new RequestParam();
		r.setRequest_format(2);// 请求参数序列化方式
		r.setResponse_format(2);// 返回内容的序列化方式
		r.setBeanName("bookServiceImpl");// springbean的名称
		r.setInterfaceOrClassName(IBookService.class.getName());// 接口名、类名
		r.setMethodName("getAllBooksInDB");// 服务方法名
		return r;
	}
}
