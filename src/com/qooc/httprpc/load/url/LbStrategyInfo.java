package com.qooc.httprpc.load.url;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import com.qooc.httprpc.load.BusinessInfo;
import com.qooc.httprpc.load.URLMap;

/**
 * @ClassName: LbStrategyInfo
 * @Description: 软负载配置
 * @author pangjianhai
 * @date 2014-9-24 下午19:38:06
 * 
 */
public class LbStrategyInfo {
	/**
	 * 配置文件地址
	 */
	private final static String location = "/loadbalance_strategy.properties";

	/**
	 * 策略
	 */
	public static Integer RB_STRATEGY = 1;
	/**
	 * 初始化策略
	 */
	static {
		Properties result = new Properties();
		try {
			result.load(LbStrategyInfo.class.getResourceAsStream(location));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set<Entry<Object, Object>> set = result.entrySet();
		Iterator<Entry<Object, Object>> it = set.iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			RB_STRATEGY = Integer.valueOf(entry.getValue().toString());
			break;
		}
	}

	public static void main(String[] args) {
		System.out.println(LbStrategyInfo.RB_STRATEGY);
	}
}
