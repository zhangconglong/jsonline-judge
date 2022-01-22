package com.example.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisHashMapUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time（秒）
     * @return
     */
    public boolean expire(String key, long time){
        try{
            if (time>0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 相同一张hash表中放入数据，如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return
     */
    public boolean hset(String key, String item, String value){

        try{
            System.out.println("插入redis中");
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据
     * @param key
     * @param item
     * @param value
     * @param time 如果hash表中有时间，将会被替换原有的时间
     * @return
     */
    public boolean hset(String key, String item, Object value, long time){

        try{
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key, String item1, Object value1, String item2, Object value2,long time){

        try{
            redisTemplate.opsForHash().put(key, item1, value1);
            redisTemplate.opsForHash().put(key, item2, value2);
            if (time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key
     * @param item
     */
    public void hdel(String key, Object... item){
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key
     * @param item
     */
    public boolean hHaashKey(String key, String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }
}