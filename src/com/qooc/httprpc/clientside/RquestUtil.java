package com.qooc.httprpc.clientside;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qooc.httprpc.load.url.UrlConstructUtil;
import com.qooc.httprpc.netvalue.RequestMethodArgs;
import com.qooc.httprpc.netvalue.RequestParam;

/**
 * @ClassName: RquestUtil
 * @Description: 客户端请求工具类
 * @author pangjianhai
 * @date 2014-9-27 下午20:03:39
 * 
 */
public class RquestUtil {
	protected static Logger logger = LoggerFactory.getLogger(RquestUtil.class);

	public static String reqeustServer(RequestParam rp, RequestMethodArgs args) {
		/**
		 * 再执行的时候查看真正请求的URL和需要传递的参数
		 */
		String url = UrlConstructUtil.constructURL(rp, args);
		logger.debug("请求的地址：" + url);
		System.out.println(url);
		String response = null;
		HttpMethod method = null;
		try {
			HttpClient client = new HttpClient();
			method = new GetMethod(url);
			client.executeMethod(method);
			String status = method.getStatusText();// OK
			System.out.println("status:" + status);
			response = method.getResponseBodyAsString();
			System.out.println("response:" + response);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (method != null)
				method.releaseConnection();
		}
		return response;
	}

	public static void main(String[] args) {

	}

}
