package com.taotao.util;

/**
 * @author YuLong
 * Date: 2022/11/16 15:24
 */
public class RedisConstants {
    public static final String USER_CODE_KEY = "login:code:";
    public static final Long USER_CODE_TTL = 2L;
    public static final String USER_TOKEN_KEY = "login:token:";
    public static final Long USER_TOKEN_TTL = 3600L;

    public static final Long CACHE_NULL_TTL = 2L;
    public static final Long CACHE_SHOP_TTL = 30L;
    public static final String CACHE_SHOP_KEY = "cache:shop:";
    public static final String CACHE_SHOP_TYPE_KEY = "cache:shopType:list";


    public static final String LOCK_SHOP_KEY = "lock:shop:";
    public static final Long LOCK_SHOP_TTL = 10L;
    public static final String ADMIN_CODE_KEY = "admin:code:";
    public static final Long ADMIN_CODE_TTL = 10L;
    public static final String ADMIN_TOKEN_KEY = "admin:token:";
    public static final Long ADMIN_TOKEN_TTL = 3600L;

    public static final String SECKILL_STOCK_KEY = "seckill:stock:";
    public static final String BLOG_LIKED_KEY = "blog:liked:";
    public static final String FEED_KEY = "feed:";
    public static final String SHOP_GEO_KEY = "shop:geo:";
    public static final String USER_SIGN_KEY = "sign:";
}
