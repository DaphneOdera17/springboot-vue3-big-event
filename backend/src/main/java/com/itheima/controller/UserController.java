package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Birdy
 * @date 2025/1/21 19:10
 * @description UserController
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户
        User user = userService.findByUserName(username);
        if(user == null) {
            // 注册用户
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已经被占用！");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp="^\\S{5,16}$") String username, @Pattern(regexp="^\\S{5,16}$") String password) {
        // 根据用户名查询 User
        User loginUser = userService.findByUserName(username);
        // 判断用户是否存在
        if(loginUser == null) {
            return Result.error("用户名错误");
        }

        // 对获取的密码进行加密，并和数据库中密码进行比对
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            // 将 token 存储到 redis 当中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, 1, TimeUnit.HOURS);

            return Result.success(token);
        }

        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        // 校验参数
        // 旧密码
        String oldPwd = params.get("old_pwd");
        // 新密码
        String newPwd = params.get("new_pwd");
        // 确认密码
        String rePwd = params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd) ||
                !StringUtils.hasLength(newPwd) ||
        !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        // 检验原密码
        // 调用 userService 根据用户名得到原密码，和 oldPwd 比对
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码填写错误");
        }

        if(!rePwd.equals(newPwd)) {
            return Result.error("两次填写的新密码不一样");
        }

        userService.updatePwd(newPwd);

        // 删除 redis 中对应的 token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }
}
