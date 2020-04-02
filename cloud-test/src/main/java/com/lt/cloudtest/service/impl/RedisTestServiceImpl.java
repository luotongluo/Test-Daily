package com.lt.cloudtest.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.cloudtest.service.RedisTestService;
import com.lt.cloudtest.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tong.luo on 2019/12/21 21:50
 */
@Service
public class RedisTestServiceImpl implements RedisTestService {
    @Autowired
    private RedisUtils redisService;

    @Override
    public Object JedisTest() {
//        String key = "test2";
//        boolean set = redisService.set(key, key);
//        if(set){
//            System.out.println(redisService.get(key));
//            return "success";
//        }else {
//            return "error";
//        }

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("user4", "user4");
//        map.put("user2", "user2");
//        map.put("user3", "user3");
        List<Serializable> var1 = new ArrayList<>();
        boolean mset = this.redisService.mset(map);
        for (String key : map.keySet()) {
            var1.add(key);
        }

        List<Object> mget = this.redisService.mget(var1);
        System.out.println("mget" + JSON.toJSONString(mget));

        return null;
    }
}
