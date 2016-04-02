package com.qtong.health.basic.web.interceptor;

import com.qtong.health.basic.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZML on 2016/3/24.
 */
public class UserAccessLoggerInterceptor implements HandlerInterceptor {

    private static Logger logger = Logger.getLogger(UserAccessLoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String address = HttpUtils.getRealRemoteAddr(request);
        StringBuffer stringBuffer = new StringBuffer("START :");
        logger.info(stringBuffer.append(address).append("------").append(request.getRequestURL()));
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String address = HttpUtils.getRealRemoteAddr(request);
        StringBuffer stringBuffer = new StringBuffer("END :");
        logger.info(stringBuffer.append(address).append("------").append(request.getRequestURL()));
    }
}
