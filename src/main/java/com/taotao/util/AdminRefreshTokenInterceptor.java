package com.taotao.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.taotao.dto.AdminDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.taotao.util.RedisConstants.ADMIN_TOKEN_KEY;
import static com.taotao.util.RedisConstants.ADMIN_TOKEN_TTL;
import static com.taotao.util.SystemConstants.AUTHORIZATION;


/**
 * @author YuLong
 * Date: 2022/11/21 16:59
 */
@Slf4j
public class AdminRefreshTokenInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public AdminRefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1.获取请求头中的 token
        String token = request.getHeader(AUTHORIZATION);
        log.info("请求头中的token = {}", token);
        if (StrUtil.isBlank(token)) {
            return true;
        }

        // 2.基于 token获取 redis中的管理员
        String key = ADMIN_TOKEN_KEY + token;
        String adminJson = stringRedisTemplate.opsForValue().get(key);

        // 3.判断管理员是否存在
        if (adminJson == null || adminJson.isEmpty()) {
            return true;
        }

        // 4.将查询到的 Json数据转为 AdminDTO对象
        AdminDTO adminDTO = JSONUtil.toBean(adminJson, AdminDTO.class);

        // 5.存在，保存管理员信息到 ThreadLocal
        AdminHolder.saveAdmin(adminDTO);

        // 6.刷新 token有效期
        stringRedisTemplate.expire(key, ADMIN_TOKEN_TTL, TimeUnit.MINUTES);

        // 7.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        // 移除用户
        AdminHolder.removeAdmin();
    }
}
