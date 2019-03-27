package com.realMoney.utils;

import com.realMoney.common.Constant;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Tuple;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RedisClusterUtil {

    public static class JedisClusterMultiton{

        private static Map<String, JedisCluster> jedisClusterMap = new ConcurrentHashMap<>();

        public static JedisCluster getInstance(String cluster){

            if(jedisClusterMap.get(cluster) == null){
                if(cluster == null ){
                    cluster = PropertiesUtils.getRedisPropertiesFile().getProperty(Constant.REDIS_KEY);
                }
                synchronized(JedisClusterMultiton.class){
                    if(jedisClusterMap.get(cluster) == null) {

                        String[] servers = cluster.split(";");

                        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
                        poolConfig.setMaxTotal(500);
                        poolConfig.setMaxIdle(500);

                        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
                        for (String server : servers) {
                            String[] serverFields = server.split(":");
                            String host = serverFields[0];
                            int port = Integer.parseInt(serverFields[1]);
                            jedisClusterNodes.add(new HostAndPort(host, port));

                        }
                        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, poolConfig);
                        jedisClusterMap.put(cluster, jedisCluster);
                    }
                }
            }
            return jedisClusterMap.get(cluster);
        }


    }

    public static String get(String key){
        if(key == null){
            return null;
        }
        JedisCluster jedisCluster = JedisClusterMultiton.getInstance(null);

        return jedisCluster.get(key);
    }

    public static Long expire( String key, int secondes)  {
        if(key == null){
            return null;
        }
        JedisCluster jedisCluster = JedisClusterMultiton.getInstance(null);

        return jedisCluster.expire(key, secondes);
    }

    public static String set(String key,String value){
        if(key == null){
            return null;
        }
        JedisCluster jedisCluster = JedisClusterMultiton.getInstance(null);

        return jedisCluster.set(key, value);
    }

    public static String hget(String key,String field) {
        if(key == null){
            return null;
        }
        JedisCluster jedisCluster = JedisClusterMultiton.getInstance(null);
        return  jedisCluster.hget(key,field);
    }

    public static Long hset(String key,String field, String value){
        if(key == null){
            return null;
        }
        JedisCluster jedisCluster = JedisClusterMultiton.getInstance(null);
        return jedisCluster.hset(key, field, value);
    }

    public static Long lpush(String key,String value)  {
        if(key == null){
            return 0L;
        }
        JedisCluster jedisCluster = JedisClusterMultiton.getInstance(null);
        return jedisCluster.lpush(key, value);
    }

    public static String lset(String key,int index,String value) {
        if(key == null){
            return null;
        }
        JedisCluster jedisCluster = JedisClusterMultiton.getInstance(null);
        return jedisCluster.lset(key, index, value);

    }

    public static List<String> getZRange(String key){
        if(key == null){
            return new LinkedList<>();
        }
        JedisCluster jedisCluster = JedisClusterMultiton.getInstance(null);
        Set<String> set = jedisCluster.zrange(key, 0, -1);
        List<String> list = new LinkedList<String>(set);
        return list;
    }

    public static List<Tuple> getZRangeWithScore(String key) {
        if(key == null){
            return new LinkedList<>();
        }
        JedisCluster jedisCluster = JedisClusterMultiton.getInstance(null);
//        Set<Tuple> set = jedisCluster.zrangeWithScores(key, 0, -1);
//        Set<Tuple> set = jedisCluster.zrangeByScoreWithScores(key,"-inf","+inf");
        Set<String> set = jedisCluster.zrange(key, 0, -1);
        LinkedList<Tuple> list = new LinkedList<>();
        for (String item : set) {
            list.add(new Tuple(item, 0.0));
        }
        return list;

    }
}
