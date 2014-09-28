package com.qooc.httprpc.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.qooc.httprpc.clientside.HttpThreadPool;
import com.qooc.httprpc.clientside.TaskForRequest;
import com.qooc.httprpc.netvalue.RequestMethodArgs;
import com.qooc.httprpc.netvalue.RequestParam;
import com.qoocc.httprpc.example.IBookService;
import com.qoocc.httprpc.example.User;

public class FormalTest2 {

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
		RequestMethodArgs a = new RequestMethodArgs();
		User user = new User();
		user.setAge(11);
		user.setBirthday(new Date());
		user.setName("pjh");
		List lst = new ArrayList();
		lst.add(user);
		a.setArgs(lst);
		return a;
	}

	public static RequestParam td() throws Exception, Exception {
		RequestParam r = new RequestParam();
		r.setRequest_format(2);// 请求参数序列化方式
		r.setResponse_format(2);// 返回内容的序列化方式
		r.setBeanName("bookServiceImpl");// springbean的名称
		r.setInterfaceOrClassName(IBookService.class.getName());// 接口名、类名
		r.setMethodName("getAllBooksInDBByUser");// 服务方法名
		return r;
	}
}
