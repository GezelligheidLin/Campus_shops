package com.taotao.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.taotao.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.taotao.util.RedisConstants.USER_TOKEN_KEY;
import static com.taotao.util.RedisConstants.USER_TOKEN_TTL;
import static com.taotao.util.SystemConstants.AUTHORIZATION;


/**
 * @author YuLong
 * Date: 2022/11/21 16:59
 */
@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
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

        // 2.基于 token获取 redis中的用户
        String key = USER_TOKEN_KEY + token;
        String userJson = stringRedisTemplate.opsForValue().get(key);

        // 3.判断用户是否存在，若取到的值为 null，则返回
        if (userJson == null || userJson.isEmpty()) {
            return true;
        }

        // 4.将查询到的 json数据转为 UserVO对象
        UserVO userVO = JSONUtil.toBean(userJson, UserVO.class);

        // 5.存在，保存用户信息到 ThreadLocal
        UserHolder.saveUser(userVO);

        // 6.刷新 token有效期
        stringRedisTemplate.expire(key, USER_TOKEN_TTL, TimeUnit.SECONDS);

        // 7.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        // 移除用户
        UserHolder.removeUser();
    }
}
