package com.qtong.core.web.interceptor;

import com.qtong.core.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by ZML on 2016/3/22.
 */
@ControllerAdvice
public class DefaultExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = Logger.getLogger(DefaultExceptionResolver.class);

    MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();

    /**
     * 所有可捕获的异常都由此抛出
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response,
                                        Exception ex) throws IOException {
        //获取调用客户端的ip
        String remoteAddress = HttpUtils.getRealRemoteAddr(request);
        String targetMethod = null;
        try {
            targetMethod = methodNameResolver.getHandlerMethodName(request);
        } catch (NoSuchRequestHandlingMethodException e) {
            e.printStackTrace();
        }
        logger.warn("REMOTE ADDRESS: " + remoteAddress);
        logger.warn("TARGET METHOD: " + targetMethod);
        logger.warn("PARAMETERMAP: " + request.getParameterMap().toString());
        logger.warn("EXCEPTION:", ex);
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    /**
     * 所有运行时异常都由此抛出，比如空指针，数据库连接丢失，数组越界等，
     * <p>
     * <p>
     * 都是由于服务器运行环境或者代码健壮性不够引起的异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        //获取调用客户端的ip
        String remoteAddress = HttpUtils.getRealRemoteAddr(request);
        String targetMethod = null;
        try {
            targetMethod = methodNameResolver.getHandlerMethodName(request);
        } catch (NoSuchRequestHandlingMethodException e) {
            e.printStackTrace();
        }
        logger.error("REMOTE ADDRESS: " + remoteAddress);
        logger.error("TARGET METHOD: " + targetMethod);
        logger.error("PARAMETERMAP: " + request.getParameterMap().toString());
        logger.error("EXCEPTION:", ex);
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
