package com.qooc.httprpc.load;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @ClassName: BusinessInfo
 * @Description: 服务提供者URL业务部分
 * @author pangjianhai
 * @date 2014-9-24 下午19:38:11
 * 
 */
public class BusinessInfo {
	/**
	 * 配置文件地址
	 */
	private final static String location = "/business_part.properties";

	/**
	 * 业务URL后缀
	 */
	public static String APPENDIX = "";
	/**
	 * 初始化后缀
	 */
	static {
		Properties result = new Properties();
		try {
			result.load(BusinessInfo.class.getResourceAsStream(location));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set<Entry<Object, Object>> set = result.entrySet();
		Iterator<Entry<Object, Object>> it = set.iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			APPENDIX = entry.getValue().toString();
			break;
		}
	}

	public static void main(String[] args) {
		System.out.println(BusinessInfo.APPENDIX);
	}
}
