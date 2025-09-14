package cn.hengzq.orange.system.common.constant;

public final class RedisKeys {

    private RedisKeys() {
        throw new AssertionError("Cannot be instantiated!");
    }

    public static final String BASE_KEY_PREFIX = "orange-system:";

    public static final String AUTH_TOKEN_KEY_PREFIX = BASE_KEY_PREFIX + "auth:";

    /**
     * 用户相关缓存KEY
     */
    public static final String USER_KEY_PREFIX = BASE_KEY_PREFIX + "user:";
    public static final String USER_BASIC_KEY_PREFIX = USER_KEY_PREFIX + "basic";
    public static final String USER_DETAIL_KEY_PREFIX = USER_KEY_PREFIX + "detail";

    public static final String ROLE_KEY_PREFIX = BASE_KEY_PREFIX + "role:";
    public static final String ROLE_BASIC_KEY_PREFIX = USER_KEY_PREFIX + "basic";

    /**
     * 构建认证 token 的 Redis Key
     */
    public static String getAuthTokenKey(String token) {
        return AUTH_TOKEN_KEY_PREFIX + token;
    }
}