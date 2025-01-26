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

    /**
     * 修改用户信息
     * @param user
     */
    void update(User user);

    /**
     * 修改用户头像
     * @param avatarUrl
     */
    void updateAvatar(String avatarUrl);

    /**
     * 修改用户密码
     * @param newPwd
     */
    void updatePwd(String newPwd);
}
