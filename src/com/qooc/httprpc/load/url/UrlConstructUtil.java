package com.qooc.httprpc.load.url;

import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qooc.httprpc.codec.Object2JSON;
import com.qooc.httprpc.codec.Object2XML;
import com.qooc.httprpc.load.BusinessInfo;
import com.qooc.httprpc.load.DynamicURLMap;
import com.qooc.httprpc.netvalue.RequestConst;
import com.qooc.httprpc.netvalue.RequestMethodArgs;
import com.qooc.httprpc.netvalue.RequestParam;

/**
 * @ClassName: UrlConstructUtil
 * @Description: 构造请求URL
 * @author pangjianhai
 * @date 2014-9-24 下午19:35:16
 * 
 */
public class UrlConstructUtil {
	protected static Logger logger = LoggerFactory
			.getLogger(UrlConstructUtil.class);

	/**
	 * @Description: TODO
	 * @author pangjianhai
	 * @date 2014-9-27 下午5:46:49
	 * @String
	 * 
	 * @return
	 */
	public static String constructURL(RequestParam rp, RequestMethodArgs args) {
		String PRE = "http://";
		String IP = getIPByStrategy(LbStrategyInfo.RB_STRATEGY);
		String APPENDIX = BusinessInfo.APPENDIX;
		String PARAMETER = "";
		int request_format = rp.getRequest_format();
		/**
		 * 如果返回值序列化方式没有设置，则默认为JSON
		 */
		int response_format = rp.getResponse_format();
		if (response_format == 0) {
			rp.setResponse_format(RequestConst.RETURN_FORMAT_JSON);
		}
		String p_str = "";
		if (RequestConst.RETURN_FORMAT_JSON == request_format) {
			p_str = Object2JSON.convertObject2JSON(rp);
		} else if (RequestConst.RETURN_FORMAT_XML == request_format) {
			p_str = Object2XML.convertObject2XML(rp);
		} else {
			/**
			 * 请求的时候没有设定编码的方式，则默认JSON
			 */
			request_format = RequestConst.RETURN_FORMAT_XML;
			rp.setRequest_format(RequestConst.RETURN_FORMAT_XML);
			p_str = Object2XML.convertObject2XML(rp);
		}
		/**
		 * 参数必须用XML序列化
		 */
		String arg_str = "";
		if (args != null) {
			List arg_list = args.getArgs();
			if (arg_list != null && arg_list.size() > 0) {
				arg_str = Object2XML.convertObject2XML(arg_list);
			}
		}
		try {
			p_str = URLEncoder.encode(p_str, "utf-8");
			arg_str = URLEncoder.encode(arg_str, "utf-8");
		} catch (Exception e) {

		}
		PARAMETER = PARAMETER + "?http_rpc_request_format=" + request_format
				+ "&http_rpc_request_content=" + p_str + "&arg_str=" + arg_str;
		return PRE + IP + APPENDIX + PARAMETER;
	}

	private static String getIPByStrategy(Integer type) {
		String ip = "";
		if (RequestConst.LoadbalanceStrategy.LB_RANDOM == type) {
			ip = DynamicURLMap.getRandomIP();
		} else if (RequestConst.LoadbalanceStrategy.LB_POLL == type) {
			ip = DynamicURLMap.getPollIP();
		} else if (RequestConst.LoadbalanceStrategy.LB_RANDOM_WEIGHT == type) {
			ip = DynamicURLMap.getRandomWeightIP();
		} else if (RequestConst.LoadbalanceStrategy.LB_POLLWEIGHT == type) {
			ip = DynamicURLMap.getPollWeightIP();
		} else {
			logger.info("没有配置负载均衡策略");
			ip = DynamicURLMap.getRandomIP();
		}
		return ip;
	}

	public static void main(String[] args) {
		// System.out.println(constructURL(null));
	}

}
