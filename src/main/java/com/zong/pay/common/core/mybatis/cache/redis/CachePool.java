package com.zong.pay.common.core.mybatis.cache.redis;

import com.zong.paycommon.utils.cache.redis.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * redis吃实话与连接池实现
 * 由于需结合mybatis实现，不与spring redis注解实现混用
 * 与spring redis注解实现，个独立实现各自功能
 * @author 宗叶青 on 2017/8/21/21:44
 */
public class CachePool {
    JedisSentinelPool pool;
    private static final CachePool cachePool = new CachePool();
    private RedisUtils redisUtils = new RedisUtils();

    public static CachePool getInstance(){
        return cachePool;
    }

    private CachePool(){
        pool = redisUtils.getJedisSentinelPool();
    }

    public Jedis getJedis(){
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = pool.getResource();
        }catch (JedisConnectionException e){
            borrowOrOprSuccess = false;
            if(jedis != null){
                pool.returnBrokenResource(jedis);
            }
        }finally{
            if(borrowOrOprSuccess)
                pool.returnResource(jedis);
        }
        jedis = pool.getResource();
        return jedis;
    }

    public JedisSentinelPool getJedisPool(){
        return this.pool;
    }
}
