package com.itheima.interceptors;

import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author Birdy
 * @date 2025/1/23 15:49
 * @description LoginInterceptor
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        // 验证 token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return true; // 放行
        } catch (Exception e) {
            // http 响应状态码为 401
            response.setStatus(401);
            // 不放行
            return false;
        }
    }
}
