package com.qooc.httprpc.test;

import java.util.ArrayList;
import java.util.List;

import com.qooc.httprpc.codec.Object2XML;
import com.qooc.httprpc.codec.XML2Object;

public class Test3 {

	public static void main(String[] args) {
		// encode();
		decode();
	}

	public static void encode() {
		Person p = new Person();
		p.setAge(1);
		p.setName("cao");
		List dogs = new ArrayList();
		Dog d1 = new Dog();
		d1.setDogName("cc");
		Dog d2 = new Dog();
		d2.setDogName("cc");
		dogs.add(d1);
		dogs.add(d2);
		p.setDs(dogs);
		String xml = Object2XML.convertObject2XML(p);
		System.out.println("xml:" + xml);
	}

	public static void decode() {
		String s = "<com.qooc.httprpc.test.Person><name>cao</name><age>1</age>"
				+ "<ds><com.qooc.httprpc.test.Dog><dogName>cc</dogName></com.qooc.httprpc.test.Dog><com.qooc.httprpc.test.Dog><dogName>cc</dogName></com.qooc.httprpc.test.Dog>"
				+ "" + "</ds></com.qooc.httprpc.test.Person>";
		Person obj = (Person) XML2Object.convertXML2Object(s, Person.class);
		System.out.println(((Dog) (obj.getDs().get(0))).getDogName());
	}
}
