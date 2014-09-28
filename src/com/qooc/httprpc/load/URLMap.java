package com.qooc.httprpc.load;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * @ClassName: URLMap
 * @Description: 地址容器
 * @author pangjianhai
 * @date 2014-9-24 下午19:28:22
 * 
 */
public abstract class URLMap {
	private final static String location = "/http_url.properties";
	private static Map<String, Integer> urlMap = new HashMap<String, Integer>();

	public final static void initMap() {
		Properties ps = getProperties(location);
		Set<Entry<Object, Object>> set = ps.entrySet();
		Iterator<Entry<Object, Object>> it = set.iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			Integer weight = entry.getValue() != null ? new Integer(entry
					.getValue().toString()) : 0;
			String url = entry.getKey().toString();
			url = url.replace("$", ":");
			// System.out.println(url + "*****" + weight);
			urlMap.put(url, weight);
		}
	}

	private static Properties getProperties(String location) {
		Properties result = new Properties();
		try {
			result.load(URLMap.class.getResourceAsStream(location));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Map<String, Integer> getURLMap() {
		if (urlMap == null || urlMap.size() == 0) {
			synchronized (urlMap) {
				initMap();
			}
		}
		return urlMap;
	}

	public static void main(String[] args) {
		initMap();
	}
}
