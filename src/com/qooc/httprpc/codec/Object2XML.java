package com.qooc.httprpc.codec;

import java.util.ArrayList;
import java.util.List;

import com.qooc.httprpc.test.Dog;
import com.qooc.httprpc.test.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @ClassName: Object2XML
 * @Description: 将对象转换成XML
 * @author pangjianhai
 * @date 2014-9-24 下午20:30:06
 * 
 */
public class Object2XML {

	/**
	 * 
	 * @Description: 转换
	 * @author pangjianhai
	 * @date 2014-9-27 下午1:58:10
	 * @String
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertObject2XML(Object obj) {
		XStream xs = new XStream(new DomDriver());
		return xs.toXML(obj);
	}

	public static void main(String[] args) {

	}
}
