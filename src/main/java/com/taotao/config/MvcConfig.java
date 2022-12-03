package com.taotao.config;

import com.taotao.util.AdminRefreshTokenInterceptor;
import com.taotao.util.LoginInterceptor;
import com.taotao.util.RefreshTokenInterceptor;
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
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
                .addPathPatterns("/**").order(0);
        // 管理员 token刷新拦截器
        registry.addInterceptor(new AdminRefreshTokenInterceptor(stringRedisTemplate))
                        .addPathPatterns("/**").order(1);
        // 登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        // 待添加
                        "/**/**"
                        // "/upload/**",
                        // "/user/**"
                        // "/admin/**",
                        // "/home/**",
                        // "/goodsType/**",
                        // "/merchant/**"
                ).order(2);
    }
}
