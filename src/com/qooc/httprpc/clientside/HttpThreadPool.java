package com.qooc.httprpc.clientside;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName: HttpThreadPool
 * @Description: 请求线程池
 * @author pangjianhai
 * @date 2014-9-27 下午20:55:23
 * 
 */
public class HttpThreadPool {
	/**
	 * 锁
	 */
	private final static Object lock = new Object();
	private static HttpThreadPool pool = null;
	/**
	 * 线程数
	 */
	private int coreCPUNum;
	/**
	 * 线程池
	 */
	private ExecutorService executor;

	private HttpThreadPool() {
		coreCPUNum = Runtime.getRuntime().availableProcessors();
		executor = Executors.newFixedThreadPool(coreCPUNum);
	}

	/**
	 * @Description: 执行请求
	 * @author pangjianhai
	 * @date 2014-9-27 下午2:55:47
	 * @Future
	 * @param task
	 * @return
	 */
	public Future submitTask(TaskForRequest task) {
		return executor.submit(task);
	}

	/**
	 * 
	 * @Description: 获取唯一的线程池
	 * @author pangjianhai
	 * @date 2014-9-27 下午3:01:36
	 * @HttpThreadPool
	 * 
	 * @return
	 */
	public static HttpThreadPool getHttpThreadPool() {
		if (pool == null) {
			synchronized (lock) {
				if (pool == null) {
					pool = new HttpThreadPool();
				}
			}
		}

		return pool;
	}

	public ExecutorService getExecutor() {
		return executor;
	}
}
