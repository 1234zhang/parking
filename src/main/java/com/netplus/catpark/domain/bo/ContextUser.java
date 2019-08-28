package com.netplus.catpark.domain.bo;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 22:40.
 */

public class ContextUser {
    private static ThreadLocal<RedisUser> entrySet = new ThreadLocal<>();

    /**
     * 这个用户信息set集合插入User
     */
    public static void addUser(RedisUser user) {
        entrySet.set(user);
    }

    /**
     * 获取用户信息
     */
    public static Object getUserInfor() {
        return entrySet.get();
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        // MyAssert.notNull(entrySet.get()== null,"当前线程用户集为空");
//        return entrySet.get().getId();
        return 1L;
    }
    /**
     * 清除本次请求连接
     */
    public static void clear() {
        entrySet.remove();
    }

    /**
     * 直接获取 user 实例
     */
    public static RedisUser getUser() {
        return entrySet.get();
    }
}
