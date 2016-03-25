package com.qtong.core.auth;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ZML on 2015/4/22.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private net.sf.ehcache.Cache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {

        passwordRetryCache= cacheManager.getCache("passwordRetryCache");

    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount=null;
        Element userCache= passwordRetryCache.get(username);
         
        if(userCache == null) {
            retryCount = new AtomicInteger(0);

            Element element=new Element(username,retryCount);
            passwordRetryCache.put(element);
        }else{
        	retryCount = (AtomicInteger) passwordRetryCache.get(username).getObjectValue();
        }
        if(retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
