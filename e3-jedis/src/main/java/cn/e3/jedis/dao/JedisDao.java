package cn.e3.jedis.dao;

/**
 * Created by GliangJu on 2017/12/12.
 */
public interface JedisDao {
    //抽取jedis常用命令
    //String
    public String set(String key,String value);
    public String get(String key);
    //自增
    public long incr(String key);
    //自减
    public long decr(String key);
    //hash
    //设置值
    public long hset(String key,String field,String value);
    //获取值
    public String hget(String key,String field);
    //删除
    public long hdel(String key,String field);

    //过期时间方法
    public long expire(String key,int seconds);
    //查询过期时间剩余时间
    public long ttl(String key);
}
