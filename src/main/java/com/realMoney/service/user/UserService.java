package com.realMoney.service.user;

import com.realMoney.entity.user.User;
import com.realMoney.utils.response.Response;

public interface UserService {
    Response<User> registUser(User user) ;
}
