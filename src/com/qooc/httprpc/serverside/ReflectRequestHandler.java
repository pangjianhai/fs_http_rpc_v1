package com.qooc.httprpc.serverside;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qooc.httprpc.netvalue.RequestParam;

public class ReflectRequestHandler extends AbstractRequestHandler {
	protected static Logger logger = LoggerFactory
			.getLogger(ReflectRequestHandler.class);

	@Override
	public Object getServiceObject(RequestParam r) {
		String className = r.getInterfaceOrClassName();
		if (className == null || "".equals(className.trim())) {
			logger.error("请求没有类名参数");
			throw new RuntimeException("请求没有类名参数");
		}
		try {
			return Class.forName(className).newInstance();
		} catch (ClassNotFoundException e) {
			logger.error("根据类名反射构造对象出错，查不到类");
			e.printStackTrace();
		} catch (InstantiationException e) {
			logger.error("根据类名反射构造对象出错，初始化异常");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error("根据类名反射构造对象出错，非法访问");
			e.printStackTrace();
		}
		return null;
	}

}
