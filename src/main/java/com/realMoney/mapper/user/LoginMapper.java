package com.realMoney.mapper.user;

import com.realMoney.entity.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    User queryByNameWord(User user);
}
