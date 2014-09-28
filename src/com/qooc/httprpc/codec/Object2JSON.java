package com.qooc.httprpc.codec;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;

public class Object2JSON {
	/**
	 * @Description: 将对象转换JSON字符串
	 * @author pangjianhai
	 * @date 2014-9-24 下午20:30:06
	 * @String
	 * 
	 * @param vo
	 * @return
	 */
	public static String convertObject2JSON(Object object) {

		try {
			return JSONMapper.toJSON(object).render(false);
		} catch (MapperException e) {
			throw new RuntimeException("把对象【" + object + "】转换JSON为字符串的时候出现问题了",
					e);
		}

	}
}
