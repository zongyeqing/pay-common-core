package com.zong.pay.common.core.mybatis.cache.redis;

import com.zong.paycommon.utils.cache.redis.SerializeUtils;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 宗叶青 on 2017/8/21/21:34
 */
public class RedisCache implements Cache {

    private static final Logger LOGGER = Logger.getLogger(RedisCache.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String id;

    public RedisCache(final String id){
        if(id == null){
            throw new IllegalArgumentException("必须传入ID");
        }
        LOGGER.debug("MybatisRedisCache : id" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {
        Jedis jedis = null;
        JedisSentinelPool jedisPool = null;
        int result = 0;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = CachePool.getInstance().getJedis();
            jedisPool = CachePool.getInstance().getJedisPool();
            result = Integer.valueOf(jedis.dbSize().toString());
        }catch (JedisConnectionException e){
            borrowOrOprSuccess = false;
            if(jedis != null)
                jedisPool.returnBrokenResource(jedis);
        }finally {
            if(borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
        return result;
    }

    @Override
    public void putObject(Object key, Object value) {
        if(LOGGER.isDebugEnabled())
            LOGGER.debug("putObject:" + key.hashCode() + "=" + value);
        if(LOGGER.isInfoEnabled())
            LOGGER.info("put to redis sql :" + key.toString());
        Jedis jedis = null;
        JedisSentinelPool jedisPool = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = CachePool.getInstance().getJedis();
            jedisPool = CachePool.getInstance().getJedisPool();
            jedis.set(SerializeUtils.serialize(key.hashCode()), SerializeUtils.serialize(value));
        }catch (JedisConnectionException e){
            borrowOrOprSuccess = false;
            if(jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
    }

    @Override
    public Object getObject(Object key) {
        Jedis jedis = null;
        JedisSentinelPool jedisPool = null;
        Object value = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = CachePool.getInstance().getJedis();
            jedisPool = CachePool.getInstance().getJedisPool();
            value = SerializeUtils.unSerialize(jedis.get(SerializeUtils.serialize(key.hashCode())));
        } catch (JedisConnectionException e){
            borrowOrOprSuccess = false;
            if(jedis != null)
                jedisPool.returnBrokenResource(jedis);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
        if(LOGGER.isDebugEnabled())
            LOGGER.debug("getObject:" + key.hashCode() + "=" + value);
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        Jedis jedis = null;
        JedisSentinelPool jedisPool = null;
        Object value = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CachePool.getInstance().getJedis();
            jedisPool = CachePool.getInstance().getJedisPool();
            value = jedis.expire(SerializeUtils.serialize(key.hashCode()), 0);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("getObject:" + key.hashCode() + "=" + value);
        return value;
    }

    @Override
    public void clear() {
        Jedis jedis = null;
        JedisSentinelPool jedisPool = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CachePool.getInstance().getJedis();
            jedisPool = CachePool.getInstance().getJedisPool();
            jedis.flushDB();
            jedis.flushAll();
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
