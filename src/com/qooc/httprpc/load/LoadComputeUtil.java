package com.qooc.httprpc.load;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @ClassName: LoadComputeUtil
 * @Description: 算法
 * @author pangjianhai
 * @date 2014-9-24 下午19:01:06
 * 
 */
public class LoadComputeUtil {

	/**
	 * 轮询
	 */
	private static Integer pose = 0;
	/**
	 * 加权轮询
	 */
	private static Integer weight_pose = 0;

	/**
	 * 随机算法
	 */
	public static String getRandomValue(Map<String, Integer> newMap) {
		List<String> list = new ArrayList<String>();
		Set<String> set = newMap.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			list.add(it.next().toString());
		}
		int size = list.size();
		int random = (int) (Math.random() * size);
		return list.get(random);
	}

	/**
	 * 轮询算法
	 */
	public static String getPollValue(Map<String, Integer> newMap) {
		List<String> list = new ArrayList<String>();
		Set<String> set = newMap.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			list.add(it.next().toString());
		}
		synchronized (pose) {
			if (pose >= list.size() - 1) {
				pose = 0;
			} else {
				pose++;
			}
		}
		return list.get(pose);
	}

	/**
	 * 随机加权算法
	 */
	public static String getRandomWeightValue(Map<String, Integer> newMap) {
		List<String> list = new ArrayList<String>();
		Set<Entry<String, Integer>> set = newMap.entrySet();
		Iterator<Entry<String, Integer>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			String ip = entry.getKey();
			Integer value = entry.getValue() != null ? entry.getValue() : 0;
			for (int i = 0; i < value; i++) {
				list.add(ip);
			}
		}
		int size = list.size();
		int random = (int) (Math.random() * size);
		return list.get(random);
	}

	/**
	 * 加权轮询算法
	 */
	public static String getPollWeightValue(Map<String, Integer> newMap) {
		List<String> list = new ArrayList<String>();
		Set<Entry<String, Integer>> set = newMap.entrySet();
		Iterator<Entry<String, Integer>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			String ip = entry.getKey();
			Integer value = entry.getValue() != null ? entry.getValue() : 0;
			for (int i = 0; i < value; i++) {
				list.add(ip);
			}
		}
		synchronized (weight_pose) {
			if (weight_pose >= list.size() - 1) {
				weight_pose = 0;
			} else {
				weight_pose++;
			}
		}
		return list.get(weight_pose);
	}

}
