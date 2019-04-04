package com.realMoney.service.user.impl;

import com.realMoney.entity.user.User;
import com.realMoney.mapper.user.UserMapper;
import com.realMoney.service.user.UserService;
import com.realMoney.utils.StringUtil;
import com.realMoney.utils.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public Response<User> registUser(User user) {
        user.setUserId(UUID.randomUUID().toString().replaceAll("-",""));
        if(StringUtil.isBlank(user.getShowName()) ){
            user.setShowName(user.getUserName());
        }
        Long aLong = userMapper.insertUser(user);
        System.out.println(aLong);
        return null;
    }


}
