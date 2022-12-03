package com.taotao.util;

import com.taotao.vo.UserVO;

/**
 * @author YuLong
 * Date: 2022/11/21 16:29
 */
public class UserHolder {
    private static final ThreadLocal<UserVO> THREAD_LOCAL = new ThreadLocal<>();

    public static void removeUser() {
        THREAD_LOCAL.remove();
    }

    public static void saveUser(UserVO user) {
        THREAD_LOCAL.set(user);
    }

    public static UserVO getUser() {
        return THREAD_LOCAL.get();
    }

}