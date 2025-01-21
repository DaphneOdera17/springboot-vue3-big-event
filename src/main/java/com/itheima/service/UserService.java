package com.itheima.service;

import com.itheima.pojo.User;

/**
 * @author Birdy
 * @date 2025/1/21 19:15
 * @description UserService
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 注册
     * @param username
     * @param password
     */
    void register(String username, String password);
}
