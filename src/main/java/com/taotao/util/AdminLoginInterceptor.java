package com.taotao.util;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.taotao.util.SystemConstants.NOT_AUTH_CODE;

/**
 * @author YuLong
 * Date: 2022/11/21 16:26
 */
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 判断是否需要拦截（ThreadLocal中是否有管理员）
        if (AdminHolder.getAdmin() == null) {
            // 没有，需要拦截，设置状态码
            response.setStatus(NOT_AUTH_CODE);
            // 拦截
            return false;
        }
        // 有管理员，则放行
        return true;
    }
}
