package com.taotao.util;

import com.taotao.dto.AdminDTO;

/**
 * @author YuLong
 * Date: 2022/11/24 21:09
 */
public class AdminHolder {
    private static final ThreadLocal<AdminDTO> THREAD_LOCAL = new ThreadLocal<>();

    public static void removeAdmin() {
        THREAD_LOCAL.remove();
    }

    public static void saveAdmin(AdminDTO admin) {
        THREAD_LOCAL.set(admin);
    }

    public static AdminDTO getAdmin() {
        return THREAD_LOCAL.get();
    }

}