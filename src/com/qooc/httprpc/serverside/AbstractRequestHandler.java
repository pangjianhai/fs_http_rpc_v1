package com.qooc.httprpc.serverside;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qooc.httprpc.codec.JSON2Object;
import com.qooc.httprpc.codec.Object2JSON;
import com.qooc.httprpc.codec.Object2XML;
import com.qooc.httprpc.codec.XML2Object;
import com.qooc.httprpc.netvalue.RequestConst;
import com.qooc.httprpc.netvalue.RequestMethodArgs;
import com.qooc.httprpc.netvalue.RequestParam;
import com.qooc.httprpc.netvalue.ResponseValue;

/**
 * @ClassName: AbstractRequestHandler
 * @Description: 提供者请求执行
 * @author pangjianhai
 * @date 2014-9-26 上午9:12:02
 * 
 */
public abstract class AbstractRequestHandler {
	protected static Logger logger = LoggerFactory
			.getLogger(AbstractRequestHandler.class);

	/**
	 * @Description: 提供者根据请求的两个参数构造RPC调用的依据
	 * @author pangjianhai
	 * @date 2014-9-26 下午2:11:18
	 * @RequestParam
	 * 
	 * @param requestType
	 * @param requestContent
	 * @return
	 */
	public RequestParam parseRequestParam(Integer requestType,
			String requestContent) {
		System.out.println("**************requestContent:" + requestContent);
		if (requestType == null) {
			logger.debug("请求的时候没有指明参数的序列化方式，默认将采用JSON反序列化");
		}
		RequestParam rp = null;
		if (RequestConst.RETURN_FORMAT_JSON == requestType) {
			rp = (RequestParam) JSON2Object.convertJSON2Object(requestContent,
					RequestParam.class);
		} else if (RequestConst.RETURN_FORMAT_JSON == requestType) {
			rp = (RequestParam) XML2Object.convertXML2Object(requestContent,
					RequestParam.class);
		} else {
			rp = (RequestParam) JSON2Object.convertJSON2Object(requestContent,
					RequestParam.class);
		}
		if (rp == null) {
			logger.error("提供者端反序列化参数时失败");
			throw new RuntimeException("提供者端反序列化参数时失败");
		}
		return rp;
	}

	/**
	 * @Description: 解析方法参数（XML）
	 * @author pangjianhai
	 * @date 2014-9-26 下午4:41:00
	 * @RequestMethodArgs
	 * 
	 * @param requestArgs
	 * @return
	 */
	public RequestMethodArgs getMethodArgs(String requestArgs) {
		if (requestArgs != null && !"".equals(requestArgs)) {
			List lst = (List) XML2Object.convertXML2Object(requestArgs,
					List.class);
			RequestMethodArgs a = new RequestMethodArgs();
			a.setArgs(lst);
			return a;
		}
		return null;
	}

	/**
	 * @Description: 根据消费者请求查询对应的服务bean
	 * @author pangjianhai
	 * @date 2014-9-26 上午9:12:57
	 * @Object
	 * 
	 * @param serviceName
	 * @return
	 */
	public abstract Object getServiceObject(RequestParam r);

	/**
	 * @Description: 真正处理业务
	 * @author pangjianhai
	 * @date 2014-9-26 上午9:15:39
	 * @Object
	 * 
	 * @param r
	 * @return
	 * @throws Exception
	 * @throws Exception
	 * @throws SecurityException
	 */
	public Object handleRequest(RequestParam r, RequestMethodArgs a)
			throws SecurityException, Exception {
		Object obj = getServiceObject(r);
		if (obj == null) {
			logger.error("查不到服务");
		}
		String methodName = r.getMethodName();
		List<Object> args = null;
		if (a != null)
			args = a.getArgs();
		Class[] paramClass = null;
		Method method = null;
		if (args == null || args.size() == 0) {
			paramClass = null;
		} else {
			int arg_length = args.size();
			paramClass = new Class[arg_length];
			for (int i = 0; i < arg_length; i++) {
				Class pc = args.get(i).getClass();
				paramClass[i] = pc;
			}
		}
		method = obj.getClass().getMethod(methodName, paramClass);
		Object[] arg_arr = null;
		if (args != null && args.size() > 0) {
			arg_arr = args.toArray();
		}
		Object result = method.invoke(obj, arg_arr);
		return result;
	}

	/**
	 * @Description: 结果序列化
	 * @author pangjianhai
	 * @date 2014-9-26 上午9:16:42
	 * @String
	 * 
	 * @param r
	 * @param result
	 * @return
	 */
	public String wrapResult(RequestParam r, RequestMethodArgs a) {
		ResponseValue rv = new ResponseValue();// 需要返回的序列化结果
		Object result = null;
		rv.setStatus(RequestConst.RESPONSE_FAILURE);
		try {
			result = handleRequest(r, a);
			rv.setStatus(RequestConst.RESPONSE_SUCCESS);// 设置提供者执行结果状态
			rv.setResponseObj(result);// 设置执行结果
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String value = "";
		int f = r.getResponse_format();// 返回给消费者的序列化类型，以供反序列化成为对象
		/**
		 * 返回结果的序列化方式就是请求参数中规定的
		 */
		if (RequestConst.RETURN_FORMAT_XML == f) {
			rv.setResponse_format(f);
			value = Object2XML.convertObject2XML(rv);
		} else if (RequestConst.RETURN_FORMAT_JSON == f) {
			rv.setResponse_format(f);
			value = Object2JSON.convertObject2JSON(rv);
		} else {
			/**
			 * 若没有规定默认JSON
			 */
			rv.setResponse_format(RequestConst.RETURN_FORMAT_JSON);
			value = Object2JSON.convertObject2JSON(rv);
		}
		return value;
	}

}
