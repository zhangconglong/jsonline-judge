//package com.example.util.redis;
//
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//public final class RedisUtil {
//
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
//
//    /**
//     * 指定缓存失效时间
//     * @param key 键
//     * @param time（秒）
//     * @return
//     */
//    public boolean expire(String key, long time){
//        try{
//            if (time>0){
//                redisTemplate.expire(key, time, TimeUnit.SECONDS);
//            }
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 根据key获取过期时间
//     * @param key 不能为null
//     * @return 时间（秒） 返回0代表永久有效
//     */
//    private long getExpire(String key){
//        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
//    }
//
//    /**
//     * 判断key是否存在
//     * @param key
//     * @return true为存在
//     */
//    public boolean hasKey(String key){
//        try {
//            return redisTemplate.hasKey(key);
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 删除缓存
//     * @param key 可以传一个或者多个
//     */
//    public void del(String... key){
//
//        if (key!=null && key.length>0){
//            if (key.length == 1){
//                redisTemplate.delete(key[0]);
//            }else {
//                redisTemplate.delete(CollectionUtils.arrayToList(key));
//            }
//        }
//    }
//
//    /**
//     * 普通获取缓存
//     * @param key
//     * @return
//     */
//    public Object get(String key){
//
//       return key == null ? null : redisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 普通缓存放入
//      * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(String key, Object value){
//
//        try{
//            redisTemplate.opsForValue().set(key, value);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
//
//    /**
//     * 普通缓存放入，并设置时间
//      * @param key
//     * @param value
//     * @param time 时间（秒）time要大于0，如果time小于0，则设为不过期
//     * @return
//     */
//    public boolean set(String key, Object value, long time){
//
//        try{
//            if (time>0){
//                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
//            }else {
//                set(key, value);
//            }
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
//
//    /**
//     * 递增
//     * @param key
//     * @param delta
//     * @return
//     */
//    public Long incr(String key, long delta){
//        if (delta < 0){
//            throw new RuntimeException("递增因子必须大于0");
//        }
//        return redisTemplate.opsForValue().increment(key,  delta);
//    }
//
//    /**
//     * 递减
//     * @param key
//     * @param delta
//     * @return
//     */
//    public Long decr(String key, long delta){
//        if (delta < 0){
//            throw new RuntimeException("递减因子必须大于0");
//        }
//        return redisTemplate.opsForValue().increment(key,  -delta);
//    }
//
//    /**
//     * HashGet
//     * @param key 不能为null
//     * @param item 不能为null
//     * @return
//     */
//    public Object hget(String key, String item){
//        return redisTemplate.opsForHash().get(key,  item);
//    }
//
//    /**
//     * 获取hashkey对应的所有键值
//     * @param key
//     * @return 对应的多个键值
//     */
//    public Map<Object, Object> hmget(String key){
//        return redisTemplate.opsForHash().entries(key);
//    }
//
//    /**
//     * HashSet
//     * @param key
//     * @param map 对应多个键值
//     * @return 对应的多个键值
//     */
//    public boolean hmset(String key, Map<String, Object> map){
//
//        try{
//            redisTemplate.opsForHash().putAll(key, map);
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * HashSet 并设置时间
//     * @param key
//     * @param map 对应的多个键值
//     * @param time
//     * @return
//     */
//    public boolean hmset(String key, Map<String, Object> map, long time){
//
//        try{
//            redisTemplate.opsForHash().putAll(key, map);
//            if (time >0 ){
//                expire(key, time);
//            }
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 相同一张hash表中放入数据，如果不存在将创建
//     * @param key 键
//     * @param item 项
//     * @param value 值
//     * @return
//     */
//    public boolean hset(String key, String item, Object value){
//
//        try{
//            redisTemplate.opsForHash().put(key, item, value);
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 向一张hash表中放入数据
//     * @param key
//     * @param item
//     * @param value
//     * @param time 如果hash表中有时间，将会被替换原有的时间
//     * @return
//     */
//    public boolean hset(String key, String item, Object value, long time){
//
//        try{
//            redisTemplate.opsForHash().put(key, item, value);
//            if (time > 0){
//                expire(key, time);
//            }
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 删除hash表中的值
//     * @param key
//     * @param item
//     */
//    public void hdel(String key, Object... item){
//       redisTemplate.opsForHash().delete(key, item);
//    }
//
//    /**
//     * 判断hash表中是否有该项的值
//     * @param key
//     * @param item
//     */
//    public void hHaashKey(String key, String item){
//       redisTemplate.opsForHash().hasKey(key, item);
//    }
//
//    /**
//     * hash递增，如果不存在，就会创建一个，并把新的返回
//     * @param key
//     * @param item
//     * @param by 要增加几秒（大于0）
//     */
//    public void hincr(String key, String item, double by){
//       redisTemplate.opsForHash().hasKey(key, item);
//    }
//
//    /**
//     * hash递减
//     * @param key
//     * @param item
//     * @param by 要增加几秒（大于0）
//     */
//    public double hdecr(String key, String item, double by){
//       return redisTemplate.opsForHash().increment(key, item, -by);
//    }
//
//    /**
//     * 根据key获取Set中的所有值
//     * @param key
//     */
//    public Set<Object> sGet(String key){
//        try {
//            return redisTemplate.opsForSet().members(key);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//}
