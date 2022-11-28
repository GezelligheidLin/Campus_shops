package com.taotao.util;

import com.taotao.dto.UserDTO;

/**
 * @author YuLong
 * Date: 2022/11/21 16:29
 */
public class UserHolder {
    private static final ThreadLocal<UserDTO> THREAD_LOCAL = new ThreadLocal<>();

    public static void removeUser() {
        THREAD_LOCAL.remove();
    }

    public static void saveUser(UserDTO user) {
        THREAD_LOCAL.set(user);
    }

    public static UserDTO getUser() {
        return THREAD_LOCAL.get();
    }

}