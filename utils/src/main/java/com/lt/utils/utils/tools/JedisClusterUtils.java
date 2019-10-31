//package com.lt.utils.utils.tools;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisCluster;
//
//@Component
//public class JedisClusterUtils{
//
//    private static Logger logger = LoggerFactory.getLogger(JedisClusterUtils.class);
//
//    @Autowired
//    private JedisCluster jedisCluster;
//
//    /**
//     * 查询缓存信息
//     * @param key
//     * @return
//     */
//    public String get(String key) {
//        String value = null;
//        try{
//            if(StringUtils.isBlank(key)){
//                return null;
//            }
//            value = jedisCluster.get(key);
//        }catch (Exception e){
//            logger.error("获取缓存KEY：[{}]异常,异常信息",key,e);
//        }
//        return value;
//    }
//
//    /**
//     * 缓存KEY自增
//     * @param key
//     * @return
//     */
//    public Long incr(String key) {
//        Long value = null;
//        try{
//            if(StringUtils.isBlank(key)){
//                return null;
//            }
//            value = jedisCluster.incr(key);
//        }catch (Exception e){
//            logger.error("设置KEY：[{}]自增异常,异常信息",key,e);
//        }
//        return value;
//    }
//
//    /**
//     * 设置过期时间
//     * @param key
//     * @param seconds
//     * @return
//     */
//    public Long expire(String key, int seconds) {
//        Long value = null;
//        try{
//            if(StringUtils.isBlank(key)){
//                return null;
//            }
//            value = jedisCluster.expire(key,seconds);
//        }catch (Exception e){
//            logger.error("设置KEY：[{}]过期时间异常,异常信息",key,e);
//        }
//        return value;
//    }
//}
