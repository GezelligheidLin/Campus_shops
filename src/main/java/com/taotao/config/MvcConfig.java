package com.taotao.config;

import com.taotao.util.AdminLoginInterceptor;
import com.taotao.util.AdminRefreshTokenInterceptor;
import com.taotao.util.UserLoginInterceptor;
import com.taotao.util.UserRefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author YuLong
 * Date: 2022/11/21 14:08
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 用户 token刷新拦截器
        registry.addInterceptor(new UserRefreshTokenInterceptor(stringRedisTemplate))
                .addPathPatterns("/**").order(0);
        // 管理员 token刷新拦截器
        registry.addInterceptor(new AdminRefreshTokenInterceptor(stringRedisTemplate))
                        .addPathPatterns("/**").order(1);
        // 用户登录拦截器
        registry.addInterceptor(new UserLoginInterceptor())
                .excludePathPatterns(
                        // 待添加
                        "/**/**"
                        // "/upload/**",
                        // "/user/**",
                        // "/home/**",
                        // "/goodsType/**",
                        // "/merchant/**"
                ).order(2);
        // 管理员登录拦截器
        registry.addInterceptor(new AdminLoginInterceptor())
                .excludePathPatterns(
                        // 待添加
                        "/**/**"
                ).order(3);
    }
}
