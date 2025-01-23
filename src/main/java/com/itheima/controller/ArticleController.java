package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Birdy
 * @date 2025/1/23 14:31
 * @description ArticleController
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(/* @RequestHeader(name = "Authorization") String token, HttpServletResponse response */) {
        // 验证 token
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//        } catch (Exception e) {
//            // http 响应状态码为 401
//            response.setStatus(401);
//            return Result.error("未登录");
//        }

        return Result.success("所有的文章数据...");
    }
}
