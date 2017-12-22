package cn.e3.jedis.dao.impl;

import cn.e3.jedis.dao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

/**
 * Created by GliangJu on 2017/12/12.
 */
@Repository
public class JedisDaoImpl implements JedisDao {

    //注入集群对象
    @Autowired
    private JedisCluster jc;
    @Override
    public String set(String key, String value) {
        String set = jc.set(key, value);
        return set;
    }

    @Override
    public String get(String key) {
        String value = jc.get(key);
        return value;
    }

    @Override
    public long incr(String key) {
        Long incr = jc.incr(key);
        return incr;
    }

    @Override
    public long decr(String key) {
        Long decr = jc.decr(key);
        return decr;
    }

    @Override
    public long hset(String key, String field, String value) {
        Long hset = jc.hset(key, field, value);
        return hset;
    }

    @Override
    public String hget(String key, String field) {
        String hget = jc.hget(key, field);
        return hget;
    }

    @Override
    public long hdel(String key, String field) {
        Long hdel = jc.hdel(key, field);
        return hdel;
    }

    @Override
    public long expire(String key, int seconds) {
        Long expire = jc.expire(key, seconds);
        return expire;
    }

    @Override
    public long ttl(String key) {
        Long ttl = jc.ttl(key);
        return ttl;
    }
}
