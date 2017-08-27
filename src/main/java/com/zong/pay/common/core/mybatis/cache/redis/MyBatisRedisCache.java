package com.zong.pay.common.core.mybatis.cache.redis;

import org.apache.ibatis.cache.decorators.LoggingCache;

/**
 * @author 宗叶青 on 2017/8/21/22:55
 */
public class MyBatisRedisCache extends LoggingCache{
    public MyBatisRedisCache(String id){
        super(new RedisCache(id));
    }
}
