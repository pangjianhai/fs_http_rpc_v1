package com.qooc.httprpc.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qooc.httprpc.codec.JSON2Object;
import com.qooc.httprpc.codec.Object2JSON;

public class Person<T> {

	private String name;
	private int age;

	List<T> ds;

	public List<T> getDs() {
		return ds;
	}

	public void setDs(List<T> ds) {
		this.ds = ds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int add(int a, int b) {
		System.out.println("int加法");
		return a + b;
	}

	public int addb(Integer a, Integer b) {
		System.out.println("Integer加法");
		return a + b;
	}

	public Dog updateDog(Dog dog) {
		dog.setDogName("haishipjh");
		return dog;
	}

	public static void main(String[] args) {
		getObj();
		List list = new ArrayList();
		list.add("pangjainhai");
		list.add(1);
		String str = Object2JSON.convertObject2JSON(list);
		System.out.println(str);

	}

	public static void getObj() {
		// String json =
		// "{\"addresses\":[\"山东\",\"上海\"],\"age\":1,\"name\":\"pjh\"}";
		String j = "[\"pangjainhai\",1]";
		List p = (List) JSON2Object.convertJSON2Object(j, List.class);
		System.out.println(p.size());
	}
}
