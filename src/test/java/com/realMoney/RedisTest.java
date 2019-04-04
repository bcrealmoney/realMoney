package com.realMoney;

import com.realMoney.utils.RedisClusterUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class RedisTest {
    @Test
    public void redisTest() {
        String a = RedisClusterUtil.get("a");
        System.out.println(a);
    }
}
