package com.lt.alidemo.service.impl;

import com.lt.alidemo.config.CommonConfig;
import com.lt.alidemo.service.IdVerifyService;
import com.lt.alidemo.utils.DateUtil;
import com.lt.alidemo.utils.HttpUtils;
import com.lt.alidemo.utils.Md5utils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LT
 * @Date: 2020/3/4 17:09
 * @Description:
 * @Version 1.0
 */
@Service
public class IdVerifyServiceImpl implements IdVerifyService {
	@Autowired
	private CommonConfig commonConfig;

	@Override
	public String selectVerifyName(Map map) {
		//API产品路径
		String host = (String) map.get("host");
		String path = (String) map.get("path");
		String method = "GET";
		//阿里云APPCODE
		String appcode = "你自己的AppCode";
		appcode = this.commonConfig.getYiyuanAppCode();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("btype", (String) map.get("btype"));
		querys.put("entityId", (String) map.get("entityId"));
		Date date = new Date();
		String dateTime = DateUtil.formatDateTime(date, DateUtil.format_yyyyMMddHHmm);
		String md5 = Md5utils.MD5("6tj4u"+dateTime);
		querys.put("entityName", (String) map.get("entityName"));
		querys.put("sign", md5);
		String bodys = "";


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
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			System.out.println(response.toString());
			//获取response的body
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
