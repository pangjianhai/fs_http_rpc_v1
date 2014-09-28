package com.qooc.httprpc.netvalue;

/**
 * @ClassName: RequestConst
 * @Description: 请求常量
 * @author pangjianhai
 * @date 2014-9-27 上午9:30:09
 * 
 */
public class RequestConst {

	/**
	 * 请求要求的返回值类型
	 */
	public static final int RETURN_FORMAT_XML = 1;
	public static final int RETURN_FORMAT_JSON = 2;

	/**
	 * 服务端返回结果状态吗、1：成功、2：失败
	 */
	public static final int RESPONSE_SUCCESS = 1;
	public static final int RESPONSE_FAILURE = 0;

	/**
	 * 
	 * 负载均衡策略
	 * 
	 */
	public interface LoadbalanceStrategy {
		/**
		 * 随机
		 */
		public static final Integer LB_RANDOM = 1;
		/**
		 * 轮询
		 */
		public static final Integer LB_POLL = 2;
		/**
		 * 随机加权
		 */
		public static final Integer LB_RANDOM_WEIGHT = 3;

		/**
		 * 加权轮询
		 */
		public static final Integer LB_POLLWEIGHT = 4;
	}
	
	
}
