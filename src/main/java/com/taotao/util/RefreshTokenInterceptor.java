package com.taotao.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.taotao.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.taotao.util.RedisConstants.LOGIN_USER_KEY;
import static com.taotao.util.RedisConstants.LOGIN_USER_TTL;
import static com.taotao.util.SystemConstants.AUTHORIZATION;


/**
 * @author YuLong
 * Date: 2022/11/21 16:59
 */
@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        // 1.获取请求头中的 token
        String token = request.getHeader(AUTHORIZATION);
        log.info("请求头中的token = {}", token);
        if (StrUtil.isBlank(token)) {
            return true;
        }

        // 2.基于 token获取 redis中的用户
        String key = LOGIN_USER_KEY + token;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash()
                .entries(key);

        // 3.判断用户是否存在，entries方法会进行判断，若取到的值为 null，则返回 空map
        if (userMap.isEmpty()) {
            return true;
        }

        // 4.将查询到的 Hash数据转为 UserDTO对象
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);

        // 5.存在，保存用户信息到 ThreadLocal
        UserHolder.saveUser(userDTO);

        // 6.刷新 token有效期
        stringRedisTemplate.expire(key, LOGIN_USER_TTL, TimeUnit.MINUTES);

        // 7.放行
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object hander, Exception e) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }
}
