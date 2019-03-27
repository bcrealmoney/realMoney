package com.realMoney;

import com.realMoney.utils.RedisClusterUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class RedisTest {
    @Test
    public void redisTest() throws RedisClusterUtil.RedisClusterException {
        String a = RedisClusterUtil.get("97.64.36.211:7000;97.64.36.211:7001", "a");
        System.out.println(a);
    }
}
