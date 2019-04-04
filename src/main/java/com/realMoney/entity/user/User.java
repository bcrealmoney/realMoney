package com.realMoney.entity.user;

import lombok.Data;

@Data
public class User {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户短ID
     */
    private Long shortId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 图形验证码
     */
    private String imageCode;

    /**
     * 验证码
     */
    private String code;

    /**
     * 用户票据
     */
    private String upk;

    /**
     * 用户展示名
     */
    private String showName;

    /**
     * 用户头像
     */
    private String userHead;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 第三方登录ID
     */
    private String thirdPartyId;

    /**
     * 登录类型
     * 1  用户名密码登录，2 邮箱验证码登录，3 facebook登录
     */
    private Integer loginType;

}
