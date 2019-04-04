package com.realMoney.service.user.impl;

import com.realMoney.entity.user.User;
import com.realMoney.mapper.user.LoginMapper;
import com.realMoney.service.user.LoginService;
import com.realMoney.utils.response.Response;
import com.realMoney.utils.response.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Response<User> userAndPasswordLogin(User user) {
        log.info("查询用户信息: ========== {}", user);
        User queryUser = loginMapper.queryByNameWord(user);
        log.info("查询用户结果: ========== {}", queryUser);
        return ResponseUtil.buildResponse(queryUser);
    }

    @Override
    public Response<User> emailCodeLogin(User user) {
        return null;
    }

    @Override
    public Response<User> thridPartyLogin(User user) {
        return null;
    }



}
