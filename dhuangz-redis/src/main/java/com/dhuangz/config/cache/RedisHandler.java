package com.dhuangz.config.cache;

import redis.clients.jedis.Jedis;

/**
 * Created By zhangyufei on 2017/9/21
 */
public interface RedisHandler<T> {
    /**
     * Redis执行方法
     *
     * @param jedis
     * @return
     */
    T execute(Jedis jedis);
}
