package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Birdy
 * @date 2025/1/21 19:15
 * @description UserMapper
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);


    /**
     * 注册
     * @param username
     * @param password
     */
    @Insert("insert into user(username, password, create_time, update_time) " +
            "values(#{username}, #{password}, now(), now())")
    void add(String username, String password);

    /**
     * 修改用户信息
     * @param user
     */
    @Update("update user set nickname=#{nickname}, email=#{email}," +
            "update_time=#{updateTime} where id=#{id}")
    void update(User user);

    /**
     * 修改头像
     *
     * @param avatarUrl
     * @param id
     */
    @Update("update user set user_pic=#{avatarUrl}, update_time=now() " +
            "where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    /**
     * 修改密码
     *
     * @param password
     * @param id
     */
    @Update("update user set password = #{password}," +
            " update_time = now() where id=#{id}")
    void updatePwd(String password, Integer id);
}
