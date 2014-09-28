package com.qooc.httprpc.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test1 {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FutureTask<String> future = new FutureTask<String>(
				new Callable<String>() {// 使用Callable接口作为构造参数
					public String call() {
						return "3";
						// 真正的任务在这里执行，这里的返回值类型为String，可以为任意类型
					}
				});
		executor.execute(future);
		// 在这里可以做别的任何事情
		try {
			Object result = future.get(5000, TimeUnit.MILLISECONDS); // 取得结果，同时设置超时执行时间为5秒。同样可以用future.get()，不设置执行超时时间取得结果
			System.out.println(result);
		} catch (InterruptedException e) {
			future.cancel(true);
		} catch (ExecutionException e) {
			future.cancel(true);
		} catch (TimeoutException e) {
			future.cancel(true);
		} finally {
			//executor.shutdown();
		}
	}
}
