package com.lt.demo.service;

import com.lt.demo.bean.UserEntity;

/**
 * @Author: LT
 * @Date: 2019/4/9 16:54
 * @Description:
 * @Version 1.0
 */
public interface UserService {
    Long saveUser(UserEntity userEntity);
}
