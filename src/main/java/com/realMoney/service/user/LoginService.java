package com.realMoney.service.user;

import com.realMoney.entity.user.User;
import com.realMoney.utils.response.Response;


public interface LoginService {
    Response<User> userAndPasswordLogin(User user);

    Response<User> emailCodeLogin(User user);

    Response<User> thridPartyLogin(User user);
}
