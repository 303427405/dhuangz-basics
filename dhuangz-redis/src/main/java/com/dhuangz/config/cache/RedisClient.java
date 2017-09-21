package com.dhuangz.config.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 客户端
 * Created By zhangyufei on 2017/9/21
 */
public class RedisClient {


    /**
     * redis客户端连接池
     */
    private JedisPool jedisPool;

    /**
     * 初始化 redis
    */
    public RedisClient(){
        if(jedisPool == null){
            initialPool();
        }
    }

    /**
     * 初始化连接池
     */
    private void initialPool(){
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config,"127.0.0.1",6379);
    }


    /**
     * 调用Redis
     *
     * @param handler
     * @return
     */
    public <T> T callRedis(RedisHandler<T> handler) {
        Jedis jedis = jedisPool.getResource();
        try {
            return (T) handler.execute(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }


}
