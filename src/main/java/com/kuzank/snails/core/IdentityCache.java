package com.kuzank.snails.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户身份信息缓存类
 */
@Service
public class IdentityCache {

    /**
     * key : token
     * value : Map<String,Object>
     */
    private Cache<String, Map<String, Object>> identityCache;


    public IdentityCache() {
        identityCache = CacheBuilder.newBuilder()
                .maximumSize(10000).expireAfterAccess(10, TimeUnit.HOURS)
                .build();
    }


    public IdentityCache(Long maximumSize, long duration, TimeUnit unit) {

        identityCache = CacheBuilder.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(duration, unit)
                .build();
    }


    public Map<String, Object> getIdentity(String token) {

        Map<String, Object> identity = identityCache.getIfPresent(token);

        if (identity != null) {
            return identity;
        }

        return null;
    }


    public void add(String token, Map<String, Object> identityInfo) {

        identityCache.put(token, identityInfo);
    }


    public void remove(String token) {

        identityCache.invalidate(token);
    }


    /**
     * expireAfterAccess,获取一下即可保持登录
     *
     * @param token
     * @return
     */
    public boolean keepAliveIfPresent(String token) {
        return identityCache.getIfPresent(token) != null;
    }
}
