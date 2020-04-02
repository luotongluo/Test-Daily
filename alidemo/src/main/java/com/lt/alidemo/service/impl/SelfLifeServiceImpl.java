package com.lt.alidemo.service.impl;

import com.lt.alidemo.config.CommonConfig;
import com.lt.alidemo.service.SelfLifeService;
import com.lt.alidemo.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LT
 * @Date: 2020/3/4 15:26
 * @Description:
 * @Version 1.0
 */
@Service
public class SelfLifeServiceImpl implements SelfLifeService {
	@Autowired
	private CommonConfig commonConfig;
	@Override
	public Map getSlefLife(Map map) {
		//API产品路径
		String host = (String) map.get("host");
		String path = (String) map.get("path");
		String method = "GET";
		//阿里云APPCODE
		String appcode = "你自己的AppCode";
		appcode = this.commonConfig.getYiyuanAppCode();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode);
		//UUID采用当前程序运行时间，用于防止重放攻击，开发者可根据自己需求，自定义字符串
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String UUID = df.format(new Date());
		java.util.UUID uuid = java.util.UUID.randomUUID();
		headers.put("X-Ca-Nonce", uuid.toString());
		Map<String, String> querys = new HashMap<String, String>();
		//参数配置
		//生日，如：20180808080808，即2018年08月08日08时08分08秒，若无法确定确切小时分钟与秒，请用00表示，如：20180808000000
		querys.put("BIRTH", (String) map.get("BIRTH"));
		//姓氏，如：张
		querys.put("FIRST_NAME", (String) map.get("FIRST_NAME"));
		//性别，如：男
		querys.put("GENDER", (String) map.get("GENDER"));
		//名称，如：无忌
		querys.put("LAST_NAME", (String) map.get("LAST_NAME"));


		try {
			/**
			 * 重要提示如下:
			 * HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			System.out.println(response.toString());
			//获取response的body
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

