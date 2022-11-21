package com.taotao.util;

import com.taotao.dto.UserDTO;

/**
 * @author YuLong
 * Date: 2022/11/21 16:29
 */
public class UserHolder {
    private static final ThreadLocal<UserDTO> TL = new ThreadLocal<>();

    public static void saveUser(UserDTO user) {
        TL.set(user);
    }

    public static UserDTO getUser() {
        return TL.get();
    }

    public static void removeUser() {
        TL.remove();
    }
}
