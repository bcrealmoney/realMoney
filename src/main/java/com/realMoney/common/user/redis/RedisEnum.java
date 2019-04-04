package com.realMoney.common.user.redis;

public enum RedisEnum {
    UserCode("user_code_", 5*60 ,"用户登录验证码保存redis: set  user_code_${username}  ${code} ")
    ;
    // key
    private String key;
    // 过期时间 -1 不过期
    private Long expire;
    // key 描述
    private String desc;

    RedisEnum(String key, long expire, String desc) {
        this.key = key;
        this.expire = expire;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
