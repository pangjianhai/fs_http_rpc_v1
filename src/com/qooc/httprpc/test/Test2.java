package com.qooc.httprpc.test;

import java.util.ArrayList;
import java.util.List;

import com.qooc.httprpc.codec.JSON2Object;
import com.qooc.httprpc.codec.Object2JSON;
import com.qooc.httprpc.codec.Object2XML;
import com.qooc.httprpc.codec.XML2Object;

public class Test2 {

	public static void encode() {

		List<Object> args = new ArrayList();
		args.add(new Integer(4));
		args.add(new Long(4));
		args.add(new Double(44.44));
		args.add(new Double(4444444444444444444.44));
		args.add(1111111111111111111L);
		args.add("cao");
		args.add("e");
		// String s = Object2JSON.convertObject2JSON(args);
		String s = Object2XML.convertObject2XML(args);
		System.out.println(s);
	}

	public static void dc() {
		String xml = "<list><short>1</short><float>4.44</float><int>4</int><long>4</long><double>44.44</double><double>4.4444444444444447E18</double><long>1111111111111111111</long><string>cao</string><string>e</string></list>";
		List list = (List) XML2Object.convertXML2Object(xml, List.class);
		for (int i = 0; i < list.size(); i++) {
			String clazz = list.get(i).getClass().getName();
			System.out.println(clazz);
		}
	}

	public static void decode() {
		String json = "[4,555.66]";
		List list = (List) JSON2Object.convertJSON2Object(json, List.class);
		for (int i = 0; i < list.size(); i++) {
			String clazz = list.get(i).getClass().getName();
			System.out.println(clazz);
		}
	}

	public static void main(String[] args) {
		dc();
	}
}
