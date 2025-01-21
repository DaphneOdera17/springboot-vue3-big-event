package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
