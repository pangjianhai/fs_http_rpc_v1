package com.qooc.httprpc.serverside;

/**
 * 
 * @ClassName: ClazzUtil
 * @Description: TODO
 * @author pangjianhai
 * @date 2014-9-26 上午9:41:35
 * 
 */
public class ClazzUtil {

	public static Class getPrivateByWapper(Class pc) {
		if (pc == Integer.class) {
			return int.class;
		}
		if (pc == Long.class) {
			return long.class;
		}
		if (pc == Double.class) {
			return double.class;
		}
		if (pc == Float.class) {
			return float.class;
		}
		if (pc == Short.class) {
			return short.class;
		}
		if (pc == Boolean.class) {
			return boolean.class;
		}
		if (pc == Byte.class) {
			return byte.class;
		}
		return pc;
	}
}
