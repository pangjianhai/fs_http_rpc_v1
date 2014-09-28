package com.qooc.httprpc.codec;

import java.io.StringReader;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;

public class JSON2Object {
	/**
	 * @Description:将一个JSON转化成对象
	 * @author pangjianhai
	 * @date 2014-9-24 下午20:38:06
	 * @Object
	 * 
	 * @param message
	 * @param destClass
	 * @return
	 */
	public static Object convertJSON2Object(String json, Class destClass) {

		try {
			JSONValue value = new JSONParser(new StringReader(json))
					.nextValue();
			return JSONMapper.toJava(value, destClass);
		} catch (Exception e) {
			throw new RuntimeException("json转换对象异常", e);
		}
	}
}
