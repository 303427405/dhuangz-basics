package com.dhuangz.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhuangz.config.cache.RedisClient;
import com.dhuangz.config.cache.RedisHandler;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * redis工具类
 * Created By zhangyufei on 2017/9/21
 */
public class RedisUtils {

    private static RedisClient client = new RedisClient();

    /**
     * put object to redis,key exist by default time(3h)
     *
     * @param key
     * @param value
     */
    public static void put(final String key, final Object value) {
        put(key, value, 60 * 60);
    }

    /**
     * put object to redis,key exist by set time
     *
     * @param key
     * @param value
     */
    public static void put(final String key, final Object value, final Integer seconds) {

        client.callRedis(new RedisHandler<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                String json = JSON.toJSONString(value);
                jedis.set(key, json);
                jedis.expire(key, seconds);
                return null;
            }
        });
    }


    /**
     * get object by id and key
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T get(final String key, final Class<T> clazz) {
        return client.callRedis(new RedisHandler<T>() {
            @Override
            public T execute(Jedis jedis) {
                String value = jedis.get(key);
                return StringUtils.isEmpty(value) ? null : JSONObject.parseObject(value, clazz);
            }
        });
    }

    /**
     * remove object by key
     *
     * @param key
     * @return
     */
    public static Long remove(final String key) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.del(key);
            }
        });
    }

    /**
     * get key exist time
     *
     * @param key
     * @return
     */
    public static Long ttl(final String key) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.ttl(key);
            }
        });
    }

    /**
     * 设置此key的生存时间，单位秒(s)
     */
    public static void setExpire(final String key, final int seconds) {
        client.callRedis(new RedisHandler<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                jedis.expire(key, seconds);
                return null;
            }
        });
    }


    //******************以下保存对象采用序列化机制,json形式有很多限制********************

    /**
     * 移除指定key中的指定field
     */
    public static void hremove(final String key, final String field) {
        client.callRedis(new RedisHandler<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                jedis.hdel(key.getBytes(StandardCharsets.UTF_8), field.getBytes(StandardCharsets.UTF_8));
                return null;
            }
        });
    }


    public static Long rpush(final String key, final String... value) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                Long result = jedis.rpush(key, value);
                return result;
            }
        });
    }

    /**
     * 获取rpush中的数据，start下标从0开始，end为-1时，表示最后一个元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<String> lrange(final String key, final int start, final int end) {
        return client.callRedis(new RedisHandler<List<String>>() {
            @Override
            public List<String> execute(Jedis jedis) {
                return jedis.lrange(key, start, end);
            }
        });
    }

    /**
     * 删除rpush中的数据，其中count为0的话，删除所有。假如为-2，则删除从后往前的2个value，为2则从前往后的2个value
     *
     * @param key
     * @param value
     * @param count
     * @return
     */
    public static Long lrem(final String key, final String value, final int count) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.lrem(key, count, value);
            }
        });
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public static Boolean exists(final String key) {
        return client.callRedis(new RedisHandler<Boolean>() {
            @Override
            public Boolean execute(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }

    /**
     * 原子增加key的值+1，如果key不存在，则创建并赋值为1。如果存在值但不是integer类型的则会报错
     *
     * @param key 键
     * @return 返回原子+1后的数值
     */
    public static Long incr(final String key) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.incr(key);
            }
        });
    }

    /**
     * 原子增加key的值+num
     *
     * @param key 键
     * @param num 原子增加的数量
     * @return 返回原子+num后的数值
     */
    public static Long incrBy(final String key, final long num) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.incrBy(key, num);
            }
        });
    }

    /**
     * 原子增加key的值+num
     *
     * @param key 键
     * @param num 原子增加的数量
     * @return 返回原子+num后的数值
     */
    public static Double incrByFloat(final String key, final double num) {
        return client.callRedis(new RedisHandler<Double>() {
            @Override
            public Double execute(Jedis jedis) {
                return jedis.incrByFloat(key, num);
            }
        });
    }

    /**
     * set if not exists object to redis, key exist by default time(1h)
     * @param key 键
     * @param value 值
     * @return Integer reply, specifically: 1 if the key was set；0 if the key was not set
     */
    public static Long setnx(final String key, final Object value){
        return setnx(key, value, 60 * 60);
    }

    /**
     * set if not exists object to redis
     * @param key 键
     * @param value 值
     * @param seconds 有效期秒数
     * @return Integer reply, specifically: 1 if the key was set；0 if the key was not set
     */
    public static Long setnx(final String key, final Object value, final Integer seconds){
        return setnx(key, value, seconds, true);
    }

    /**
     * 与上面方法一致，refreshExpireTime为true的时候，每次set会重置key的生存时间，false不会重置
     *
     * @param key
     * @param value
     * @param seconds
     * @param refreshExpireTime
     * @return
     */
    public static Long setnx(final String key, final Object value, final Integer seconds, final Boolean refreshExpireTime) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                String json = JSON.toJSONString(value);
                Long ret = jedis.setnx(key, json);
                if (refreshExpireTime || ret == 1) {
                    jedis.expire(key, seconds);
                }
                return ret;
            }
        });
    }

    /**
     * 原子增加key的值-1
     *
     * @param key 键
     * @return 返回原子-1后的数值
     */
    public static Long decr(final String key) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.decr(key);
            }
        });
    }

    /**
     * 原子增加key的值-num
     *
     * @param key 键
     * @param num 原子增加的数量
     * @return 返回原子+num后的数值
     */
    public static Long decrBy(final String key, final long num) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.decrBy(key, num);
            }
        });
    }

    /** shiro add method start*/

    /**
     * get value from redis
     *
     * @param key 键
     * @return 返回 value
     */
    public static byte[] get(final byte[] key) {
        return client.callRedis(new RedisHandler<byte[]>() {
            @Override
            public byte[] execute(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    /**
     * put object to redis,key exist by set time
     *
     * @param key
     * @param value
     */
    public static byte[] set(final byte[] key, final byte[] value, final Integer seconds) {

        client.callRedis(new RedisHandler<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                jedis.set(key, value);
                jedis.expire(key, seconds);
                return value;
            }
        });
        return value;
    }

    /**
     * remove object by key
     *
     * @param key
     * @return
     */
    public static Long del(final byte[] key) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.del(key);
            }
        });
    }

    /**
     * 查找key
     *
     * @param pattern
     * @return 返回 value
     */
    public static Set<byte[]> keys(final String pattern) {
        return client.callRedis(new RedisHandler<Set<byte[]>>() {
            @Override
            public Set<byte[]> execute(Jedis jedis) {
                return jedis.keys(pattern.getBytes(StandardCharsets.UTF_8));
            }
        });
    }

    /**
     * 删除所有元素
     */
    public static void flushDB() {
        client.callRedis(new RedisHandler<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                jedis.flushDB();
                return null;
            }
        });
    }

    /**
     * 查询当前KEY数量
     *
     * @return
     */
    public static Long dbSize() {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.dbSize();
            }
        });
    }




}
