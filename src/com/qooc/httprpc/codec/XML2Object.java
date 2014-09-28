package com.qooc.httprpc.codec;

import com.qooc.httprpc.test.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @ClassName: XML2Object
 * @Description: 将XML转换成javabean
 * @author pangjianhai
 * @date 2014-9-24 下午20:20:06
 * 
 */
public class XML2Object {
	public static Object convertXML2Object(String xml, Class clazz) {
		XStream xs = new XStream(new DomDriver());
		xs.alias(clazz.getName(), clazz);
		Object obj = xs.fromXML(xml);
		return obj;
	}

	public static void main(String[] args) {
		String xml = "<com.qooc.httprpc.test.Person><name>pjh</name><age>1</age><addresses><string>shandong</string><string>beijing</string>"
				+ "</addresses></com.qooc.httprpc.test.Person>";
		Object obj = convertXML2Object(xml, Person.class);
		System.out.println(obj);
	}
}
