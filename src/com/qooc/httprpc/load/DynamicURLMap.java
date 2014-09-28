package com.qooc.httprpc.load;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DynamicURLMap
 * @Description: 地址选择策略
 * @author pangjianhai
 * @date 2014-9-24 下午19:51:16
 * 
 */
public class DynamicURLMap extends URLMap {

	/**
	 * 
	 * @Description: 随机获取地址
	 * @author pangjianhai
	 * @date 2014-9-27 上午10:07:25
	 * @String
	 * 
	 * @return
	 */
	public static String getRandomIP() {
		Map<String, Integer> oldMap = getURLMap();
		Map<String, Integer> newMap = new HashMap<String, Integer>(oldMap);
		return LoadComputeUtil.getRandomValue(newMap);
	}

	/**
	 * @Description: 轮询获取地址
	 * @author pangjianhai
	 * @date 2014-9-27 下午5:51:27
	 * @String
	 * 
	 * @return
	 */
	public static String getPollIP() {
		Map<String, Integer> oldMap = getURLMap();
		Map<String, Integer> newMap = new HashMap<String, Integer>(oldMap);
		return LoadComputeUtil.getPollValue(newMap);
	}

	/**
	 * @Description: 随机加权获取地址
	 * @author pangjianhai
	 * @date 2014-9-27 下午5:51:27
	 * @String
	 * 
	 * @return
	 */
	public static String getRandomWeightIP() {
		Map<String, Integer> oldMap = getURLMap();
		Map<String, Integer> newMap = new HashMap<String, Integer>(oldMap);
		return LoadComputeUtil.getRandomWeightValue(newMap);
	}

	/**
	 * @Description: 加权轮询获取地址
	 * @author pangjianhai
	 * @date 2014-9-27 下午5:51:27
	 * @String
	 * 
	 * @return
	 */
	public static String getPollWeightIP() {
		Map<String, Integer> oldMap = getURLMap();
		Map<String, Integer> newMap = new HashMap<String, Integer>(oldMap);
		return LoadComputeUtil.getPollWeightValue(newMap);
	}

	public static void main(String[] args) {
		// String ip = getRandomIP();
		// System.out.println(ip);

		for (int i = 0; i < 17; i++) {
			String ip = getPollWeightIP();
			System.out.println(ip);
		}
	}
}
